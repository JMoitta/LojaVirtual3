package br.com.joao.lojavirtual3.util.redirect;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class RedirecionaPaginaPadrao implements AuthenticationSuccessHandler{

//	private Seguranca seguranca = new Seguranca();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if(roles.contains("ROLE_ADMINISTRADOR")) {
			response.sendRedirect("/LojaVirtual3/pages/admin/principal.xhtml");
		} else if(roles.contains("ROLE_FUNCIONARIO")) {
			response.sendRedirect("/LojaVirtual3/index.xhtml");
		} else if(roles.contains("ROLE_CLIENTE")) {
			response.sendRedirect("/LojaVirtual3/index.xhtml");
		}
	}

}
