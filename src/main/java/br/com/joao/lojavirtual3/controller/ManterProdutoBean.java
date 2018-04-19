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

import br.com.joao.lojavirtual3.model.Produto;
import br.com.joao.lojavirtual3.service.ProdutoService;

@ManagedBean
@ViewScoped
public class ManterProdutoBean implements Serializable {

	private static final long serialVersionUID = -5327540983341723093L;

	private ProdutoService produtoService = new ProdutoService();
	
	private Produto produto = new Produto();
	private List<Produto> produtos;

	public void abrirModalDeProduto(Produto produto) {
		Map<String, Object> options = new HashMap<String, Object>();
		Map<String, List<String>> params = new HashMap<>();
		List<String> produtoId;

		options.put("modal", true);
		options.put("width", "80%");
//		options.put("id", "modalDeProduto");
//		options.put("styleClass", "jm-modal");
		options.put("height", 365);
		options.put("resizable", false);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");

		if (produto != null) {
			produtoId = new ArrayList<String>();
			produtoId.add(produto.getId() + "");
			params.put("produtoId", produtoId);
		}

		RequestContext.getCurrentInstance().openDialog("/pages/produto/modal/cadastroDeProduto", options, params);// params
	}

	public void mostrarMensagemSucesso() {
		this.carregarListaProduto();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "A produto foi salva com sucesso!"));
	}

	public void salvarProduto() {
		RequestContext reqCon = RequestContext.getCurrentInstance();
		try {
			produtoService.salvarPassandoProduto(getProduto());
			reqCon.closeDialog(getProduto());
		} catch (Exception e) {
	        FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contato o administrador."));
		}
	}

	public void carregarListaProduto() {
		try {
			produtos = produtoService.listaDeTodasProdutos();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Não foi possivel listar."));
			produtos = null;
		}
	}

	public void excluirProduto(Produto objeto) {
		try {
			produtoService.deletarPassandoProduto(objeto);
			carregarListaProduto();
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "A produto foi excluida!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Não foi possivel excluir."));
		}
	}
	
	//GETTERS AND SETTERS
	public Produto getProduto() {
		if(produto == null){
			produto = produtoService.inicializarObjeto();
		}
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		if (produtos == null) {
			carregarListaProduto();
		}
		return produtos; 
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
