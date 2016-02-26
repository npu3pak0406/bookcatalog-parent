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

import com.softserve.model.Review;
import com.softserve.rest.dto.ReviewDTO;

@Path("/review")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface ReviewServiceREST {

	@GET
	@Path("{id}")
	public Response getReview(@PathParam("id") Integer id);

	@POST
	public Response createReview(ReviewDTO reviewDTO);

	@PUT
	public Response updateReview(ReviewDTO reviewDTO);

	@DELETE
	@Path("{id}")
	public Response removeReview(@PathParam("id") Integer id);

	@GET
	@Path("/bookreviews/{id}")
	public Response getBookReviews(@PathParam("id") Integer id);

}
