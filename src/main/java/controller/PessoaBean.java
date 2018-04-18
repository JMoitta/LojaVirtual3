package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Fone;
import model.Pessoa;
import persistencia.FoneDAO;
import persistencia.PessoaDAO;
import service.PessoaService;

@ManagedBean
@SessionScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PessoaService pessoaService = new PessoaService();
	
	private Pessoa pessoa;
	private Fone fone;
	private List<Fone> fonesASeremExcluidos;
	
	public List<Pessoa> getListagem() {
		return pessoaService.buscarTodasAsPessoas();
	}

	public String actionGravar() {
		List<Fone> fones = getFonesASeremExcluidos();
		if(fones.size() > 0) {
			fones.forEach(fone -> {
				if(fone.getId() > 0) {
					FoneDAO.excluir(fone);
				}
			});
		}
		if (pessoa.getId() == 0) {
			PessoaDAO.inserir(pessoa);
		} else {
			PessoaDAO.alterar(pessoa);
		}
		return "/pages/pessoa/administrarPessoas?faces-redirect=true";
	}

	public String actionInserir() {
		inicializaPessoa();
		inicializaFone(); 
		return "/pages/pessoa/cadastroDePessoa?faces-redirect=true";
	}

	private void inicializaPessoa() {
		setPessoa(new Pessoa());
	}

	public String actionExcluir(Pessoa pessoa) {
		PessoaDAO.excluir(pessoa);
		return "/pages/pessoa/administrarPessoas?faces-redirect=true";
	}

	public String actionAlterar(Pessoa pessoa) {
		setPessoa(pessoa);
		inicializaFone();
		return "/pages/pessoa/cadastroDePessoa?faces-redirect=true";
	}
	
	public void adicionarFone(Fone fone) {
		List<Fone> fones =
		getPessoa().getFones();
		if(!fones.contains(fone)) {
			fones.add(fone);
		}
		inicializaFone();
	}

	public void alterarFone(Fone fone) {
		setFone(fone);
	}
	
	public String excluirFone(Fone fone){
		getPessoa().getFones().remove(fone);
		FoneDAO.excluir(fone);
		getFonesASeremExcluidos().add(fone);
		return "";
	}
	public Fone getFone() {
		if(fone == null){
			inicializaFone();
		}
		return fone;
	}

	private void inicializaFone() {
		fone = new Fone();
		fone.setPessoa(getPessoa());
	}

	public void setFone(Fone fone) {
		this.fone = fone;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Fone> getFonesASeremExcluidos() {
		if(fonesASeremExcluidos == null){
			inicializaFonesASeremExcluidos();
		}
		return fonesASeremExcluidos;
	}
	
	private void inicializaFonesASeremExcluidos() {
		this.fonesASeremExcluidos = new ArrayList<>();
	}

	public void setFonesASeremExcluidos(List<Fone> fonesASeremExcluidos) {
		this.fonesASeremExcluidos = fonesASeremExcluidos;
	}

}
