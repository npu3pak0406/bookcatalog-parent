package com.softserve.rest.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserve.model.Review;

public class ReviewClientREST extends ClientRest {

	public ReviewClientREST() {
		super();
		TARGET_URL += "review";
	}

	public Review findReviewByPk(Integer id) {
		Response response = CLIENT.target(TARGET_URL).path(id.toString()).request().get();
		return response.readEntity(Review.class);
	}

	public void saveReview(Review review) {
		Response response = CLIENT.target(TARGET_URL).request().post(Entity.entity(review, MediaType.APPLICATION_JSON));
		response.close();
	}

	public void updateReview(Review review) {
		Response responce = CLIENT.target(TARGET_URL).request().put(Entity.entity(review, MediaType.APPLICATION_JSON));
		responce.close();
	}

	public void removeReview(Integer id) {
		Response response = CLIENT.target(TARGET_URL).path(id.toString()).request().delete();
		response.close();
	}

	public void findReviewsByBook(Integer id) {
		Response response = CLIENT.target(TARGET_URL).path("bookreviews").path(id.toString()).request().get();
		response.close();

	}

}