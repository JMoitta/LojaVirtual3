package br.com.joao.lojavirtual3.service;

import java.io.Serializable;
import java.util.List;

import br.com.joao.lojavirtual3.dao.FoneDAO;
import br.com.joao.lojavirtual3.dao.PedidoDAO;
import br.com.joao.lojavirtual3.model.Fone;
import br.com.joao.lojavirtual3.model.ItensPedido;
import br.com.joao.lojavirtual3.model.Pedido;

public class CarrinhoDeComprasService implements Serializable {

	private static final long serialVersionUID = -8316801243607929061L;

	private PedidoDAO pedidoDAO = new PedidoDAO();
	public int totalDeProdutos(List<ItensPedido> itensPedidos) {
		return itensPedidos.size();
	}

	public void finalizarPedido(Pedido pedido) {
		pedidoDAO.save(pedido);
	}

	
}
