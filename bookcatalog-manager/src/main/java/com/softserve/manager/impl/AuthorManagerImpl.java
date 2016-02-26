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
import com.softserve.manager.AuthorManager;
import com.softserve.model.Author;
import com.softserve.model.Book;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AuthorManagerImpl implements AuthorManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorManagerImpl.class);

	private static final Double ZERO = 0d;

	@EJB
	private AuthorDAO authorDAO;

	@EJB
	private BookDAO bookDAO;

	public List<Author> findAllAuthors() {
		LOGGER.info("List<Author> findAllAuthors()");
		List<Author> authors = authorDAO.findAllAuthors();
		return authors;
	}

	public Author findById(Integer id) {
		LOGGER.info("findById(Integer {})", id);
		return authorDAO.findById(id);
	}

	public void removeByPk(Integer id) {
		LOGGER.info("void removeByPk(Integer {})", id);
		authorDAO.removeByPk(id);
	}

	@Override
	public void create(Author newAuthor) {
		LOGGER.info("create(Author {})", newAuthor);
		authorDAO.create(newAuthor);

	}

	@Override
	public void update(Author author) {
		LOGGER.info("update(Author {})", author);
		authorDAO.update(author);
	}

	@Override
	public Integer countAllAuthors() {
		LOGGER.info("Integer countAllAuthors()");
		return authorDAO.countAllAuthors();
	}

	@Override
	public void removeAllByPk(List<Integer> pks) {
		LOGGER.info("removeAllByPk(List<Integer> {})", pks);
		authorDAO.removeAllById(pks);

	}

	@Override
	public void calculateAuthorRate(Book book) {

		LOGGER.info("calculateAuthorRate(Book {})", book);

		List<Author> authors = book.getAuthors();
		Double sum = ZERO;
		for (Author author : authors) {
			sum = ZERO;
			List<Double> ratings = new ArrayList<Double>();
			List<Book> books = author.getBooks();

			for (Book b : books) {
				ratings.add(b.getAverageRating());
			}

			if (!ratings.isEmpty()) {
				for (Double rating : ratings) {
					sum += rating;
				}
				author.setAverageRating(Math.floor((sum / (double) ratings.size()) * 100) / 100);
				authorDAO.update(author);
			}
		}
	}

}
