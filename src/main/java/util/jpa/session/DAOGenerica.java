package util.jpa.session;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Pessoa;
import persistencia.HibernateUtil;

public abstract class DAOGenerica<T> implements Serializable {
	
	private static final long serialVersionUID = 3495514230255671485L;

	public void inserir(Class<T> entidade) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(entidade);
		t.commit();
		sessao.close();
	}

	public void alterar(Class<T> entidade) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(entidade);
		t.commit();
		sessao.close();
	}

	public void excluir(Class<T> entidade) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(entidade);
		t.commit();
		sessao.close();
	}
	public List<T> buscarTodos(Class<T> clazz) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		List<T> lista = sessao.createQuery("FROM "+ clazz.getName()).list();
		t.commit();
		sessao.close();
		
		return lista;
	}
}
