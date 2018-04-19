package br.com.joao.lojavirtual3.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.joao.lojavirtual3.model.FormaPgto;
import br.com.joao.lojavirtual3.util.jpa.DAOAbstract;

public class FormaPgtoDAO extends DAOAbstract<FormaPgto> implements Serializable{

	private static final long serialVersionUID = -4450760422642399410L;

	public FormaPgto buscarFonePorId(Long foneId) {
		StringBuilder condicao = new StringBuilder();
		Map<String, Object> paramentros = new HashMap<>();

		condicao.append("SELECT t FROM FormaPgto t WHERE t.id = :id");
		paramentros.put("id", foneId);
		
		return buscarPor(condicao.toString(), paramentros);
	}
}
