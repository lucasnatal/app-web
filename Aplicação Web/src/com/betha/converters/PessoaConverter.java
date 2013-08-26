package com.betha.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.betha.business.Pessoa;
import com.betha.daos.PessoaDAO;


public class PessoaConverter implements Converter {
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			PessoaDAO pessoaDao = new PessoaDAO();
			return pessoaDao.buscarPorCodigo(new Integer(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null)
			return ((Pessoa) value).getCodigo().toString();
		return null;
	}

}
