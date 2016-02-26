package com.softserve.dao.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.ReviewDAO;
import com.softserve.model.Review;

@Stateful
public class ReviewDAOImpl extends AbstractGenericDAOImpl<Review, Integer> implements ReviewDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewDAO.class);

	@PersistenceContext(unitName = "persistence")
	private EntityManager em;

	public ReviewDAOImpl() {
		super(Review.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Review> getReviewsByBookId(int id) {
		LOGGER.info("getReviewsByBookId(int {})", id);
		Query query = em.createNamedQuery(Review.FIND_REVIEWS_BY_BOOK_ID).setParameter("id", id);
		List<Review> reviews = (List<Review>) query.getResultList();
		return reviews;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Review> findReviews(int pageNumber, int pageSize, int bookId) {
		LOGGER.info("findReviews({},{},{})", pageNumber, pageSize, bookId);
		Query query = em.createNamedQuery(Review.FIND_BOOK_REVIEWS).setParameter("id", bookId);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return (List<Review>) query.getResultList();
	}

	@Override
	public Integer countAllReviews(int id) {
		LOGGER.info("getBookReviewsSize(int {})", id);
		Query query = em.createNamedQuery(Review.FIND_BOOK_REVIEWS_SIZE).setParameter("id", id);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Review> findAllReviews() {
		LOGGER.info("findAllReviews()");
		Query query = em.createNamedQuery(Review.FIND_ALL_REVIEWS);
		return (List<Review>) query.getResultList();
	}

}
