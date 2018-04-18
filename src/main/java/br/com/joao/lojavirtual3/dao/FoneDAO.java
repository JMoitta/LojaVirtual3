package br.com.joao.lojavirtual3.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.joao.lojavirtual3.model.Fone;
import br.com.joao.lojavirtual3.model.Pessoa;
import br.com.joao.lojavirtual3.util.jpa.DAOAbstract;

public class FoneDAO extends DAOAbstract<Fone> implements Serializable{

	private static final long serialVersionUID = -4450760422642399410L;

	public Fone buscarFonePorId(Long foneId) {
		StringBuilder condicao = new StringBuilder();
		Map<String, Object> paramentros = new HashMap<>();

		condicao.append("SELECT f FROM Fone f WHERE f.id = :id");
		paramentros.put("id", foneId);
		
		return buscarPor(condicao.toString(), paramentros);
	}
}
