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

import com.softserve.rest.dto.AuthorDTO;

@Path("/author")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public interface AuthorServiceREST {

	/**
	 * Get author by primary key
	 *
	 * @param id
	 *            -author's primary key
	 * @return Response - author entity
	 */
	@GET
	@Path("{id}")
	public Response getAuthor(@PathParam("id") Integer id);

	/**
	 * Create author
	 *
	 * @param authorDTO
	 *            - authorDTO in parameter request
	 * @return response status
	 */
	@POST
	public Response createAuthor(AuthorDTO authorDTO);

	/**
	 * Update author;
	 *
	 * @param authorDTO
	 *            - authorDTO in parameter request
	 * @return response status
	 */
	@PUT
	public Response updateAuthor(AuthorDTO authorDTO);

	/**
	 * Remove author in parameter request.
	 *
	 * @param id
	 *            - author id
	 * @return response status
	 */
	@DELETE
	@Path("{id}")
	public Response removeAuthor(@PathParam("id") Integer id);
}
