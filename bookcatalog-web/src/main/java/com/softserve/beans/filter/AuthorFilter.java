package com.softserve.beans.filter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.lang3.StringUtils;

import com.softserve.manager.BookManager;
import com.softserve.model.Author;
import com.softserve.model.Book;

@ManagedBean
@RequestScoped
public class AuthorFilter implements Serializable {
	private static final long serialVersionUID = -3725901188975943276L;

	@EJB
	private BookManager bookManager;

	private String authorFilter;

	public boolean getFilterAuthorImpl(Object object) {

		Boolean result = false;

		if (object instanceof Book) {
			Book book = (Book) object;
			if (StringUtils.isEmpty(authorFilter)) {
				result = true;
			} else {
				for (Author author : book.getAuthors()) {
					result = StringUtils.containsIgnoreCase((author.getFirstName() + " " + author.getSecondName()),
							authorFilter);
					if (result) {
						break;
					}
				}
			}
			return result;

		} else if (object instanceof Author) {
			Author author = (Author) object;
			if (StringUtils.isEmpty(authorFilter)) {
				result = true;
			} else {
				result = StringUtils.containsIgnoreCase(
						(author.getFirstName() + " " + author.getSecondName()), authorFilter);
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
