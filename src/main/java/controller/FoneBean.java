package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import model.Fone;
import model.Pessoa;
import persistencia.FoneDAO;
import persistencia.PessoaDAO;

@ManagedBean
@SessionScoped
public class FoneBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fone fone;

	public List<Fone> getListagem() {
		return FoneDAO.listagem("");
	}

	public String actionGravar() {
		if(fone.getPessoaId() > 0) {
			fone.setPessoa(PessoaDAO.buscarPorId(fone.getPessoaId()));
		}
		if (fone.getId() == 0) {
			FoneDAO.inserir(fone);
		} else {
			FoneDAO.alterar(fone);
		}
		return "/pages/fone/administrarFones?faces-redirect=true";
	}

	public String actionInserir() {
		fone = new Fone();
		return "/pages/fone/cadastroDeFone?faces-redirect=true";
	}

	public String actionExcluir(Fone f) {
		FoneDAO.excluir(f);
		return "/pages/fone/administrarFones?faces-redirect=true";
	}

	public String actionAlterar(Fone f) {
		FoneDAO.alterar(f);
		return "/pages/fone/cadastroDeFone?faces-redirect=true";
	}

	public List<SelectItem> selectPessoa(Fone fone) {
		List<SelectItem> listaSelectItem = new ArrayList<>();
		List<Pessoa> listaPessoa;
		SelectItem itemDefault, aa;

		listaPessoa = PessoaDAO.listagem("");
		aa = new SelectItem();
		aa.setLabel("---Selecione Apenas Um---");
		aa.setDisabled(Boolean.TRUE);
		aa.setEscape(Boolean.FALSE);
		listaSelectItem.add(aa);

		for (Pessoa pessoa : listaPessoa) {
			itemDefault = new SelectItem();
			itemDefault.setEscape(Boolean.TRUE);
			itemDefault.setLabel(pessoa.getNome() + " - " + pessoa.getCpf());
			itemDefault.setValue(pessoa.toString());
			listaSelectItem.add(itemDefault);
		}
		return listaSelectItem;
	}

	public Fone getFone() {
		if (fone == null) {
//			fone = new Fone();
		}
		System.out.println("fone: " + fone.getId());
		return fone;
	}

	public void setFone(Fone fone) {
		this.fone = fone;
	}

}
