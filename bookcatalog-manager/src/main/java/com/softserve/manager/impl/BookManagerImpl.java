package com.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.AuthorDAO;
import com.softserve.dao.BookDAO;
import com.softserve.manager.BookManager;
import com.softserve.model.Author;
import com.softserve.model.Book;
import com.softserve.model.Review;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BookManagerImpl implements BookManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookManagerImpl.class);
	private static final Double ZERO = 0.0;

	@EJB
	private BookDAO bookDAO;

	@EJB
	private AuthorDAO authorDAO;

	public BookManagerImpl() {
	}

	public List<Book> getAllBooksByAuthor(Author a) {
		LOGGER.info("getAllBooksByAuthor(Author {})", a);
		return bookDAO.findAllBooksByAuthor(a);
	}

	@Override
	public List<Book> returnAllBooks() {
		LOGGER.info("start method returnAllBooks()");
		return bookDAO.findAllBooks();
	}

	public List<Book> getAllAvailableBooks() {
		LOGGER.info("start method getAllAvailableBooks()");
		return bookDAO.findAllAvailableBooks();
	}

	@Override
	public List<Book> getBooksWithRating(Double rating) {
		LOGGER.info("start getBooksWithRating(Integer {})", rating);
		return bookDAO.findBooksWithRating(rating);
	}

	@Override
	public Book findBookById(Integer id) {
		LOGGER.info("start findBookById(Integer {})", id);
		return bookDAO.findBookById(id);
	}

	@Override
	public void create(Book book) {
		LOGGER.info(" void create(Book {})", book);
		bookDAO.create(book);
	}

	@Override
	public void update(Book book) {
		LOGGER.info("start update(Book {})", book);
		bookDAO.update(book);
	}

	@Override
	public List<Book> findBooks(int pageNumber, int pageSize) {
		LOGGER.info("findBooks(int {}, int {})", pageNumber, pageSize);
		return bookDAO.findBooks(pageNumber, pageSize);
	}

	@Override
	public void removeByPk(Integer id) {
		LOGGER.info("removeByPk(Integer {})", id);
		bookDAO.removeByPk(id);
	}

	public void calculateBookRate(Book book) {
		LOGGER.info("calculateBookRate(Book {})", book);
		Double sum = ZERO;
		List<Review> reviews = book.getReviews();
		List<Double> ratings = new ArrayList<Double>();

		for (Review review : reviews) {
			ratings.add((double) review.getRating());
		}

		if (!ratings.isEmpty()) {
			for (Double rating : ratings) {
				sum += rating;
			}
			book.setAverageRating(Math.floor((sum / (double) ratings.size()) * 100) / 100);

		} else
			book.setAverageRating(sum);
		bookDAO.update(book);
	}

	@Override
	public Integer calculateCountOfBook(Integer r) {
		LOGGER.info("calculateCountOfBook(Integer {})", r);
		return bookDAO.findBooksWithRating((double) r).size();
	}

	@Override
	public Integer findBookDataSize() {
		LOGGER.info("findBookDataSize()");
		return bookDAO.countAllBooks();
	}

	@Override
	public void removeAllByPk(List<Integer> pks) {
		LOGGER.info("removeAllByPk(List<Integer> {})", pks);
		bookDAO.removeAllbyId(pks);
	}

	@Override
	public Integer findBooksCountBitweenRating(double minRating, double maxRating) {
		LOGGER.info("findBooksCountBitweenRating(double {}, double {})", minRating, maxRating);
		return bookDAO.findBooksCountBitweenRating(minRating, maxRating);
	}

	@Override
	public Book findBookByISBN(String isbn) {
		LOGGER.info("Book findBookByISBN(String {})", isbn);
		return bookDAO.findBookByISBN(isbn);
	}

	@Override
	public List<Book> findTopBooks() {
		LOGGER.info("List<Book> findTopBooks()");
		return bookDAO.findTopBooks();
	}

	@Override
	public List<Book> findBooksByStatus(String status) {
		LOGGER.info("List<Book> findBooksByStatus(String {})", status);
		return bookDAO.findBooksByStatus(status);
	}

}
