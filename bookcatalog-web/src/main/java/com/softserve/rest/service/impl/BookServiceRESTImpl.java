package com.softserve.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;

import com.softserve.manager.AuthorManager;
import com.softserve.manager.BookManager;
import com.softserve.model.Author;
import com.softserve.model.Book;
import com.softserve.rest.dto.BookDTO;
import com.softserve.rest.dto.Converter;
import com.softserve.rest.service.BookServiceREST;

@Stateless
@Transactional
public class BookServiceRESTImpl implements BookServiceREST {

	@EJB
	private BookManager bookManager;

	@EJB
	private AuthorManager authorService;

	private Converter converter = new Converter();

	@Override
	public Response getBook(Integer id) {
		try {
			Book book = bookManager.findBookById(id);

			BookDTO bookDTO = new BookDTO();
			bookDTO = converter.convertBookToDTO(book);

			return Response.status(200).entity(bookDTO).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response createBook(BookDTO bookDTO) {
		try {

			Book book = new Book();
			book = converter.convertDTOToBook(bookDTO);
			bookManager.create(book);

			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response updateBook(BookDTO bookDTO) {
		try {

			Book book = new Book();
			book = converter.convertDTOToBook(bookDTO);
			bookManager.update(book);

			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response removeBook(Integer id) {
		try {
			bookManager.removeByPk(id);
			return Response.status(200).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getBooksByAuthor(Integer id) {
		try {
			Author author = authorService.findById(id);

			List<Book> books = author.getBooks();
			List<BookDTO> booksDTO = new ArrayList<BookDTO>();

			if (CollectionUtils.isNotEmpty(books)) {
				for (Book book : books) {
					booksDTO.add(converter.convertBookToDTO(book));
				}
			}

			return Response.accepted(booksDTO).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getBooksByRating(Integer rating) {
		try {
			List<Book> books = bookManager.getBooksWithRating((double) rating);
			List<BookDTO> booksDTO = new ArrayList<BookDTO>();
			
			if (CollectionUtils.isNotEmpty(books)) {
				for (Book book : books) {
					booksDTO.add(converter.convertBookToDTO(book));
				}
			}

			return Response.accepted(booksDTO).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getBooksByStatus(String status) {
		try {
			List<Book> books = bookManager.findBooksByStatus(status);
			List<BookDTO> booksDTO = new ArrayList<BookDTO>();

			if (CollectionUtils.isNotEmpty(books)) {
				for (Book book : books) {
					booksDTO.add(converter.convertBookToDTO(book));
				}
			}

			return Response.accepted(booksDTO).build();
		} catch (Exception e) {
			return Response.status(422).entity(e.getMessage()).build();
		}
	}

}
