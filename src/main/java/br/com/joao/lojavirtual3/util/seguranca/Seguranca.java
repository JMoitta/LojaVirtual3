package br.com.joao.lojavirtual3.util.seguranca;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

import br.com.joao.lojavirtual3.model.Pessoa;
import br.com.joao.lojavirtual3.service.PessoaService;

@ManagedBean
@RequestScoped
public class Seguranca {

	PessoaService pessoaService = new PessoaService();
	private Pessoa pessoa = null;
	
	public Pessoa getUsuario() {
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && this.pessoa == null) {
			User user = (User) auth.getPrincipal();
			try {
				pessoa = pessoaService.buscarPessoaPorEmail(user.getUsername());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return this.pessoa;
	}
}
