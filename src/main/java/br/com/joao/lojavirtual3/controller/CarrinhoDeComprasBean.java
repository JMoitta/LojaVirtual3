package br.com.joao.lojavirtual3.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.joao.lojavirtual3.model.ItensPedido;
import br.com.joao.lojavirtual3.model.Pedido;
import br.com.joao.lojavirtual3.model.Produto;
import br.com.joao.lojavirtual3.service.CarrinhoDeComprasService;

@ManagedBean
@SessionScoped
public class CarrinhoDeComprasBean implements Serializable {

	private static final long serialVersionUID = -4216482421562681132L;
	
	private Pedido pedido;
	private CarrinhoDeComprasService carrinhoDeComprasService = new CarrinhoDeComprasService();
	
	public void adicionarProduto(Produto produto) {
		List<ItensPedido> itensPedidos = getPedido().getItensPedidos();
		
		if(!itensPedidos.contains(produto)) {
			
		}
		ItensPedido ip = new ItensPedido();
		
		ip.setProduto(produto);
		ip.setQtd(1);
		ip.setValorUnit(produto.getPreco());
		ip.setSubtotal(produto.getPreco());
		ip.setPedido(getPedido());
		
		itensPedidos.add(ip);
	}
	
	public int totalDeProdutos() {
		return carrinhoDeComprasService.totalDeProdutos(getPedido().getItensPedidos());
	}
	
	public Pedido getPedido() {
		if( pedido == null) {
			pedido = new Pedido();
		}
		return  pedido;
	}
}
