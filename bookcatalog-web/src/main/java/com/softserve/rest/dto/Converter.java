package com.softserve.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.collections.CollectionUtils;

import com.softserve.manager.AuthorManager;
import com.softserve.model.Author;
import com.softserve.model.Book;
import com.softserve.model.Review;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Converter {

	@EJB
	private AuthorManager authorManager;

	public BookDTO convertBookToDTO(Book book) {

		BookDTO bookDTO = new BookDTO();

		bookDTO.setBookId(book.getBookId());
		bookDTO.setAverageRating(book.getAverageRating());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setName(book.getName());
		bookDTO.setPublisher(book.getPublisher());
		bookDTO.setStatus(book.getStatus());
		bookDTO.setYearPublished(book.getYearPublished());
		bookDTO.setCreateDate(book.getCreateDate());

		List<AuthorDTO> authorsDTO = new ArrayList<AuthorDTO>();
		List<Author> authors = book.getAuthors();

		if (CollectionUtils.isNotEmpty(book.getAuthors())) {
			for (Author author : authors) {
				authorsDTO.add(convertAuthorToDTO(author));
			}
		}
		bookDTO.setAuthors(authorsDTO);

		List<ReviewDTO> reviewDTO = new ArrayList<ReviewDTO>();
		List<Review> reviews = book.getReviews();

		if (CollectionUtils.isNotEmpty(book.getReviews())) {
			for (Review review : reviews) {
				reviewDTO.add(convertReviewToDTO(review));
			}
		}

		bookDTO.setReviews(reviewDTO);

		return bookDTO;
	}

	public AuthorDTO convertAuthorToDTO(Author author) {

		AuthorDTO authorDTO = new AuthorDTO();

		authorDTO.setAuthorId(author.getAuthorId());
		authorDTO.setFirstName(author.getFirstName());
		authorDTO.setSecondName(author.getSecondName());
		authorDTO.setCreateDate(author.getCreateDate());
		authorDTO.setAverageRating(author.getAverageRating());

		return authorDTO;

	}

	public ReviewDTO convertReviewToDTO(Review review) {

		ReviewDTO reviewDTO = new ReviewDTO();

		reviewDTO.setReviewId(review.getReviewId());
		reviewDTO.setCommenterName(review.getCommenterName());
		reviewDTO.setComment(review.getComment());
		reviewDTO.setRating(review.getRating());

		return reviewDTO;

	}

	public Book convertDTOToBook(BookDTO bookDTO) {
		Book book = new Book();

		book.setBookId(bookDTO.getBookId());

		book.setBookId(bookDTO.getBookId());
		book.setAverageRating(bookDTO.getAverageRating());
		book.setIsbn(bookDTO.getIsbn());
		book.setName(bookDTO.getName());
		book.setPublisher(bookDTO.getPublisher());
		book.setStatus(bookDTO.getStatus());
		book.setYearPublished(bookDTO.getYearPublished());
		book.setCreateDate(bookDTO.getCreateDate());

		List<AuthorDTO> authorsDTO = bookDTO.getAuthors();
		List<Author> authors = new ArrayList<Author>();

		if (CollectionUtils.isNotEmpty(bookDTO.getAuthors())) {
			for (AuthorDTO authorDTO : authorsDTO) {
				authors.add(authorManager.findById(authorDTO.getAuthorId()));

			}
		}
		book.setAuthors(authors);

		List<ReviewDTO> reviewsDTO = bookDTO.getReviews();
		List<Review> reviews = new ArrayList<Review>();

		if (CollectionUtils.isNotEmpty(bookDTO.getReviews())) {
			for (ReviewDTO reviewDTO : reviewsDTO) {
				reviews.add(convertDTOToReview(reviewDTO));
			}
		}
		book.setReviews(reviews);
		return book;
	}

	public Author convertDTOToAuthor(AuthorDTO authorDTO) {

		Author author = new Author();

		author.setAuthorId(authorDTO.getAuthorId());
		author.setFirstName(authorDTO.getFirstName());
		author.setSecondName(authorDTO.getSecondName());
		author.setCreateDate(authorDTO.getCreateDate());
		author.setAverageRating(authorDTO.getAverageRating());

		return author;

	}

	public Review convertDTOToReview(ReviewDTO reviewDTO) {

		Review review = new Review();

		review.setReviewId(reviewDTO.getReviewId());
		review.setCommenterName(reviewDTO.getCommenterName());
		review.setComment(reviewDTO.getComment());
		review.setRating(reviewDTO.getRating());

		return review;

	}

}
