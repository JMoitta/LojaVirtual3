package br.com.joao.lojavirtual3.util.service;

import java.util.Date;

import br.com.joao.lojavirtual3.exception.NegocioException;

public abstract class ServiceAbstract {

	public Date vibializaDataInicial(Date dataInicial) throws NegocioException {
		if (dataInicial == null) throw new NegocioException("Data Inicial OBRIGATÓRIA!");
		else
			return dataInicial;
	}

	public void vibializaDataFinal(Date dataInicial, Date dataFinal) {
		// TODO Auto-generated method stub
		
	}
}
