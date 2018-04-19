package br.com.joao.lojavirtual3.model;

import java.io.Serializable;

import javax.persistence.*;

import br.com.joao.lojavirtual3.util.jpa.PrimaryKeyTest;

@Entity 
@Table(name = "produto")
public class Produto implements PrimaryKeyTest, Serializable{

	private static final long serialVersionUID = -8023482526632579452L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id")
	private long id;

	@Column(name = "pro_nome", length = 60, nullable = false)
	private String nome;

	@Column(name = "pro_preco", length = 9, precision = 2, nullable = false)
	private float preco;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getPreco() {
		return preco;
	}
	
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public boolean temChavePrimaria() {
		return getId() > 0;
	}

}
