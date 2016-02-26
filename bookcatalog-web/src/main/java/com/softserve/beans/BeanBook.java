package com.softserve.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.manager.AuthorManager;
import com.softserve.manager.BookManager;
import com.softserve.manager.ReviewManager;
import com.softserve.model.Author;
import com.softserve.model.Book;
import com.softserve.model.Review;

@ManagedBean
@ViewScoped
@Transactional
public class BeanBook implements Serializable {

	private static final long serialVersionUID = -7816666732933109557L;
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanBook.class);

	@EJB
	private BookManager bookManager;
	@EJB
	private AuthorManager authorManager;
	@EJB
	private ReviewManager reviewManager;

	private Book newBook = new Book();
	private Book currentBook = null;
	private List<Author> authors = null;
	private List<Book> books = null;

	private List<Book> topBooks = null;

	private List<Review> bookReviews = null;
	private Review newReview = new Review();
	private Review currentReview = null;

	private Integer id;
	private Boolean select = new Boolean(false);

	public BeanBook() {
	}

	public List<Book> findTopBooks() {
		if (topBooks == null) {
			topBooks = new ArrayList<Book>();
		}
		topBooks = bookManager.findTopBooks();
		return topBooks;
	}

	public void removeSelected() {
		LOGGER.info("removeSelected()");
		List<Integer> selectedBooksId = new ArrayList<Integer>();
		for (Book book : books) {
			if (book.isSelected()) {
				selectedBooksId.add(book.getBookId());
			}
		}
		bookManager.removeAllByPk(selectedBooksId);
	}

	public void selectAll() {
		LOGGER.info("selectAll()");
		if (select == true)
			for (Book book : books)
				book.setSelected(true);
		else {
			for (Book book : books)
				book.setSelected(false);
		}
	}

	public List<Book> selectedBooks() {
		List<Book> selectedbooks = new ArrayList<Book>();
		for (Book book : books) {
			if (book.isSelected()) {
				selectedbooks.add(book);
			}
		}
		return selectedbooks;
	}

	public void initBooks() {
		books = bookManager.returnAllBooks();
	}

	public void createBook() {
		LOGGER.info("createBook()");
		bookManager.create(newBook);
		newBook = new Book();
	}

	public void initCurrentBook(Integer id) {
		currentBook = bookManager.findBookById(id);
	}

	public void initAuthors() {
		if (authors == null)
			LOGGER.info("initAuthors()");
		authors = authorManager.findAllAuthors();
	}

	public void loadData() {
		LOGGER.info("loadData()");
		currentBook = bookManager.findBookById(getId());
		authors = authorManager.findAllAuthors();
	}

	public Integer getId() {
		if (id == null)
			id = Integer.parseInt(
					FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
		return id;
	}

	public void initCurrentReview(Integer id) {
		LOGGER.info("initCurrentReview()" + "id:" + id);
		currentReview = reviewManager.findReviewById(id);
	}

	public void updateBook() {
		LOGGER.info("updateBook()");
		bookManager.update(currentBook);
		refreshRating();
	}

	public String removeBook() {
		LOGGER.info("removeBook()");
		bookManager.removeByPk(currentBook.getBookId());
		return "booklist";
	}

	public void updateReview() {
		LOGGER.info("updateReview()");
		reviewManager.update(currentReview);
		refreshRating();
		currentBook = bookManager.findBookById(getId());
	}

	public void removeReview() {
		LOGGER.info("removeReview()");
		reviewManager.removeByPk(currentReview.getReviewId());
		refreshRating();
	}

	public void saveReview() {
		LOGGER.info("saveReview()");
		newReview.setBook(currentBook);
		reviewManager.create(newReview);
		refreshRating();
		newReview = new Review();
	}

	private void refreshRating() {
		bookManager.calculateBookRate(bookManager.findBookById(currentBook.getBookId()));
		authorManager.calculateAuthorRate(bookManager.findBookById(currentBook.getBookId()));
	}

	@SuppressWarnings("unchecked")
	public void isExistAuthors(FacesContext context, UIComponent comp, Object value) {
		LOGGER.info("isExistAuthors");

		List<Author> authors = (List<Author>) value;
		if (authors.isEmpty()) {
			((UIInput) comp).setValid(false);

			FacesMessage message = new FacesMessage("Book must have authors");
			context.addMessage(comp.getClientId(context), message);

		} else {
			LOGGER.info("book has authors");
		}
	}

	// GETTERS AND SETTERS

	public Book getCurrentBook() {
		return currentBook;
	}

	public void setCurrentBook(Book currentBook) {
		this.currentBook = currentBook;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Review> getBookReviews() {
		return bookReviews;
	}

	public void setBookReviews(List<Review> bookReviews) {
		this.bookReviews = bookReviews;
	}

	public Review getCurrentReview() {
		return currentReview;
	}

	public void setCurrentReview(Review currentReview) {
		this.currentReview = currentReview;
	}

	public List<Book> getTopBooks() {
		return topBooks;
	}

	public Integer getBookSize() {
		return bookManager.findBookDataSize();
	}

	public void setTopBooks(List<Book> topBooks) {
		this.topBooks = topBooks;
	}

	public Review getNewReview() {
		return newReview;
	}

	public void setNewReview(Review newReview) {
		this.newReview = newReview;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Book> getBooks() {
		initBooks();
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

	public Book getNewBook() {
		return newBook;
	}

	public void setNewBook(Book newBook) {
		this.newBook = newBook;
	}

}
