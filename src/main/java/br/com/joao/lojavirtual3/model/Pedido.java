package br.com.joao.lojavirtual3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.joao.lojavirtual3.util.jpa.PrimaryKeyTest;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable, PrimaryKeyTest {

	private static final long serialVersionUID = -8023482526632579452L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ped_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "fpg_id")
	private FormaPgto formaPgto;

	@Column(name = "ped_dataEmissao")
	@Temporal(value = TemporalType.DATE)
	private Date dataEmissao;

	@Column(name = "ped_dataAutorizacao")
	@Temporal(value = TemporalType.DATE)
	private Date dataAutorizacao;

	@Column(name = "ped_total")
	private float total;

	@Column(name = "ped_desconto")
	private float desconto;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItensPedido> itensPedidos = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public FormaPgto getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getDesconto() {
		return desconto;
	}

	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}

	public List<ItensPedido> getItensPedidos() {
		return itensPedidos;
	}

	public void setItensPedidos(List<ItensPedido> itensPedidos) {
		this.itensPedidos = itensPedidos;
	}

	@Override
	public boolean temChavePrimaria() {
		return getId() > 0;
	}
}
