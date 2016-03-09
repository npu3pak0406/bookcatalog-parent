package com.softserve.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserve.rest.dto.ReviewDTO;

@Path("/review")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface ReviewServiceREST {
	/**
	 * Get review by primary key
	 *
	 * @param id
	 *            -review's primary key
	 * @return Response - book entity
	 */
	@GET
	@Path("{id}")
	public Response getReview(@PathParam("id") Integer id);

	/**
	 * Create review
	 *
	 * @param reviewDTO
	 *            - reviewDTO in parameter request
	 * @return response status
	 */
	@POST
	public Response createReview(ReviewDTO reviewDTO);

	/**
	 * Update review;
	 *
	 * @param reviewDTO
	 *            - reviewDTO in parameter request
	 * @return response status
	 */
	@PUT
	public Response updateReview(ReviewDTO reviewDTO);

	/**
	 * Remove review by primary key
	 *
	 * @param id
	 *            - review's primary key
	 * @return response status
	 */
	@DELETE
	@Path("{id}")
	public Response removeReview(@PathParam("id") Integer id);

	/**
	 * Get reviews by book id
	 * 
	 * @param id
	 *            - book's primary key
	 * @return list of reviews
	 */
	@GET
	@Path("/bookreviews/{id}")
	public Response getBookReviews(@PathParam("id") Integer id);

}
