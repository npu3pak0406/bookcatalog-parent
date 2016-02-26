package com.softserve.beans.filter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.softserve.manager.BookManager;
import com.softserve.model.Author;
import com.softserve.model.Book;

@ManagedBean
@ViewScoped
public class AuthorFilter implements Serializable {
	private static final long serialVersionUID = -3725901188975943276L;

	@EJB
	private BookManager bookManager;
	
	private String authorFilter;

	public boolean getFilterAuthorImpl(Object book) {
		Boolean result = false;
		Book currentBook = (Book) book;
		if (StringUtils.isEmpty(authorFilter)) {
			result = true;
		} else {
			for (Author author : currentBook.getAuthors()) {
				result = StringUtils.containsIgnoreCase((author.getFirstName() + " " + author.getSecondName()),
						authorFilter);
				if (result) {
					break;
				}
			}
		}
		return result;
	}

	public String getAuthorFilter() {
		return authorFilter;
	}

	public void setAuthorFilter(String authorFilter) {
		this.authorFilter = authorFilter;
	}

}
