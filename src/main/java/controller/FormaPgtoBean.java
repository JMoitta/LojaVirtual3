package controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.FormaPgto;
import persistencia.FormaPgtoDAO;

@ManagedBean
@SessionScoped
public class FormaPgtoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FormaPgto formaPgto;
	
	public FormaPgto getFormaPgto() {
		if(formaPgto == null) {
			formaPgto = new FormaPgto();
		}
		return formaPgto;
	}
	
	public void setFormaPgto(FormaPgto formaPgto) {
		this.formaPgto = formaPgto;
	}
	
	public List<FormaPgto> getListagem() {
		return FormaPgtoDAO.listagem("");
	}
	
	public String actionGravar(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(formaPgto.getId() == 0) {
			FormaPgtoDAO.inserir(formaPgto);
			context.addMessage(null,new FacesMessage("Sucesso", "Inserido com sucesso!"));
		} else {
			FormaPgtoDAO.alterar(formaPgto);
			context.addMessage(null,new FacesMessage("Sucesso", "Alterado com sucesso!"));
		}
		return "/pages/formaPgto/administrarFormaPgto?faces-redirect=true";
	}
	public String actionInserir() {
		formaPgto = new FormaPgto();
		return "/pages/formaPgto/cadastroDeFormaPgto?faces-redirect=true";
	}
	
	public String actionExcluir(FormaPgto formaPgto){
		FormaPgtoDAO.excluir(formaPgto);
		return "/pages/formaPgto/administrarFormaPgto?faces-redirect=true";
	}
	
	public String actionAlterar(FormaPgto formaPgto){
		FormaPgtoDAO.alterar(formaPgto);
		return "/pages/formaPgto/cadastroDeFormaPgto?faces-redirect=true";
	}
	
}
