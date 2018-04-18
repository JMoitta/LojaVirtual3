package br.com.joao.lojavirtual3.util.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOAbstract<T extends PrimaryKeyTest> implements Serializable {

	private static final long serialVersionUID = -44903475258537556L;

	public void save(T objeto) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(objeto);
		t.commit();
		sessao.close();
	}

	public void merge(T objeto) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.merge(objeto);
		t.commit();
		sessao.close();
	}

	public void update(T objeto) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(objeto);
		t.commit();
		sessao.close();
	}

	public void delete(T objeto) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(objeto);
		t.commit();
		sessao.close();
	}

	public List<T> selecioneALista(String condicao, Map<String, Object> paramentros) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Query consulta;
		List<T> lista = new ArrayList<>();
		consulta = sessao.createQuery(condicao);
		if (paramentros != null && (paramentros.size() > 0)) {
			for (String key : paramentros.keySet()) {
				consulta.setParameter(key, paramentros.get(key));
			}
		}
		lista.addAll(consulta.list());
		sessao.close();
		return lista;
	}

	public List<T> selecioneTodos(Class<T> classe) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Query consulta;
		List<T> lista = new ArrayList<>();
		consulta = sessao.createQuery("FROM " + classe.getSimpleName());

		lista.addAll(consulta.list());
		sessao.close();
		return lista;
	}

	public T buscarPor(String condicao, Map<String, Object> paramentros) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Query consulta;
		T objeto;
		consulta = sessao.createQuery(condicao);
		if (paramentros != null && (paramentros.size() > 0)) {
			for (String key : paramentros.keySet()) {
				consulta.setParameter(key, paramentros.get(key));
			}
		}
		objeto = (T) consulta.uniqueResult();
		sessao.close();
		return objeto;
	}

	public T buscarPorId(Class<T> classe, Long id) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		StringBuilder condicao = new StringBuilder("SELECT t FROM " + classe.getSimpleName() + " t WHERE t.id = :id");
		Query consulta;
		T objeto;

		try {
			consulta = sessao.createQuery(condicao.toString());
			consulta.setParameter("id", id);
			objeto = (T) consulta.uniqueResult();
			sessao.close();
			return objeto;
		} catch (Exception e) {
			return null;
		}
	}
}
