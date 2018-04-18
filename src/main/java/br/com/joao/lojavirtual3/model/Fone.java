package br.com.joao.lojavirtual3.model;

import java.io.Serializable;

import javax.persistence.*;

import br.com.joao.lojavirtual3.util.jpa.PrimaryKeyTest;

@Entity
@Table(name = "fone")
public class Fone implements Serializable, PrimaryKeyTest {

	private static final long serialVersionUID = -8023482526632579452L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fon_id")
	private int id;

	@Column(name = "fon_numero", length = 20, nullable = false)
	private String numero;

	@Column(name = "fon_descricao", length = 30, nullable = false)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;

	@Transient
	private int pessoaId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Fone other = (Fone) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public boolean temChavePrimaria() {
		return getId() > 0;
	}

}
