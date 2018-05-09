package br.com.joao.lojavirtual3.util.service;

import java.util.Date;

import br.com.joao.lojavirtual3.exception.NegocioException;
import br.com.joao.lojavirtual3.util.date.DateUtil;


public abstract class ServiceAbstract {

	public Date vibializaDataInicial(Date dataInicial) throws NegocioException  {
		if (dataInicial == null)
			throw new NegocioException("Data Inicial OBRIGATÓRIA!");
		else
			return dataInicial;
	}

	public Date vibializaDataFinal(Date dataInicial, Date dataFinal) throws NegocioException {
		if (DateUtil.dataFinalEAntesDaDataInicial(dataInicial, dataFinal))
			throw new NegocioException("Data Inicial anterior a Data Final OBRIGATÓRIA!");
		else
			return dataFinal;
	}
}
