package service;

import java.util.List;

import dao.PessoaDAO;
import model.Pessoa;

public class PessoaService {

	private PessoaDAO pessoaDAO = new PessoaDAO();
	
	public List<Pessoa> buscarTodasAsPessoas() {
		return pessoaDAO.buscarTodos(Pessoa.class);
	}

}
