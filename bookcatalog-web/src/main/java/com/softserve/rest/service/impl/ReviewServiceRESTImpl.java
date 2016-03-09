package com.softserve.rest.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.manager.ReviewManager;
import com.softserve.model.Review;
import com.softserve.rest.dto.Converter;
import com.softserve.rest.dto.ReviewDTO;
import com.softserve.rest.service.ReviewServiceREST;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReviewServiceRESTImpl implements ReviewServiceREST {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceRESTImpl.class);

	@EJB
	private ReviewManager reviewManager;

	private Converter converter = new Converter();

	@Override
	public Response getReview(Integer id) {
		LOGGER.info("getReview({})", id);
		try {
			ReviewDTO reviewDTO = converter.convertReviewToDTO(reviewManager.findReviewById(id));
			return Response.status(Status.OK).entity(reviewDTO).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@Override
	public Response createReview(ReviewDTO reviewDTO) {
		LOGGER.info("createReview({})", reviewDTO);
		try {
			Review review = converter.convertDTOToReview(reviewDTO);
			reviewManager.create(review);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response updateReview(ReviewDTO reviewDTO) {
		LOGGER.info("updateReview({})", reviewDTO);
		try {
			Review review = converter.convertDTOToReview(reviewDTO);
			reviewManager.update(review);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response removeReview(Integer id) {
		LOGGER.info("removeReview({})", id);
		try {
			reviewManager.removeByPk(id);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getBookReviews(Integer id) {
		LOGGER.info("getBookReviews({})", id);
		try {
			List<Review> reviews = reviewManager.findReviewsByBookId(id);

			for (Review review : reviews) {
				review.setBook(null);
			}
			return Response.accepted(reviews).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
