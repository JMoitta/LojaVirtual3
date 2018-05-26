package br.com.joao.lojavirtual3.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.joao.lojavirtual3.model.FormaPgto;
import br.com.joao.lojavirtual3.model.ItensPedido;
import br.com.joao.lojavirtual3.model.Pedido;
import br.com.joao.lojavirtual3.model.Produto;
import br.com.joao.lojavirtual3.service.CarrinhoDeComprasService;
import br.com.joao.lojavirtual3.service.FormaPgtoService;
import br.com.joao.lojavirtual3.util.seguranca.Seguranca;

@ManagedBean
@SessionScoped
public class CarrinhoDeComprasBean implements Serializable {

	private static final long serialVersionUID = -4216482421562681132L;
	
	private Pedido pedido;
	private CarrinhoDeComprasService carrinhoDeComprasService = new CarrinhoDeComprasService();
	private FormaPgtoService formaPgtoService = new FormaPgtoService();
	private Seguranca seguranca = new Seguranca();
	private Produto produto;
	
	public void adicionarProduto(Produto produto) {
		List<ItensPedido> itensPedidos = getPedido().getItensPedidos();
		ItensPedido ip = new ItensPedido();
		
		ip.setProduto(produto);
		ip.setQtd(1);
		ip.setValorUnit(produto.getPreco());
		ip.setSubtotal(produto.getPreco());
		ip.setPedido(getPedido());
		
		itensPedidos.add(ip);
		
		this.produto = produto;
		calcularValorPedido();
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO , "Pedido finalizado com sucesso!", null));
		
	}
	
	public boolean produtoNaoEstaNoCarrinho(Produto produto) {
		List<ItensPedido> itensPedidos = getPedido().getItensPedidos();
		
		for (ItensPedido ip : itensPedidos) {
			if(ip.getProduto().equals(produto)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	public void removerProdutoDoCarrinho(Produto produto) {
		List<ItensPedido> itensPedidos = getPedido().getItensPedidos();
		this.produto = produto;
		
		for (ItensPedido ip : itensPedidos) {
			if(ip.getProduto().getId() == produto.getId()) {
				itensPedidos.remove(ip);
				calcularValorPedido();
				break;
			}
		}
	}
	
	public void mensagem() {
		FacesContext.getCurrentInstance().addMessage(
				null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO , "Carrinho:", 
						String.format("Produto %s adicionado no carrinho", produto.getNome())));
	}
	
	public void mensagemRetirarDoCarrinho() {
		FacesContext.getCurrentInstance().addMessage(
				null, 
				new FacesMessage(FacesMessage.SEVERITY_WARN , "Carrinho:", 
						String.format("Produto %s removido no carrinho", produto.getNome())));
	}
	
	public void calcularValorTotal(ItensPedido itensPedido) {
		itensPedido.setSubtotal(itensPedido.getQtd() * itensPedido.getProduto().getPreco());
		calcularValorPedido();
	}
	
	public void calcularValorPedido() {
		float valorTotal = 0;
		for(ItensPedido itensPedidofor : getPedido().getItensPedidos()) {
			valorTotal += itensPedidofor.getProduto().getPreco() * itensPedidofor.getQtd();
		}
		
		getPedido().setTotal(valorTotal);
	}
	
	public int totalDeProdutos() {
		return carrinhoDeComprasService.totalDeProdutos(getPedido().getItensPedidos());
	}
	
	public String finalizarPedido() {
		getPedido().setPessoa(seguranca.getUsuario());
		carrinhoDeComprasService.finalizarPedido(getPedido());
		
		inicializaNovoPedido();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO , "Pedido finalizado com sucesso!", null));
		
		return "/index";
	}
	
	public void inicializaNovoPedido() {
		pedido = new Pedido();
		pedido.setFormaPgto(formaPgtoService.formaPgtoPadrao());
	}
	public void definirFormaPgto(FormaPgto formaPgto){
		getPedido().setFormaPgto(formaPgto);
	}
	public List<FormaPgto> getFormaPgtos() {
		List<FormaPgto> formaPgtos = null;
		try {
			formaPgtos = formaPgtoService.listaDeTodasFormaPgtos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formaPgtos;
	}
	
	public Pedido getPedido() {
		if( pedido == null) {
			inicializaNovoPedido();
		}
		return  pedido;
	}
	
	protected void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public List<Produto> getProdutos() {
		List<ItensPedido> itensPedidos = getPedido().getItensPedidos();
		List<Produto> produtosR = new ArrayList<>();
		
		for(ItensPedido itensPedido : itensPedidos) {
			produtosR.add(itensPedido.getProduto());
		}
		
		return produtosR;
	}
	
	public List<Integer> getQtdMaxPorProduto() {
		int qtdMax = 10;
		List<Integer> qtdMaxPorProduto = new ArrayList<>(qtdMax);
		for(int i = 1; i <= qtdMax; i ++) {
			qtdMaxPorProduto.add(i);
		}
		
		return qtdMaxPorProduto;
	}
}
