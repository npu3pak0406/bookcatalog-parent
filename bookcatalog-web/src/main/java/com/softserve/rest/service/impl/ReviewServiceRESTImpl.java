package com.softserve.rest.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import com.softserve.manager.ReviewManager;
import com.softserve.model.Review;
import com.softserve.rest.dto.Converter;
import com.softserve.rest.dto.ReviewDTO;
import com.softserve.rest.service.ReviewServiceREST;

@Stateless
@Transactional
public class ReviewServiceRESTImpl implements ReviewServiceREST {

	@EJB
	private ReviewManager reviewManager;

	private Converter converter = new Converter();

	@Override
	public Response getReview(Integer id) {
		try {
			ReviewDTO reviewDTO = converter.convertReviewToDTO(reviewManager.findReviewById(id));
			return Response.status(200).entity(reviewDTO).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}

	}

	@Override
	public Response createReview(ReviewDTO reviewDTO) {
		try {
			Review review = converter.convertDTOToReview(reviewDTO);
			reviewManager.create(review);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response updateReview(ReviewDTO reviewDTO) {
		try {
			Review review = converter.convertDTOToReview(reviewDTO);
			reviewManager.update(review);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response removeReview(Integer id) {
		try {
			reviewManager.removeByPk(id);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getBookReviews(Integer id) {
		try {
			List<Review> reviews = reviewManager.getReviewsByBookId(id);
			
			for (Review review : reviews) {
				review.setBook(null);
			}
			return Response.accepted(reviews).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

}
