package com.softserve.dao.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.BookDAO;
import com.softserve.model.Author;
import com.softserve.model.Book;
import com.softserve.model.Status;

@Stateful
public class BookDAOImpl extends AbstractGenericDAOImpl<Book, Integer> implements BookDAO {

	private final static Logger LOGGER = LoggerFactory.getLogger(BookDAO.class);

	private static final int TOP_10 = 10;

	@PersistenceContext(unitName = "persistence")
	private EntityManager em;

	public BookDAOImpl() {
		super(Book.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findAllBooksByAuthor(Author a) {

		LOGGER.info("getAllBooksByAuthor(Author {})", a);
		Query query = em.createNamedQuery(Book.FIND_ALL_BOOKS_BY_AUTHOR);
		query.setParameter("authorId", a.getAuthorId());
		List<Book> booklist = (List<Book>) query.getResultList();

		return booklist;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findAllBooks() {

		LOGGER.info("List<Book> returnAllBooks()");

		Query query = em.createNamedQuery(Book.FIND_ALL_BOOKS);
		List<Book> booklist = (List<Book>) query.getResultList();

		return booklist;
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
		List<Book> booklist = (List<Book>) query.getResultList();

		return booklist;
	}

	@Override
	public Book findBookById(Integer id) {
		LOGGER.info("Book getBookById(Integer{})", id);
		Query query = em.createNamedQuery(Book.FIND_BOOK_BY_ID);
		query.setParameter("id", id);
		Book book = (Book) query.getSingleResult();
		return book;
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
	public void removeAllbyId(List<Integer> pks) {
		LOGGER.info("removeAllbyPk(List<Intege> pks)" + " pks:" + pks);
		for (Integer pk : pks) {
			em.remove(em.find(entityClass, pk));
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
		query.setMaxResults(TOP_10);
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
		List<Book> booklist = (List<Book>) query.getResultList();

		return booklist;
	}

}
