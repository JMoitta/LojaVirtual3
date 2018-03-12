package util.jpa;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import exception.DAOException;
import exception.NegocioException;
import helper.Assert;

public abstract class GenericDAO<T, PK extends Serializable> extends LazyDataModel<T> implements Serializable {

	private static final long serialVersionUID = -1350868251167801491L;

	protected EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;

	public Object executeQuery(String query, Object... params) {
		Query createdQuery = this.entityManager.createQuery(query);
		for (int i = 0; i < params.length; i++) {
			createdQuery.setParameter(i, params[i]);
		}
		return createdQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> getList(Class<T> type) {
		return this.entityManager.createQuery(("FROM " + type.getName())).getResultList();
	}

	public T findById(Class<T> type, PK pk) {
		return this.entityManager.find(type, pk);
	}

	public boolean findBy(T t) throws NegocioException {
		return this.entityManager.contains(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> selectList(String select, HashMap<String, Object> map) throws Exception {
		Query query = this.entityManager.createQuery(select);
		List<T> res = new ArrayList<T>();

		try {
			for (String key : map.keySet())
				query.setParameter(key, map.get(key));

			res.addAll(query.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
			return res;
		}
		return res;
	}

	@Transactional
	public void insert(T entity) throws NegocioException {
		this.entityManager.persist(entity);
	}

	@Transactional
	public void update(T entity) throws NegocioException {
		this.entityManager.merge(entity);
	}

	/**
	 * Abstrai detalhes (iserção ou edição) da persistência de um objeto no
	 * banco de dados. Método experimental.
	 * 
	 * @param entity
	 * @throws DAOException
	 * @author thiago
	 * @throws NegocioException
	 */
	@Transactional
	public void save(T entity) throws DAOException, NegocioException {
		try {
			if (entity != null) {
				Class<?> clazz = entity.getClass();
				Field field = clazz.getField("id");
				field.setAccessible(true);
				// entidade não gerenciada?
				if (!entityManager.contains(entity)) {
					// estado transient.
					if (field.get(entity) == null) {
						entityManager.persist(entity);
					} else {
						entity = entityManager.merge(entity);
					}
				}
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new DAOException(e);
		}
	}

	@Transactional
	public void delete(Class<T> type, PK codigo) throws NegocioException {
		this.entityManager.remove(this.findById(type, codigo));
		this.entityManager.flush();
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		List<T> elements = this.getWithPagination(first, pageSize, filters);
		this.setRowCount(this.getNumberOfEntities(first, pageSize, filters).intValue());
		return elements;
	}

	public abstract Long getNumberOfEntities(int first, int pageSize, Map<String, Object> filters);

	public abstract List<T> getWithPagination(int first, int pageSize, Map<String, Object> filters);

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityTransaction getTransaction() {
		return entityManager.getTransaction();
	}

	public EntityTransaction transaction() {
		return getTransaction();
	}

	public void begin() {
		getTransaction().begin();
	}

	public void commit() {
		getTransaction().commit();
	}

	public void rollback() {
		getTransaction().rollback();
	}

	/**
	 * @author thiago-amm
	 * @param clazz
	 * @param criteria
	 * @return
	 */
	public Long count(Class<T> clazz, String... criteria) {
		Table table = clazz.getAnnotation(javax.persistence.Table.class);
		if (table != null) {
			String hql = String.format("SELECT COUNT(*) FROM %s %s", clazz.getSimpleName(),
					clazz.getSimpleName().toLowerCase());
			if (criteria != null && criteria.length >= 1) {
				StringBuilder conditions = new StringBuilder();
				for (String c : criteria) {
					conditions.append(c);
				}
				hql = String.format("%s WHERE %s", hql, conditions.toString());
			}
			TypedQuery<Long> query = this.entityManager.createQuery(hql, Long.class);
			return query.getSingleResult();
		} else {
			try {
				throw new DAOException("A classe informada não é uma Entidade JPA!");
			} catch (DAOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * @author thiago-amm
	 * @param clazz
	 * @param rows
	 * @param start
	 * @param criteria
	 * @return
	 */
	public List<T> paginate(Class<T> clazz, Integer rows, Integer start, String... criteria) {
		TypedQuery<T> query = null;
		Table table = clazz.getAnnotation(javax.persistence.Table.class);
		if (table != null) {
			String hql = String.format("SELECT %s FROM %s %s", clazz.getSimpleName().toLowerCase(),
					clazz.getSimpleName(), clazz.getSimpleName().toLowerCase());
			if (criteria != null && criteria.length >= 1) {
				StringBuilder conditions = new StringBuilder();
				for (String c : criteria) {
					if (Assert.isNotEmptyOrNull(c)) {
						conditions.append(c);
					}
				}
				if (!conditions.toString().isEmpty()) {
					hql = String.format("%s WHERE %s", hql, conditions.toString());
				}
			}
			rows = rows == null ? 10 : rows;
			start = start == null ? 0 : start;
			query = this.entityManager.createQuery(hql, clazz);
			query.setFirstResult(start);
			query.setMaxResults(rows);
			return query.getResultList();
		} else {
			try {
				throw new DAOException("A classe informada não é uma Entidade JPA!");
			} catch (DAOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public List<T> paginate(Class<T> clazz, Integer rows, Integer start, String parameter, String value,
			String... criteria) {
		TypedQuery<T> query = null;
		Table table = clazz.getAnnotation(javax.persistence.Table.class);
		if (table != null) {
			String hql = String.format("SELECT %s FROM %s %s", clazz.getSimpleName().toLowerCase(),
					clazz.getSimpleName(), clazz.getSimpleName().toLowerCase());
			if (criteria != null && criteria.length >= 1) {
				StringBuilder conditions = new StringBuilder();
				for (String c : criteria) {
					if (Assert.isNotEmptyOrNull(c)) {
						conditions.append(c);
					}
				}
				if (!conditions.toString().isEmpty()) {
					hql = String.format("%s WHERE %s", hql, conditions.toString());
				}
			}
			rows = rows == null ? 10 : rows;
			start = start == null ? 0 : start;
			query = this.entityManager.createQuery(hql, clazz).setParameter(parameter, value);
			query.setFirstResult(start);
			query.setMaxResults(rows);
			return query.getResultList();
		} else {
			try {
				throw new DAOException("A classe informada não é uma Entidade JPA!");
			} catch (DAOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public List<T> paginate(Class<T> clazz, Integer rows, Integer start, String join, Class joinClass, Object obj,
			String... criteria) {
		TypedQuery<T> query = null;
		Table table = clazz.getAnnotation(javax.persistence.Table.class);
		if (table != null) {
			String hql = String.format("SELECT DISTINCT(%s) FROM %s %s JOIN %s.%s %s",
					clazz.getSimpleName().toLowerCase(), clazz.getSimpleName(), clazz.getSimpleName().toLowerCase(),
					clazz.getSimpleName().toLowerCase(), join, join.toLowerCase());
			if (criteria != null && criteria.length >= 1) {
				StringBuilder conditions = new StringBuilder();
				for (String c : criteria) {
					if (Assert.isNotEmptyOrNull(c)) {
						conditions.append(c);
					}
				}
				if (!conditions.toString().isEmpty()) {
					hql = String.format("%s WHERE %s", hql, conditions.toString());
				}
			}
			rows = rows == null ? 10 : rows;
			start = start == null ? 0 : start;
			query = this.entityManager.createQuery(hql, clazz).setParameter(joinClass.getSimpleName().toLowerCase(),
					obj);
			query.setFirstResult(start);
			query.setMaxResults(rows);
			return query.getResultList();
		} else {
			try {
				throw new DAOException("A classe informada não é uma Entidade JPA!");
			} catch (DAOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * @author thiago-amm
	 * @author manoel
	 * @param clazz
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Long count(Class<T> clazz, String join, Class joinClass, Object obj, String... criteria) {
		Table table = clazz.getAnnotation(javax.persistence.Table.class);
		if (table != null) {
			String hql = String.format("SELECT %s FROM %s %s JOIN %s.%s %s", clazz.getSimpleName().toLowerCase(),
					clazz.getSimpleName(), clazz.getSimpleName().toLowerCase(), clazz.getSimpleName().toLowerCase(),
					join, join.toLowerCase());
			if (criteria != null && criteria.length >= 1) {
				StringBuilder conditions = new StringBuilder();
				for (String c : criteria) {
					conditions.append(c);
				}
				hql = String.format("%s WHERE %s", hql, conditions.toString());
			}
			TypedQuery<T> query = this.entityManager.createQuery(hql, clazz)
					.setParameter(joinClass.getSimpleName().toLowerCase(), obj);
			List<T> list = query.getResultList();
			return list.stream().distinct().count();
		} else {
			try {
				throw new DAOException("A classe informada não é uma Entidade JPA!");
			} catch (DAOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public List<T> paginate(Class<T> clazz, Integer rows, Integer start) {
		return paginate(clazz, rows, start, (String[]) null);
	}

	/**
	 * @author thiago-amm
	 * @param clazz
	 * @return
	 */
	public List<T> paginate(Class<T> clazz) {
		return paginate(clazz, null, null, (String[]) null);
	}

	/**
	 * @author thiago-amm
	 * @param clazz
	 * @param criteria
	 * @return
	 */
	public List<T> paginate(Class<T> clazz, String... criteria) {
		return paginate(clazz, null, null, criteria);
	}

	/**
	 * @author thiago-amm
	 * @return
	 */
	public EntityManagerFactory getEntityManagerFactory() {
		if (entityManager != null) {
			return entityManager.getEntityManagerFactory();
		}
		return null;
	}

	/**
	 * @author thiago-amm
	 * @param persistenceUnit
	 * @return
	 */
	public EntityManagerFactory getEntityManagerFactory(String persistenceUnit) {
		persistenceUnit = persistenceUnit == null ? "" : persistenceUnit;
		if (!persistenceUnit.isEmpty()) {
			return entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
		}
		return null;
	}

	/**
	 * @author thiago-amm
	 * @param persistenceUnit
	 * @return
	 */
	public EntityManager getEntityManager(String persistenceUnit) {
		EntityManager entityManager = null;
		EntityManagerFactory entityManagerFactory = getEntityManagerFactory(persistenceUnit);
		if (entityManagerFactory != null) {
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}

	/**
	 * @author thiago-amm
	 * @param persistenceUnit
	 */
	public void use(String persistenceUnit) {
		persistenceUnit = persistenceUnit == null ? "" : persistenceUnit;
		if (!persistenceUnit.isEmpty()) {
			this.entityManager = getEntityManager(persistenceUnit);
		}
	}

	/**
	 * @author thiago-amm
	 * @param hql
	 * @param clazz
	 * @return
	 * @throws DAOException
	 */
	public TypedQuery<T> getQuery(String hql, Class<T> clazz) throws DAOException {
		TypedQuery<T> query = null;
		hql = hql == null ? "" : hql;
		if (!hql.isEmpty()) {
			if (entityManager == null) {
				throw new DAOException("Nenhum EntityManager está sendo referenciado!");
			}
			query = entityManager.createQuery(hql, clazz);
		}
		return query;
	}

	/**
	 * @author thiago-amm
	 * @param hql
	 * @param clazz
	 * @return
	 * @throws DAOException
	 */
	public List<T> select(String hql, Class<T> clazz) throws DAOException {
		List<T> list = null;
		hql = hql == null ? "" : hql;
		if (!hql.isEmpty()) {
			if (entityManager == null) {
				throw new DAOException("Nenhum EntityManager está sendo referenciado!");
			}
			TypedQuery<T> query = entityManager.createQuery(hql, clazz);
			list = query.getResultList();
		}
		return list;
	}

	/**
	 * @author thiago-amm
	 * @param hql
	 * @param clazz
	 * @return
	 * @throws DAOException
	 */
	public int update(String hql, Class<T> clazz) throws DAOException {
		int rowsAffected = 0;
		hql = hql == null ? "" : hql;
		if (!hql.isEmpty()) {
			if (entityManager == null) {
				throw new DAOException("Nenhum EntityManager está sendo referenciado!");
			}
			TypedQuery<T> query = entityManager.createQuery(hql, clazz);
			rowsAffected = query.executeUpdate();
		}
		return rowsAffected;
	}

	public void close() {
		if (entityManager != null) {
			entityManager.close();
		}
	}

}
