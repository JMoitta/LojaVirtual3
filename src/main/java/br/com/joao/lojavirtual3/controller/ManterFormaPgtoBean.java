package br.com.joao.lojavirtual3.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.joao.lojavirtual3.model.FormaPgto;
import br.com.joao.lojavirtual3.service.FormaPgtoService;

@ManagedBean
@ViewScoped
public class ManterFormaPgtoBean implements Serializable {

	private static final long serialVersionUID = -5327540983341723093L;

	private FormaPgtoService formaPgtoService = new FormaPgtoService();
	
	private FormaPgto formaPgto = new FormaPgto();
	private List<FormaPgto> formaPgtos;

	public void abrirModalDeFormaPgto(FormaPgto formaPgto) {
		Map<String, Object> options = new HashMap<String, Object>();
		Map<String, List<String>> params = new HashMap<>();
		List<String> formaPgtoId;

		options.put("modal", true);
		options.put("width", "80%");
//		options.put("id", "modalDeFormaPgto");
//		options.put("styleClass", "jm-modal");
		options.put("height", 365);
		options.put("resizable", false);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");

		if (formaPgto != null) {
			formaPgtoId = new ArrayList<String>();
			formaPgtoId.add(formaPgto.getId() + "");
			params.put("formaPgtoId", formaPgtoId);
		}

		RequestContext.getCurrentInstance().openDialog("/pages/formaPgto/modal/cadastroDeFormaPgto", options, params);// params
	}

	public void mostrarMensagemSucesso() {
		this.carregarListaFormaPgto();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "A formaPgto foi salva com sucesso!"));
	}

	public void salvarFormaPgto() {
		RequestContext reqCon = RequestContext.getCurrentInstance();
		try {
			formaPgtoService.salvarPassandoFormaPgto(getFormaPgto());
			reqCon.closeDialog(getFormaPgto());
		} catch (Exception e) {
	        FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contato o administrador."));
		}
	}

	public void carregarListaFormaPgto() {
		try {
			formaPgtos = formaPgtoService.listaDeTodasFormaPgtos();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Não foi possivel listar."));
			formaPgtos = null;
		}
	}

	public void excluirFormaPgto(FormaPgto objeto) {
		try {
			formaPgtoService.deletarPassandoFormaPgto(objeto);
			carregarListaFormaPgto();
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "A formaPgto foi excluida!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
	        		null,
	        		new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Não foi possivel excluir."));
		}
	}
	
	//GETTERS AND SETTERS
	public FormaPgto getFormaPgto() {
		if(formaPgto == null){
			formaPgto = formaPgtoService.inicializarObjeto();
		}
		return formaPgto;
	}
	
	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}
	
	public List<FormaPgto> getFormaPgtos() {
		if (formaPgtos == null) {
			carregarListaFormaPgto();
		}
		return formaPgtos; 
	}
	
	public void setFormaPgtos(List<FormaPgto> formaPgtos) {
		this.formaPgtos = formaPgtos;
	}
	
}
