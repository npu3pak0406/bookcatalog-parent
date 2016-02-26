package com.softserve.rest.client;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.model.Book;
import com.softserve.model.Status;
import com.softserve.rest.dto.BookDTO;

public class BookClientREST extends ClientRest {

	public BookClientREST() {
		super();
		TARGET_URL += "book";
	}

	public final static Logger LOGGER = LoggerFactory.getLogger(BookClientREST.class);

	public Book findBookByPk(Integer id) {
		Response response = CLIENT.target(TARGET_URL).path(id.toString()).request().get();
		return response.readEntity(Book.class);
	}

	public void removeBook(Integer id) {
		Response response = CLIENT.target(TARGET_URL).path(id.toString()).request().delete();
		response.close();
	}

	public void saveBook(BookDTO bookDTO) {
		Response response = CLIENT.target(TARGET_URL).request()
				.post(Entity.entity(bookDTO, MediaType.APPLICATION_JSON));
		response.close();
	}

	public void updateBook(BookDTO bookDTO) {
		Response responce = CLIENT.target(TARGET_URL).request().put(Entity.entity(bookDTO, MediaType.APPLICATION_JSON));
		responce.close();

	}

	public List<Book> allBookByAuthor(Integer id) {
		Response response = CLIENT.target(TARGET_URL).path("getbooksbyauthor").path(id.toString()).request().get();
		return response.readEntity((new GenericType<List<Book>>() {
		}));
	}

	public List<Book> getBooksByRating(Integer rating) {
		Response response = CLIENT.target(TARGET_URL).path("getbooksbyrating").path(rating.toString()).request().get();
		return response.readEntity((new GenericType<List<Book>>() {
		}));
	}

	public List<Book> getBooksByStatus(Status status) {
		Response response = CLIENT.target(TARGET_URL).path("getbooksbystatus").path(status.getLabel()).request().get();
		return response.readEntity((new GenericType<List<Book>>() {
		}));
	}

}