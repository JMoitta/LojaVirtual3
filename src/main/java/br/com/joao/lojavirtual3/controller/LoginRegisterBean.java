package br.com.joao.lojavirtual3.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.joao.lojavirtual3.exception.NegocioException;
import br.com.joao.lojavirtual3.model.FormaPgto;
import br.com.joao.lojavirtual3.model.ItensPedido;
import br.com.joao.lojavirtual3.model.Pedido;
import br.com.joao.lojavirtual3.model.Pessoa;
import br.com.joao.lojavirtual3.model.Produto;
import br.com.joao.lojavirtual3.service.CarrinhoDeComprasService;
import br.com.joao.lojavirtual3.service.FormaPgtoService;
import br.com.joao.lojavirtual3.service.LoginRegisterService;
import br.com.joao.lojavirtual3.util.seguranca.Seguranca;

@ManagedBean
@ViewScoped
public class LoginRegisterBean implements Serializable {

	private static final long serialVersionUID = -8999210582086851446L;
	
	private Pessoa pessoa;
	private LoginRegisterService loginRegisterService = new LoginRegisterService();
	
	public void cadastrarPessoa() {
		try {
			loginRegisterService.cadastroDeCliente(pessoa);
		} catch (NegocioException e) {
			e.todasAsMensagens();
		}
	}
	
	private void inicializarPessoa() {
		pessoa = new Pessoa();
	}

	public Pessoa getPessoa() {
		if(pessoa == null) {
			inicializarPessoa();
		}
		return pessoa;
	}
	
}
