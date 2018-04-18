package br.com.joao.lojavirtual3.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.joao.lojavirtual3.model.Pessoa;
import br.com.joao.lojavirtual3.service.PessoaService;

@FacesConverter(value = "PessoaConverter")
public class PessoaConverter implements Converter {

	private PessoaService pessoaService = new PessoaService();
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa pessoa = null;
		try{
			pessoa = pessoaService.buscarPessoaPorId(new Long(value));
		} catch (Exception e){
			e.printStackTrace();
		}
		return pessoa;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		return null;
	}


}
