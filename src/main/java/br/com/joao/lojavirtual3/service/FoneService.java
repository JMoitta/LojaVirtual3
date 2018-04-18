package br.com.joao.lojavirtual3.service;

import java.io.Serializable;
import java.util.List;

import br.com.joao.lojavirtual3.dao.FoneDAO;
import br.com.joao.lojavirtual3.model.Fone;

public class FoneService implements Serializable {

	private static final long serialVersionUID = -8316801243607929061L;

	private FoneDAO foneDAO = new FoneDAO();

	public Fone inicializarObjeto() {
		Fone fone = new Fone();
		return fone;
	}
	
	public Fone buscarFonePorId(long foneId) {
		return foneDAO.buscarPorId(Fone.class, foneId);
	}

	public void salvarPassandoFone(Fone fone) throws Exception {
		try {
				getFoneDAO().merge(fone);
 		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void deletarPassandoFone(Fone fone) throws Exception {
		try {
			getFoneDAO().delete(fone);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<Fone> listaDeTodasFones() throws Exception {
		try {
			return getFoneDAO().selecioneTodos(Fone.class);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public FoneDAO getFoneDAO() {
		return foneDAO;
	}
}
