package com.softserve.manager.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.BookDAO;
import com.softserve.dao.ReviewDAO;
import com.softserve.manager.ReviewManager;
import com.softserve.model.Review;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ReviewManagerImpl implements ReviewManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewManagerImpl.class);

	@EJB
	private ReviewDAO reviewDAO;

	@EJB
	private BookDAO bookDAO;

	@Override
	public void removeByPk(Integer id) {
		LOGGER.info("removeByPk({})", id);
		reviewDAO.removeByPk(id);
	}

	@Override
	public void create(Review review) {
		LOGGER.info("void save(Review {})", review.getReviewId());
		reviewDAO.create(review);
	}

	@Override
	public void update(Review review) {
		LOGGER.info("update(Review {})", review.getReviewId());
		reviewDAO.update(review);
	}

	@Override
	public Review findReviewById(Integer id) {
		LOGGER.info("getReviewById({})", id);
		return reviewDAO.readById(id);
	}

	@Override
	public List<Review> findReviewsByBookId(Integer id) {
		LOGGER.info("getReviewsByBookId({})", id);
		return reviewDAO.findReviewsByBookId(id);
	}

	@Override
	public Integer countAllReviews(int bookId) {
		LOGGER.info("getBookReviewsSize(int{})", bookId);
		return reviewDAO.countAllReviews(bookId);
	}

	@Override
	public List<Review> findAllReviews() {
		LOGGER.info("findAllReviews");
		return reviewDAO.findAllReviews();
	}

}
