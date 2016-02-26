package com.softserve.validator;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.manager.BookManager;
import com.softserve.model.Book;

@ManagedBean
@RequestScoped
public class ValidateUniquenessISBN {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateUniquenessISBN.class);

	@EJB
	private BookManager bookManager;

	private String currentISBN;

	public void getISBN() {
		if (currentISBN == null)
			currentISBN = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("isbn");
	}

	public void isExistISBN(FacesContext context, UIComponent comp, Object value) {
		LOGGER.info("isExistISBN");

		String isbn = (String) value;
		Book book = null;

		getISBN();

		if (isbn != currentISBN) {
			book = bookManager.findBookByISBN(isbn);
		}
		if (book != null) {
			((UIInput) comp).setValid(false);

			FacesMessage message = new FacesMessage("ISBN not unique");
			context.addMessage(comp.getClientId(context), message);

		} else {
			LOGGER.info("unique isbn");
		}
	}
}
