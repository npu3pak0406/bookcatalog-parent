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
	/**
	 * Get book by primary key
	 *
	 * @param id
	 *            -book's primary key
	 * @return Response - book entity
	 */
	@GET
	@Path("{id}")
	public Response getBook(@PathParam("id") Integer id);

	/**
	 * Create book
	 *
	 * @param bookDTO
	 *            - bookDTO in parameter request
	 * @return response status
	 */
	@POST
	public Response createBook(BookDTO bookDTO);

	/**
	 * Update book;
	 *
	 * @param bookDTO
	 *            - bookDTO in parameter request
	 * @return response status
	 */
	@PUT
	public Response updateBook(BookDTO bookDTO);

	/**
	 * Remove book by primary key
	 *
	 * @param id
	 *            - book's primary key
	 * @return response status
	 */
	@DELETE
	@Path("{id}")
	public Response removeBook(@PathParam("id") Integer id);

	/**
	 * Get books by author id
	 *
	 * @param id
	 *            -authors's primary key
	 * @return Response - List of books
	 */
	@GET
	@Path("getbooksbyauthor/{id}")
	public Response getBooksByAuthor(@PathParam("id") Integer id);

	/**
	 * Get books by rating
	 *
	 * @param rating
	 *            -book's rating
	 * @return Response - List of books
	 */
	@GET
	@Path("getbooksbyrating/{rating}")
	public Response getBooksByRating(@PathParam("rating") Integer rating);

	/**
	 * Get books by status
	 *
	 * @param status
	 *            -book's status
	 * @return Response - List of books
	 */
	@GET
	@Path("getbooksbystatus/{status}")
	public Response getBooksByStatus(@PathParam("status") String status);

	/**
	 * Get all books
	 *
	 * @return Response - List of books
	 */
	@GET
	@Path("getallbooks")
	public Response getAllBooks();
}
