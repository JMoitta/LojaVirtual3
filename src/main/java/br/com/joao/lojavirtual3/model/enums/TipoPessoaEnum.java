package br.com.joao.lojavirtual3.model.enums;

public enum TipoPessoaEnum {

	ROLE_ADMINISTRADOR(1, "Administrador", "Usuário com funções de administrador."),
	ROLE_FUNCIONARIO(1, "Funcionario", "Usuário com funções de funcionario."),
	ROLE_CLIENTE(1, "Cliente", "Usuário que e um cliente.");
	
	private long id;
	private String role;
	private String description;

	private TipoPessoaEnum(long id, String role, String description) {
		this.id = id;
		this.role = role;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format(
				"TipoPessoa {id = '%s', role = '%s', description = '%s'}",
				getId(), getRole(), getDescription());
	}
}
