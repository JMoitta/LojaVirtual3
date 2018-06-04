package br.com.joao.lojavirtual3.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.joao.lojavirtual3.model.Pessoa;
import br.com.joao.lojavirtual3.service.PessoaService;
import br.com.joao.lojavirtual3.util.seguranca.Seguranca;

@ManagedBean
@ViewScoped
public class ConfiguracoesBean {

	private PessoaService pessoaService = new PessoaService();
	private Pessoa pessoa;
	private Seguranca seguranca = new Seguranca();
	public Pessoa getPessoa() {
		if(pessoa == null) {
			try {
				pessoa = pessoaService.buscarPessoaPorEmail(seguranca.getUsuario());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pessoa;
	}
	
	
}
