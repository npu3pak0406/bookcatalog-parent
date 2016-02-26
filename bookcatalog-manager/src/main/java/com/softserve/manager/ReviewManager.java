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

	
	public List<Review> getReviewsByBookId(Integer id);

	public Integer getBookReviewsSize(int id);

	public List<Review> findReviews(int pageNumber, int pageSize, int bookId);
	
	public List<Review> findAllReviews();

}
