package com.softserve.manager;

import java.util.List;

import javax.ejb.Local;

import com.softserve.model.Review;

@Local
public interface ReviewManager {

	public void removeByPk(Integer reviewId);

	public void create(Review newReview);

	public void update(Review currentReview);

	public Review findReviewById(Integer id);

	/**
	 * This method return list of book related to book which will be find by
	 * book id
	 * 
	 * @param id
	 *            - primary key of book
	 */
	public List<Review> findReviewsByBookId(Integer id);

	/**
	 * This method return number of review appropriate book
	 * 
	 * @param bookId
	 *            - primary key of book
	 * @return numbers of Reviews
	 */
	public Integer countAllReviews(int id);

	
	public List<Review> findAllReviews();

}
