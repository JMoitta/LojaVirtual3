package br.com.joao.lojavirtual3.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.joao.lojavirtual3.model.Pessoa;
import br.com.joao.lojavirtual3.model.Produto;
import br.com.joao.lojavirtual3.util.jpa.DAOAbstract;

public class ProdutoDAO extends DAOAbstract<Produto> implements Serializable{

	private static final long serialVersionUID = -4450760422642399410L;

	public Produto buscarPessoaPorId(Long contaId) {
		StringBuilder condicao = new StringBuilder();
		Map<String, Object> paramentros = new HashMap<>();

		condicao.append("SELECT t FROM Produto t WHERE t.id = :id");
		paramentros.put("id", contaId);
		
		return buscarPor(condicao.toString(), paramentros);
	}
}
