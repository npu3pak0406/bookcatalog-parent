package com.softserve.rest.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import com.softserve.manager.AuthorManager;
import com.softserve.model.Author;
import com.softserve.rest.dto.AuthorDTO;
import com.softserve.rest.dto.Converter;
import com.softserve.rest.service.AuthorServiceREST;

@Stateless
@Transactional
public class AuthorServiceRESTImpl implements AuthorServiceREST {

	@EJB
	private AuthorManager authorManager;

	private Converter converter = new Converter();

	@Override
	public Response getAuthor(Integer id) {
		try {
			Author author = authorManager.findById(id);

			AuthorDTO authorDTO = new AuthorDTO();
			authorDTO = converter.convertAuthorToDTO(author);
			return Response.status(200).entity(authorDTO).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response createAuthor(AuthorDTO authorDTO) {
		try {
			Author author = new Author();
			author = converter.convertDTOToAuthor(authorDTO);
			authorManager.create(author);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response updateAuthor(AuthorDTO authorDTO) {
		try {

			Author author = new Author();
			author = converter.convertDTOToAuthor(authorDTO);
			authorManager.update(author);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response removeAuthor(Integer id) {
		try {
			authorManager.removeByPk(id);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

}
