package com.softserve.manager.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.AuthorDAO;
import com.softserve.dao.BookDAO;
import com.softserve.dao.ReviewDAO;
import com.softserve.manager.BookManager;
import com.softserve.model.Book;
import com.softserve.model.Review;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BookManagerImpl implements BookManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookManagerImpl.class);
	private static final Double ZERO = 0d;

	@EJB
	private BookDAO bookDAO;

	@EJB
	private AuthorDAO authorDAO;

	@EJB
	private ReviewDAO reviewDAO;

	public BookManagerImpl() {
	}

	@Override
	public List<Book> findBooksByAuthorId(Integer id) {
		LOGGER.info("getAllBooksByAuthor(Author {})", id);
		return bookDAO.findBooksByAuthorId(id);
	}

	@Override
	public List<Book> findAllBooks() {
		LOGGER.info("findAllBooks()");
		return bookDAO.findAllBooks();
	}

	@Override
	public List<Book> getAllAvailableBooks() {
		LOGGER.info("getAllAvailableBooks()");
		return bookDAO.findAllAvailableBooks();
	}

	@Override
	public List<Book> getBooksWithRating(Double rating) {
		LOGGER.info("getBooksWithRating(Double {})", rating);
		return bookDAO.findBooksWithRating(rating);
	}

	@Override
	public Book findById(Integer id) {
		LOGGER.info("Book findById(Integer {})", id);
		return bookDAO.readById(id);
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
	public void removeByPk(Integer id) {
		LOGGER.info("void removeByPk(Integer {})", id);
		bookDAO.removeByPk(id);
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
	public void removeBooks(List<Book> books) {
		LOGGER.info("removeAllByPk(List<Integer> {})", books);
		bookDAO.removeBooks(books);
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

	@Override
	public void calculateBookRate(Book book) {
		LOGGER.info("calculateBookRate(Book {})", book);
		
		Double sum = ZERO;
		List<Review> reviews = reviewDAO.findReviewsByBookId(book.getBookId());
		
		for (Review review : reviews) {
			sum = sum + review.getRating();
		}
		if (CollectionUtils.isNotEmpty(reviews)) {
			book.setAverageRating(Math.floor((sum / (double) reviews.size()) * 100) / 100);

		} else
			book.setAverageRating(sum);
		bookDAO.update(book);
	}

}
