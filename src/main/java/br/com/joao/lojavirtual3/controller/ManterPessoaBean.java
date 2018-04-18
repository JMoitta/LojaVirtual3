package br.com.joao.lojavirtual3.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.joao.lojavirtual3.model.Fone;
import br.com.joao.lojavirtual3.model.Pessoa;
import br.com.joao.lojavirtual3.service.FoneService;
import br.com.joao.lojavirtual3.service.PessoaService;

@ManagedBean
@ViewScoped
public class ManterPessoaBean implements Serializable {

	private static final long serialVersionUID = -5327540983341723093L;

	private PessoaService pessoaService = new PessoaService();
	private FoneService foneService = new FoneService();
	
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas;

	public void abrirModalDePessoa(Pessoa pessoa) {
		Map<String, Object> options = new HashMap<String, Object>();
		Map<String, List<String>> params = new HashMap<>();
		List<String> pessoaId;

		options.put("modal", true);
		options.put("width", "80%");
//		options.put("id", "modalDePessoa");
//		options.put("styleClass", "jm-modal");
		options.put("height", 365);
		options.put("resizable", false);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");

		if (pessoa != null) {
			pessoaId = new ArrayList<String>();
			pessoaId.add(pessoa.getId() + "");
			params.put("pessoaId", pessoaId);
		}

		RequestContext.getCurrentInstance().openDialog("/pages/pessoa/modal/cadastroDePessoa", options, params);// params
	}

	public void mostrarMensagemSucesso() {
		this.carregarListaPessoa();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "A pessoa foi salva com sucesso!"));
	}

	public void salvarPessoa() {
		RequestContext reqCon = RequestContext.getCurrentInstance();
		try {
			pessoaService.salvarPassandoPessoa(getPessoa());
			reqCon.closeDialog(getPessoa());
		} catch (Exception e) {
	        FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contato o administrador."));
		}
	}

	public void carregarListaPessoa() {
		try {
			pessoas = pessoaService.listaDeTodasPessoas();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Não foi possivel listar."));
			pessoas = null;
		}
	}
	public Fone carregarFone() {
		Fone fone = new Fone();
		fone.setPessoa(getPessoa());
		return fone;
	}

	public void excluirPessoa(Pessoa objeto) {
		try {
			pessoaService.deletarPassandoPessoa(objeto);
			carregarListaPessoa();
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "A pessoa foi excluida!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Não foi possivel excluir."));
		}
	}
	
	public void adicionarTelefonePessoa(){
		List<Fone> fones = pessoa.getFones();
		
		fones.add(carregarFone());
	}
	
	public void excluirTelefonePessoa(Fone fone) {
		List<Fone> fones = getPessoa().getFones();
		
		if(fones.contains(fone)) {
			fones.remove(fone);
		}
		try {
			foneService.deletarPassandoFone(fone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//GETTERS AND SETTERS
	public Pessoa getPessoa() {
		if(pessoa == null){
			pessoa = pessoaService.inicializarObjeto();
		}
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		if (pessoas == null) {
			carregarListaPessoa();
		}
		return pessoas; 
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
}
