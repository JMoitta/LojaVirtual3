package br.com.joao.lojavirtual3.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.joao.lojavirtual3.util.jpa.PrimaryKeyTest;

@Entity
@Table(name = "itens_pedido")
public class ItensPedido implements Serializable, PrimaryKeyTest {

	private static final long serialVersionUID = -8023482526632579452L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ipe_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "ped_id")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "pro_id")
	private Produto produto;

	@Column(name = "ipe_qtd")
	private float qtd;

	@Column(name = "ipe_valorUnit")
	private float valorUnit;

	@Column(name = "ipe_subtotal")
	private float subtotal;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public float getQtd() {
		return qtd;
	}

	public void setQtd(float qtd) {
		this.qtd = qtd;
	}

	public float getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(float valorUnit) {
		this.valorUnit = valorUnit;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public boolean temChavePrimaria() {
		return getId() > 0;
	}
}
