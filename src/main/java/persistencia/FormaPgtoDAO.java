package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.FormaPgto;

public class FormaPgtoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void inserir(FormaPgto formaPgto) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(formaPgto);
		t.commit();
		sessao.close();
	}

	public static void alterar(FormaPgto formaPgto) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(formaPgto);
		t.commit();
		sessao.close();
	}
	
	public static void excluir(FormaPgto formaPgto) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(formaPgto);
		t.commit();
		sessao.close();
	}
	
	public static List<FormaPgto> listagem(String filtro) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Query consulta;
		if(filtro.trim().length() == 0){
			consulta = sessao.createQuery("from FormaPgto f order by f.id");
		} else {
			consulta = sessao.createQuery("from FormaPgto f "
					+ "where  f.descricao like :parametro order by  f.descricao");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List<FormaPgto> lista = consulta.list();
		sessao.close();
		return lista;
	}
	
	public static FormaPgto pesId(int valor) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Query consulta = sessao.createQuery("from FormaPgto where f.id = :id");
		consulta.setParameter("id", valor);
		sessao.close();
		return (FormaPgto) consulta.uniqueResult();
	}
}
