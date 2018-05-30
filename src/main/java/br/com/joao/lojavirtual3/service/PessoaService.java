package br.com.joao.lojavirtual3.service;

import java.io.Serializable;
import java.util.List;

import br.com.joao.lojavirtual3.dao.PessoaDAO;
import br.com.joao.lojavirtual3.model.Pessoa;

public class PessoaService implements Serializable {

	private static final long serialVersionUID = -8316801243607929061L;

	private PessoaDAO pessoaDAO = new PessoaDAO();

	public Pessoa inicializarObjeto() {
		Pessoa pessoa = new Pessoa();
		return pessoa;
	}
	
	public Pessoa buscarPessoaPorId(long pessoaId) {
		return pessoaDAO.buscarPorId(Pessoa.class, pessoaId);
	}

	public void salvarPassandoPessoa(Pessoa pessoa) throws Exception {
		try {
			if(pessoa.getId() == 0)
				getPessoaDAO().merge(pessoa);
			else
				getPessoaDAO().update(pessoa);
 		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void deletarPassandoPessoa(Pessoa pessoa) throws Exception {
		try {
			getPessoaDAO().delete(pessoa);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<Pessoa> listaDeTodasPessoas() throws Exception {
		try {
			return getPessoaDAO().selecioneTodos(Pessoa.class);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	public Pessoa buscarPessoaPorEmail(String email) throws Exception {
		Pessoa pessoa = new Pessoa();
		pessoa.setEmail(email);
		return pessoaDAO.buscarPessoaPorEmail(pessoa);
	}
	
	public Pessoa buscarPessoaPorEmail(Pessoa pessoa) throws Exception {
		return pessoaDAO.buscarPessoaPorEmail(pessoa);
	}

	public boolean existeUsuario(Pessoa pessoa) {
		return pessoaDAO.buscarPessoaPorEmail(pessoa) != null;
	}
}
