package br.com.joao.lojavirtual3.controller;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

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
		
		;
		this.barModel.addSeries(produtoService.buscarDadosParaGraficoProdutoPorMes(
				produtoService.contarDiferencaEntreAsDatas(dataInicial, dataFinal),
				dataInicial, dataFinal));

		this.barModel.setTitle("produto por mês");
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
