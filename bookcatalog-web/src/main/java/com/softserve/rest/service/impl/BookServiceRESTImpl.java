package com.softserve.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.manager.AuthorManager;
import com.softserve.manager.BookManager;
import com.softserve.model.Author;
import com.softserve.model.Book;
import com.softserve.rest.dto.BookDTO;
import com.softserve.rest.dto.Converter;
import com.softserve.rest.service.BookServiceREST;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BookServiceRESTImpl implements BookServiceREST {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceRESTImpl.class);

	@EJB
	private BookManager bookManager;

	@EJB
	private AuthorManager authorService;

	private Converter converter = new Converter();
	private List<Book> books = new ArrayList<Book>();
	private List<BookDTO> booksDTO = new ArrayList<BookDTO>();

	@Override
	public Response getBook(Integer id) {
		LOGGER.info("getBook({})", id);
		try {
			Book book = bookManager.findById(id);

			BookDTO bookDTO = new BookDTO();
			bookDTO = converter.convertBookToDTO(book);

			return Response.status(Status.OK).entity(bookDTO).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response createBook(BookDTO bookDTO) {
		LOGGER.info("createBook({})", bookDTO);
		try {

			Book book = new Book();
			book = converter.convertDTOToBook(bookDTO);
			bookManager.create(book);

			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response updateBook(BookDTO bookDTO) {
		LOGGER.info("updateBook({})", bookDTO);
		try {
			Book book = new Book();
			book = converter.convertDTOToBook(bookDTO);
			bookManager.update(book);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response removeBook(Integer id) {
		LOGGER.info("removeBook({})", id);
		try {
			bookManager.removeByPk(id);
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getBooksByAuthor(Integer id) {
		LOGGER.info("getBooksByAuthor({})", id);
		try {
			Author author = authorService.findById(id);
			books = author.getBooks();
			if (CollectionUtils.isNotEmpty(books)) {
				booksDTO.clear();
				for (Book book : books) {
					booksDTO.add(converter.convertBookToDTO(book));
				}
			}
			return Response.accepted(booksDTO).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getBooksByRating(Integer rating) {
		LOGGER.info("getBooksByRating({})", rating);
		try {
			books = bookManager.getBooksWithRating((double) rating);
			if (CollectionUtils.isNotEmpty(books)) {
				booksDTO.clear();
				for (Book book : books) {
					booksDTO.add(converter.convertBookToDTO(book));
				}
			}
			return Response.accepted(booksDTO).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getBooksByStatus(String status) {
		LOGGER.info("getBooksByStatus({})", status);
		try {
			books = bookManager.findBooksByStatus(status);
			if (CollectionUtils.isNotEmpty(books)) {
				booksDTO.clear();
				for (Book book : books) {
					booksDTO.add(converter.convertBookToDTO(book));
				}
			}
			return Response.accepted(booksDTO).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@Override
	public Response getAllBooks() {
		LOGGER.info("getAllBooks()");
		try {
			books = bookManager.findAllBooks();
			if (CollectionUtils.isNotEmpty(books)) {
				booksDTO.clear();
				for (Book book : books) {
					booksDTO.add(converter.convertBookToDTO(book));
				}
			}
			return Response.accepted(booksDTO).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
