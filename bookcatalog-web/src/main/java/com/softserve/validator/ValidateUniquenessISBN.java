package com.softserve.validator;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.manager.BookManager;
import com.softserve.model.Book;

@ManagedBean
@ViewScoped
public class ValidateUniquenessISBN implements Serializable {

	private static final long serialVersionUID = 732603816191448826L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateUniquenessISBN.class);

	@EJB
	private BookManager bookManager;

	private String currentISBN;

	// when we update book we need exclude current isbn in validation
	public void getISBN() {
		currentISBN = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("isbn");
	}

	public void isExistISBN(FacesContext context, UIComponent comp, Object value) {
		LOGGER.info("isExistISBN");

		String isbn = (String) value;
		Book book = null;

		if (!(isbn.equals(currentISBN))) {
			book = bookManager.findBookByISBN(isbn);
		}
		if (book != null) {
			((UIInput) comp).setValid(false);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ISBN not unique", null);
			context.addMessage(comp.getClientId(context), message);

		} else {
			LOGGER.info("unique isbn");
		}
	}
}
