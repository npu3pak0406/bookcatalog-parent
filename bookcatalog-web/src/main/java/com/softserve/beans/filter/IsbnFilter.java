package com.softserve.beans.filter;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import com.softserve.model.Book;

@ManagedBean
@RequestScoped
public class IsbnFilter implements Serializable {

	private static final long serialVersionUID = -2477896495893340750L;

	private String isbnFilter;

	public boolean getIsbnFilterImpl(Object book) {
		Boolean result = false;
		Book currentBook = (Book) book;
		if (StringUtils.isEmpty(isbnFilter)) {
			result = true;
		} else {
			result = StringUtils.containsIgnoreCase(currentBook.getIsbn(), isbnFilter);
		}
		return result;
	}

	public String getIsbnFilter() {
		return isbnFilter;
	}

	public void setIsbnFilter(String isbnFilter) {
		this.isbnFilter = isbnFilter;
	}

}
