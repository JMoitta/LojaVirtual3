package br.com.joao.lojavirtual3.service;

import javax.faces.application.FacesMessage;

import br.com.joao.lojavirtual3.dao.PessoaDAO;
import br.com.joao.lojavirtual3.exception.NegocioException;
import br.com.joao.lojavirtual3.model.Pessoa;
import br.com.joao.lojavirtual3.model.enums.TipoPessoaEnum;

public class LoginRegisterService {

	public PessoaDAO pessoaDAO = new PessoaDAO();
	public PessoaService pessoaService = new PessoaService();
	public void cadastroDeCliente(Pessoa pessoa) throws NegocioException{
		try {
			if(!pessoaService.existeUsuario(pessoa)) {
				pessoa.setTipoPessoaEnum(TipoPessoaEnum.ROLE_CLIENTE);
				pessoaDAO.save(pessoa);
				throw new NegocioException(FacesMessage.SEVERITY_INFO, "Cadastro:", "Cadastro realizado com sucesso!");
			} else {
				throw new NegocioException(FacesMessage.SEVERITY_WARN, "Cadastro:", "E-mail ja foi ultilizadado!");
			}
		} catch (NegocioException e) {
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
