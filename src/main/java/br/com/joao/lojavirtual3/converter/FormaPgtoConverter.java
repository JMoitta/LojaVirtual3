package br.com.joao.lojavirtual3.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.joao.lojavirtual3.model.FormaPgto;
import br.com.joao.lojavirtual3.service.FormaPgtoService;

@FacesConverter(value = "FormaPgtoConverter")
public class FormaPgtoConverter implements Converter {

	private FormaPgtoService formaPgtoService = new FormaPgtoService();
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		FormaPgto formaPgto = null;
		try{
			formaPgto = formaPgtoService.buscarFormaPgtoPorId(new Long(value));
		} catch (Exception e){
			e.printStackTrace();
		}
		return formaPgto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return Long.toString(((FormaPgto) value).getId());
	}


}
