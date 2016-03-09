package com.softserve.beans.converter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.softserve.manager.AuthorManager;
import com.softserve.model.Author;

@ManagedBean
@ViewScoped
public class AuthorsConverter implements Converter, Serializable {

	private static final long serialVersionUID = 2389663994853057254L;
	
	@EJB
	private AuthorManager authorManager;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		if (arg2 == null || arg2.isEmpty())
			return null;

		return authorManager.findById(Integer.valueOf(arg2).intValue());

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		if (!(arg2 instanceof Author))
			return null;

		return String.valueOf(((Author) arg2).getAuthorId());
	}
}