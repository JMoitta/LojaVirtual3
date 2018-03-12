package persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Fone;
import model.Pessoa;
import model.Produto;

public class PessoaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void inserir(Pessoa pessoa) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.save(pessoa);
		t.commit();
		sessao.close();
	}

	public static void alterar(Pessoa pessoa) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.update(pessoa);
		t.commit();
		sessao.close();
	}
	
	public static void excluir(Pessoa pessoa) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Transaction t = sessao.beginTransaction();
		sessao.delete(pessoa);
		t.commit();
		sessao.close();
	}
	
	public static List<Pessoa> listagem(String filtro) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Query consulta;
		if(filtro.trim().length() == 0){
			consulta = sessao.createQuery("from Pessoa p order by p.id");
		} else {
			consulta = sessao.createQuery("from Pessoa p "
					+ "where  p.id like :parametro order by  p.id");
			consulta.setString("parametro", "%" + filtro + "%");
		}
		List<Pessoa> lista = consulta.list();
		sessao.close();
		return lista;
	}

	public static Pessoa buscarPorId(int pessoaId) {
		Session sessao = HibernateUtil.getSessionfactory().openSession();
		Query consulta;
			consulta = sessao.createQuery("SELECT p FROM Pessoa p WHERE p.id = :pessoaId").setParameter("pessoaId", pessoaId);
		Pessoa pessoa = (Pessoa) consulta.uniqueResult();
		sessao.close();
		return pessoa;
	}
}
