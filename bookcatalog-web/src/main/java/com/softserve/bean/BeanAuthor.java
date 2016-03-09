package com.softserve.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.manager.AuthorManager;
import com.softserve.manager.BookManager;
import com.softserve.model.Author;
import com.softserve.model.Book;

@ManagedBean
@ViewScoped
public class BeanAuthor implements Serializable {

	private static final long serialVersionUID = -6635971397974265634L;

	@EJB
	private AuthorManager authorManager;
	@EJB
	private BookManager bookManager;

	private List<Author> authors = new ArrayList<Author>();;
	private List<Book> books = new ArrayList<Book>();
	private Author currentAuthor = null;
	private Author newAuthor = new Author();
	private Integer bookCount;
	private Integer id;

	private Boolean select = new Boolean(false);

	private static final Logger LOGGER = LoggerFactory.getLogger(BeanAuthor.class);

	public void loadData() {
		LOGGER.info("loadData()");
		currentAuthor = authorManager.findById(getId());
	}

	public Integer getId() {
		if (id == null)
			id = Integer.parseInt(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
		return id;
	}

	public void initCurrentAuthor(Integer id) {
		LOGGER.info("initCurrentAuthor({})", id);
		currentAuthor = authorManager.findById(id);
	}

	public void initBooks() {
		LOGGER.info("initBooks()");
		books = bookManager.findAllBooks();
	}

	public void initAuthors() {
		LOGGER.info("initAuthors()");
		authors = authorManager.findAllAuthors();
	}
	
	public void updateAuthor() {
		LOGGER.info("updateAuthor()");
		authorManager.update(currentAuthor);
	}

	public void createAuthor() {
		LOGGER.info("createAuthor");
		authorManager.create(newAuthor);
		newAuthor = new Author();
	}

	public String removeAuthor() {
		LOGGER.info("removeAuthor");
		authorManager.removeById(currentAuthor.getAuthorId());
		return "authorlist";
	}
	
	public List<Author> getAuthors() {
		initAuthors();
		if (select) {
			for (Author author : authors) {
				author.setSelected(true);
			}
		}
		return authors;
	}

	public void removeSelected() {
		List<Author> selectedAuthors = new ArrayList<Author>();
		LOGGER.info("removeSelected: {}", selectedAuthors);
		for (Author author : authors) {
			if (author.isSelected()) {
				selectedAuthors.add(author);
			}
		}
		authorManager.removeAuthors(selectedAuthors);
	}

	public Boolean checkAuthorRemove() {
		bookCount = 0;
		Integer selectedAuthors = 0;
		for (Author author : authors) {
			if (author.isSelected()) {
				selectedAuthors++;
				bookCount += author.getBooks().size();
			}
		}
		if (bookCount == 0 && selectedAuthors != 0) {
			return false;
		} else
			return true;
	}

	public List<Author> selectedAuthors() {
		List<Author> selectedAuthors = new ArrayList<Author>();
		for (Author author : authors) {
			if (author.isSelected()) {
				selectedAuthors.add(author);
			}
		}
		return selectedAuthors;
	}

	public void selectAll() {
		if (select == true)
			for (Author author : authors) {
				author.setSelected(true);
			}
		else {
			for (Author author : authors) {
				author.setSelected(false);
			}
		}
	}

	public Author getCurrentAuthor() {
		return currentAuthor;
	}

	public void setCurrentAuthor(Author currentAuthor) {
		this.currentAuthor = currentAuthor;
	}

	public Author getNewAuthor() {
		return newAuthor;
	}

	public void setNewAuthor(Author newAuthor) {
		this.newAuthor = newAuthor;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Boolean getSelect() {
		return select;
	}

	public void setSelect(Boolean select) {
		this.select = select;
	}

	public Integer getAuthorsSize() {
		return authorManager.countAllAuthors();
	}

	public Integer getBookCount() {
		return bookCount;
	}

	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}

}
