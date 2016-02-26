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

	@Override
	public void removeByPk(Integer id) {
		LOGGER.info("removeByPk({})", id);
		reviewDAO.removeByPk(id);
	}

	@Override
	public void create(Review review) {
		LOGGER.info("void save(Review {})", review);
		reviewDAO.create(review);
	}

	@Override
	public void update(Review review) {
		LOGGER.info("update(Review {})", review);
		reviewDAO.update(review);
	}

	@Override
	public Review findReviewById(Integer id) {
		LOGGER.info("getReviewById({})", id);
		return reviewDAO.readById(id);
	}

	@Override
	public List<Review> getReviewsByBookId(Integer id) {
		LOGGER.info("getReviewsByBookId({})", id);
		return reviewDAO.getReviewsByBookId(id);
	}

	@Override
	public Integer getBookReviewsSize(int id) {
		LOGGER.info("getBookReviewsSize(int{})", id);
		return reviewDAO.countAllReviews(id);
	}

	@Override
	public List<Review> findReviews(int pageNumber, int pageSize, int bookId) {
		LOGGER.info("List<Review> findReviews({},{},{})", pageNumber, pageSize, bookId);
		return reviewDAO.findReviews(pageNumber, pageSize, bookId);
	}

	@Override
	public List<Review> findAllReviews() {
		LOGGER.info("findAllReviews");
		return reviewDAO.findAllReviews();
	}

}
