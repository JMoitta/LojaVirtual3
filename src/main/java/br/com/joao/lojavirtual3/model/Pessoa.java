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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.joao.lojavirtual3.util.jpa.PrimaryKeyTest;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable, PrimaryKeyTest{

	private static final long serialVersionUID = -8023482526632579452L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pes_id")
	private long id;

	@Column(name = "pes_nome", length = 60, nullable = false)
	private String nome;

	@Column(name = "pes_cpf", length = 14, nullable = false)
	private String cpf;

	@Column(name = "pes_rg", length = 20, nullable = false)
	private String rg;

	@Column(name = "pes_data_nasc")
	@Temporal(value = TemporalType.DATE)
	private Date dataNasc;

	@Column(name = "pes_rua", length = 60)
	private String rua;

	@Column(name = "pes_bairro", length = 30)
	private String bairro;

	@Column(name = "pes_cidade", length = 30)
	private String cidade;

	@Column(name = "pes_uf", length = 2)
	private String uf;

	@Column(name = "pes_cep")
	private int cep;

	@Column(name = "pes_email", length = 40)
	private String email;

	@Column(name = "pes_senha", length = 32)
	private String senha;

	@Column(name = "pes_tipo", length = 30)
	private String tipo;// Administrador, cliente, funcionario
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Fone> fones = new ArrayList<>();

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
		Pessoa other = (Pessoa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		String consulta = "SELECT pessoa FROM Pessoa pessoa WHERE pessoa.tipo = 'Administrador' AND pessoa.pedidos.id IN ";
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Fone> getFones() {
		return fones;
	}

	public void setFones(List<Fone> fones) {
		this.fones = fones;
	}

	@Override
	public boolean temChavePrimaria() {
		return getId() > 0;
	}

	
}
