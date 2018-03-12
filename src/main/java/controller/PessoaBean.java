package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Pessoa;
import persistencia.PessoaDAO;

@ManagedBean
@SessionScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;

	public List<Pessoa> getListagem() {
		return PessoaDAO.listagem("");
	}

	public String actionGravar() {
		if (pessoa.getId() == 0) {
			PessoaDAO.inserir(pessoa);
		} else {
			PessoaDAO.alterar(pessoa);
		}
		return "/pages/pessoa/administrarPessoas?faces-redirect=true";
	}

	public String actionInserir() {
		pessoa = new Pessoa();
		return "/pages/pessoa/cadastroDePessoa?faces-redirect=true";
	}

	public String actionExcluir(Pessoa pessoa) {
		PessoaDAO.excluir(pessoa);
		return "/pages/pessoa/administrarPessoas?faces-redirect=true";
	}

	public String actionAlterar(Pessoa pessoa) {
		PessoaDAO.alterar(pessoa);
		return "/pages/pessoa/cadastroDePessoa?faces-redirect=true";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
