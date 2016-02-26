package com.softserve.rest.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserve.model.Author;

public class AuthorClientREST extends ClientRest {

	public AuthorClientREST() {
		super();
		TARGET_URL += "author";
	}

	public Author findAuthorByPk(Integer id) {
		Response response = CLIENT.target(TARGET_URL).path(id.toString()).request().get();
		return response.readEntity(Author.class);
	}

	public void saveAuthor(Author author) {
		Response response = CLIENT.target(TARGET_URL).request().post(Entity.entity(author, MediaType.APPLICATION_JSON));
		response.close();
	}

	public void updateAuthor(Author author) {
		Response responce = CLIENT.target(TARGET_URL).request().put(Entity.entity(author, MediaType.APPLICATION_JSON));
		responce.close();
	}

	public void removeAuthor(Integer id) {
		Response response = CLIENT.target(TARGET_URL).path(id.toString()).request().delete();
		response.close();

	}

}