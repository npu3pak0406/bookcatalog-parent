package com.softserve.dao;

import java.util.List;

import javax.ejb.Local;

import com.softserve.model.Review;

@Local
public interface ReviewDAO extends GenericDAO<Review, Integer> {
	/**
	 * This method return book's reviews
	 * 
	 * @param id
	 *            - book primary key
	 * @return list of Reviews
	 */
	List<Review> getReviewsByBookId(int id);

	/**
	 * This method return number of review appropriate book
	 * 
	 * @param bookId
	 *            - primary key of book
	 * @return numbers of Reviews
	 */
	public Integer countAllReviews(int bookId);

	/**
	 * This method returns reviews appropriate book to fill table page
	 * 
	 * @param pageNumber
	 *            - table page
	 * @param pageSize
	 *            - number of review on table page
	 * @param bookId
	 *            - primary key of book
	 * @return list of Reviews
	 */
	public List<Review> findReviews(int pageNumber, int pageSize, int bookId);

	/**
	 * This method returns all reviews from data base
	 * 
	 * @return list of reviews
	 */
	public List<Review> findAllReviews();

}
