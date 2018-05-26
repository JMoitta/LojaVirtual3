package br.com.joao.lojavirtual3.service;

import java.io.Serializable;
import java.util.List;

import br.com.joao.lojavirtual3.dao.FormaPgtoDAO;
import br.com.joao.lojavirtual3.model.FormaPgto;

public class FormaPgtoService implements Serializable {

	private static final long serialVersionUID = -8316801243607929061L;

	private FormaPgtoDAO formaPgtoDAO = new FormaPgtoDAO();

	public FormaPgto inicializarObjeto() {
		FormaPgto formaPgto = new FormaPgto();
		return formaPgto;
	}
	
	public FormaPgto buscarFormaPgtoPorId(long formaPgtoId) {
		return formaPgtoDAO.buscarPorId(FormaPgto.class, formaPgtoId);
	}

	public void salvarPassandoFormaPgto(FormaPgto formaPgto) throws Exception {
		try {
				getFormaPgtoDAO().merge(formaPgto);
 		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void deletarPassandoFormaPgto(FormaPgto formaPgto) throws Exception {
		try {
			getFormaPgtoDAO().delete(formaPgto);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<FormaPgto> listaDeTodasFormaPgtos() throws Exception {
		try {
			return getFormaPgtoDAO().selecioneTodos(FormaPgto.class);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public FormaPgtoDAO getFormaPgtoDAO() {
		return formaPgtoDAO;
	}

	public FormaPgto formaPgtoPadrao() {
		return this.buscarFormaPgtoPorId(01l);
	}
}
