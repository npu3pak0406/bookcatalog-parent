package com.softserve.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.BookDAO;
import com.softserve.model.Book;
import com.softserve.model.Status;

@Stateless
public class BookDAOImpl extends AbstractGenericDAOImpl<Book, Integer> implements BookDAO {

	private final static Logger LOGGER = LoggerFactory.getLogger(BookDAO.class);

	private static final int TOP_10_BOOKS = 10;

	public BookDAOImpl() {
		super(Book.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findAllBooks() {
		LOGGER.info("List<Book> returnAllBooks()");
		Query query = em.createNamedQuery(Book.FIND_ALL_BOOKS);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findAllAvailableBooks() {
		LOGGER.info("List<Book> getAllAvailableBooks()");
		Query query = em.createNamedQuery(Book.FIND_ALL_AVAILABLE_BOOKS);
		query.setParameter("status", Status.AVAILABLE);
		List<Book> booklist = (List<Book>) query.getResultList();

		return booklist;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findBooksWithRating(Double rating) {
		LOGGER.info("List<Book> getBooksWithRating({})", rating);
		Query query = em.createNamedQuery(Book.FIND_BOOKS_WITH_RATING);
		query.setParameter("rating", rating);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Book> findBooks(int pageNumber, int pageSize) {
		LOGGER.info("List<Book> findBooks({},{})", pageNumber, pageSize);
		Query query = em.createNamedQuery(Book.FIND_ALL_BOOKS);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return (List<Book>) query.getResultList();
	}

	@Override
	public Integer countAllBooks() {
		LOGGER.info("Integer getBookDataSize()");
		Query query = em.createNamedQuery(Book.FIND_BOOK_DATA_SIZE);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void removeBooks(List<Book> books) {
		LOGGER.info("removeAllbyPk(List<Intege> {})", books);
		List<Book> bs = em.createQuery("SELECT b FROM Book b WHERE b IN (:books)").setParameter("books", books)
				.getResultList();
		for (Book book : bs) {
			em.remove(book);
		}
	}

	@Override
	public Integer findBooksCountBitweenRating(double minRating, double maxRating) {
		LOGGER.info("findBooksCountBitweenRating({},{})", minRating, maxRating);
		Query query = em.createNamedQuery(Book.FIND_BOOKS_COUNT_BITWEEN_RATING);
		query.setParameter("minRating", minRating).setParameter("maxRating", maxRating);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findTopBooks() {
		LOGGER.info("List<Book> findTopBooks()");
		Query query = em.createNamedQuery(Book.FIND_ALL_BOOKS_SORTED_BY_RATING);
		query.setMaxResults(TOP_10_BOOKS);
		return (List<Book>) query.getResultList();
	}

	@Override
	public Book findBookByISBN(String isbn) {
		LOGGER.info("findBookByISBN({})", isbn);
		try {
			Query query = em.createNamedQuery(Book.FIND_BOOK_BY_ISBN);
			query.setParameter("isbn", isbn);
			return (Book) query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info("unique isbn");
			return null;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByStatus(String status) {
		LOGGER.info("findBooksByStatus({})", status);
		Query query = em.createNamedQuery(Book.FIND_BOOKS_BY_STATUS);
		query.setParameter("status", Status.valueOf(status));
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByAuthorId(Integer id) {
		LOGGER.info("findBooksByBookId({})", id);
		Query query = em.createNamedQuery(Book.FIND_BOOKS_BY_AUTHOR_ID);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
