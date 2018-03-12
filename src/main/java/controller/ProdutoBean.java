package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Produto;
import persistencia.ProdutoDAO;

@ManagedBean
@SessionScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;

	public Produto getProduto() {
		if(produto == null) {
			produto = new Produto();
		}
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getListagem() {
		return ProdutoDAO.listagem("");
	}
	
	public String actionGravar(){
		if(produto.getId() == 0) {
			ProdutoDAO.inserir(produto);
		} else {
			ProdutoDAO.alterar(produto);
		}
		return "/pages/produto/administrarProdutos?faces-redirect=true";
	}
	public String actionInserir() {
		produto = new Produto();
		return "/pages/produto/cadastroDeProduto?faces-redirect=true";
	}
	
	public String actionExcluir(Produto p){
		ProdutoDAO.excluir(p);
		return "/pages/produto/administrarPessoas?faces-redirect=true";
	}
	
	public String actionAlterar(Produto p){
		ProdutoDAO.alterar(p);
		return "/pages/produto/cadastroDeProduto?faces-redirect=true";
	}
	
}
