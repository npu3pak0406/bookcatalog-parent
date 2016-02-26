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

import com.softserve.rest.dto.BookDTO;

@Path("/book")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public interface BookServiceREST {

	@GET
	@Path("{id}")
	public Response getBook(@PathParam("id") Integer id);

	@POST
	public Response createBook(BookDTO bookDTO);

	@PUT
	public Response updateBook(BookDTO bookDTO);

	@DELETE
	@Path("{id}")
	public Response removeBook(@PathParam("id") Integer id);

	@GET
	@Path("getbooksbyauthor/{id}")
	public Response getBooksByAuthor(@PathParam("id") Integer id);

	@GET
	@Path("getbooksbyrating/{rating}")
	public Response getBooksByRating(@PathParam("rating") Integer rating);

	@GET
	@Path("getbooksbystatus/{status}")
	public Response getBooksByStatus(@PathParam("status") String status);

}
