package br.com.joao.lojavirtual3.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.joao.lojavirtual3.model.Produto;
import br.com.joao.lojavirtual3.service.ProdutoService;

@FacesConverter(value = "ProdutoConverter")
public class ProdutoConverter implements Converter {

	private ProdutoService produtoService = new ProdutoService();
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto produto = null;
		try{
			produto = produtoService.buscarProdutoPorId(new Long(value));
		} catch (Exception e){
			e.printStackTrace();
		}
		return produto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		return null;
	}


}
