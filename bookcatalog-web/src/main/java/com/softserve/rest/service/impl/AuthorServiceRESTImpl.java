package com.softserve.rest.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.manager.AuthorManager;
import com.softserve.model.Author;
import com.softserve.rest.dto.AuthorDTO;
import com.softserve.rest.dto.Converter;
import com.softserve.rest.service.AuthorServiceREST;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AuthorServiceRESTImpl implements AuthorServiceREST {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorServiceRESTImpl.class);

	@EJB
	private AuthorManager authorManager;

	private Converter converter = new Converter();

	@Override
	public Response getAuthor(Integer id) {
		LOGGER.info("getAuthor({})", id);
		try {
			Author author = authorManager.findById(id);

			AuthorDTO authorDTO = new AuthorDTO();
			authorDTO = converter.convertAuthorToDTO(author);
			return Response.status(Status.OK).entity(authorDTO).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response createAuthor(AuthorDTO authorDTO) {
		LOGGER.info("createAuthor({})", authorDTO);
		try {
			Author author = new Author();
			author = converter.convertDTOToAuthor(authorDTO);
			authorManager.create(author);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response updateAuthor(AuthorDTO authorDTO) {
		LOGGER.info("updateAuthor({})", authorDTO);
		try {
			Author author = new Author();
			author = converter.convertDTOToAuthor(authorDTO);
			authorManager.update(author);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response removeAuthor(Integer id) {
		LOGGER.info("removeAuthor({})", id);
		try {
			authorManager.removeById(id);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
