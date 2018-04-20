package br.com.joao.lojavirtual3.util.service;

import java.util.Date;

import br.gov.go.agr.ouvidoria.exception.NegocioException;

public abstract class ServiceAbstract {

	public void vibializaDataInicial(Date dataInicial) {
		if (dataInicial == null) throw new NegocioException("Data Inicial OBRIGATÓRIA!");
		else
			return dataInicial;
	}
}
