package com.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.AuthorDAO;
import com.softserve.dao.BookDAO;
import com.softserve.dao.ReviewDAO;
import com.softserve.manager.AuthorManager;
import com.softserve.model.Author;
import com.softserve.model.Book;
import com.softserve.model.Review;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AuthorManagerImpl implements AuthorManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorManagerImpl.class);

	private static final Double ZERO = 0d;

	@EJB
	private AuthorDAO authorDAO;

	@EJB
	private BookDAO bookDAO;

	@EJB
	private ReviewDAO reviewDAO;

	public Author findById(Integer id) {
		LOGGER.info("findById(Integer {})", id);
		Author author = authorDAO.readById(id);
		author.getBooks().size();
		return author;
	}

	@Override
	public void removeById(Integer id) {
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
	public List<Author> findAllAuthors() {
		LOGGER.info("List<Author> findAllAuthors()");
		return initBookList(authorDAO.findAllAuthors());
	}

	@Override
	public Integer countAllAuthors() {
		LOGGER.info("Integer countAllAuthors()");
		return authorDAO.countAllAuthors();
	}

	@Override
	public void removeAuthors(List<Author> authors) {
		LOGGER.info("removeAllByPk(List<Integer> {})", authors);
		authorDAO.removeAuthors(authors);

	}

	@Override
	public void calculateAuthorRate(Book book) {

		LOGGER.info("calculateAuthorRate(Book {})", book);

		List<Author> authors = book.getAuthors();
		Double sum = ZERO;
		for (Author author : authors) {
			sum = ZERO;
			List<Double> ratings = new ArrayList<Double>();
			List<Book> books = bookDAO.findBooksByAuthorId(author.getAuthorId());

			for (Book b : books) {
				List<Review> reviews = reviewDAO.findReviewsByBookId(b.getBookId());
				if (CollectionUtils.isNotEmpty(reviews)) {
					ratings.add(b.getAverageRating());
				}
			}
			if (CollectionUtils.isNotEmpty(ratings)) {
				for (Double rating : ratings) {
					sum += rating;
				}
				author.setAverageRating(Math.floor((sum / (double) ratings.size()) * 100) / 100);
				authorDAO.update(author);
			}
		}
	}

	@Override
	public void recalcRatingOndelete(Book book) {

		LOGGER.info("recalcRatingOndelete(Book {})", book);

		List<Author> authors = book.getAuthors();
		Double sum = ZERO;
		for (Author author : authors) {
			sum = ZERO;
			List<Double> ratings = new ArrayList<Double>();
			List<Book> books = bookDAO.findBooksByAuthorId(author.getAuthorId());
			for (Book b : books) {
				List<Review> reviews = reviewDAO.findReviewsByBookId(b.getBookId());
				if (CollectionUtils.isNotEmpty(reviews)) {
					ratings.add(b.getAverageRating());
				}
			}
			if (CollectionUtils.isNotEmpty(ratings)) {
				for (Double rating : ratings) {
					sum += rating;
				}
				if (ratings.size() == 1) {
					author.setAverageRating(ZERO);
				} else {
					sum -= book.getAverageRating();
					author.setAverageRating(Math.floor((sum / (double) (ratings.size() - 1)) * 100) / 100);
				}
				authorDAO.update(author);
			} else {
				author.setAverageRating(0.0);
				authorDAO.update(author);
			}
		}
	}

	private List<Author> initBookList(List<Author> authors) {
		LOGGER.info("initBookList{}", authors);
		for (Author author : authors) {
			author.getBooks().size();
		}
		return authors;
	}
}
