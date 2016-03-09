package com.softserve.bean;

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
import com.softserve.model.Status;

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
	private Book currentBook = new Book();
	private List<Author> authors = null;
	private List<Book> books = null;
	private List<Review> reviews = null;

	private List<Book> topBooks = null;

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
		List<Book> selectedBooks = new ArrayList<Book>();
		for (Book book : books) {
			if (book.isSelected()) {
				authorManager.recalcRatingOndelete(book);
				selectedBooks.add(book);
			}
		}
		bookManager.removeBooks(selectedBooks);
	}

	public void selectAll() {
		LOGGER.info("selectAll()");
		if (select == true) {
			for (Book book : books)
				book.setSelected(true);
		} else {
			for (Book book : books) {
				book.setSelected(false);
			}
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
		LOGGER.info("initBooks");
		books = bookManager.findAllBooks();
	}

	public void createBook() {
		LOGGER.info("createBook()");
		bookManager.create(newBook);
		newBook = new Book();
	}

	public void initCurrentBook(Integer id) {
		LOGGER.info("initCurrentBook({})", id);
		currentBook = bookManager.findById(id);
	}

	public void initCurrentBook() {
		currentBook = bookManager.findById(getId());
	}

	public void initAuthors() {
		LOGGER.info("initAuthors");
		if (authors == null)
			authors = authorManager.findAllAuthors();
	}

	public void loadData() {
		LOGGER.info("loadData()");
		currentBook = bookManager.findById(getId());
		reviews = reviewManager.findReviewsByBookId(getId());
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
		authorManager.calculateAuthorRate(currentBook);
		bookManager.update(currentBook);
		refreshRating();
	}

	public String removeBook() {
		LOGGER.info("removeBook()");
		authorManager.recalcRatingOndelete(currentBook);
		bookManager.removeByPk(currentBook.getBookId());
		return "booklist";
	}

	public void updateReview() {
		LOGGER.info("updateReview()");
		reviewManager.update(currentReview);
		refreshRating();
		currentBook = bookManager.findById(getId());
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
		newReview = new Review();
		refreshRating();
	}

	private void refreshRating() {
		bookManager.calculateBookRate(bookManager.findById(currentBook.getBookId()));
		authorManager.calculateAuthorRate(bookManager.findById(currentBook.getBookId()));
	}

	@SuppressWarnings("unchecked")
	public void isExistAuthors(FacesContext context, UIComponent comp, Object value) {
		LOGGER.info("isExistAuthors");

		List<Author> authors = (List<Author>) value;
		if (authors.isEmpty()) {
			((UIInput) comp).setValid(false);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Book must have authors", null);
			context.addMessage(comp.getClientId(context), message);

		} else {
			LOGGER.info("Book has authors");
		}
	}

	public void clearBook() {
		LOGGER.info("clearBook");
		newBook = new Book();

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
		LOGGER.info("getBooks");
		initBooks();
		if (select) {
			for (Book book : books) {
				book.setSelected(true);
			}
		}
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
		newBook.setStatus(Status.AVAILABLE);
		return newBook;
	}

	public void setNewBook(Book newBook) {
		this.newBook = newBook;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
