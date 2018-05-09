package br.com.joao.lojavirtual3.controller;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.BarChartModel;

import br.com.joao.lojavirtual3.exception.NegocioException;
import br.com.joao.lojavirtual3.service.ProdutoService;

@ManagedBean
@ViewScoped
public class GraficoProdutoPorMesBean {

	// ATTRS
	private Date dataInicial;
	private Date dataFinal;
	private BarChartModel barModel;
	private ProdutoService produtoService = new ProdutoService();

	// LOADS
	private void carregarDataInicial() {
	}

	private void carregarDataFinal() {
		this.dataFinal = new Date();
	}

	public void buscarGraficoProdutoPorMes() {
		try {
			this.produtoService.contarDiferencaEntreAsDatas(this.dataInicial, this.dataFinal);
		} catch (NegocioException e) {
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(e.getSeverity(), e.getSummary(), e.getMessage()));
		}
	}
	private void carregarBarModel() {
		this.barModel = new BarChartModel();

		// this.barModel.setLegendPosition("w");
	}

	// GETTERS AND SETTERS
	public Date getDataInicial() {
		if (dataInicial == null) {
			carregarDataInicial();
		}
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		if (dataFinal == null) {
			carregarDataFinal();
		}
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public BarChartModel getBarModel() {
		if (barModel == null) {
			carregarBarModel();
		}
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
}
