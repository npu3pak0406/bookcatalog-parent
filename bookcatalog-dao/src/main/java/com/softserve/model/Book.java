package com.softserve.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
		@NamedQuery(name = Book.FIND_BOOKS_BY_AUTHOR_ID, query = "SELECT b FROM Book b JOIN b.authors a WHERE a.authorId = :id ORDER BY b.averageRating DESC, b.createDate"),
		@NamedQuery(name = Book.FIND_ALL_BOOKS, query = "SELECT b FROM Book b ORDER BY b.averageRating DESC, b.createDate"),
		@NamedQuery(name = Book.FIND_ALL_AVAILABLE_BOOKS, query = "SELECT b FROM Book b WHERE b.status =:status"),
		@NamedQuery(name = Book.FIND_BOOKS_WITH_RATING, query = "SELECT b FROM Book b  WHERE b.averageRating >= :rating"),
		@NamedQuery(name = Book.FIND_BOOK_BY_ID, query = "SELECT b FROM Book b WHERE b.bookId = :id"),
		@NamedQuery(name = Book.FIND_COUNT_BOOKS_BY_AUTHOR, query = "SELECT COUNT(b) FROM Book b JOIN b.authors a WHERE a.authorId = :id"),
		@NamedQuery(name = Book.FIND_BOOKS_COUNT_BITWEEN_RATING, query = "SELECT COUNT(b) FROM Book b WHERE b.averageRating >=:minRating AND b.averageRating <= :maxRating"),
		@NamedQuery(name = Book.FIND_BOOK_BY_ISBN, query = "SELECT b FROM Book b WHERE b.isbn = :isbn"),
		@NamedQuery(name = Book.FIND_BOOKS_BY_STATUS, query = "SELECT b FROM Book b WHERE b.status = :status"),
		@NamedQuery(name = Book.FIND_ALL_BOOKS_SORTED_BY_RATING, query = "SELECT b FROM Book b ORDER BY b.averageRating DESC, b.createDate"),
		@NamedQuery(name = Book.FIND_BOOK_DATA_SIZE, query = "SELECT COUNT(b) FROM Book b"),
		@NamedQuery(name = Book.REMOVE_BOOKS, query = "DELETE FROM Book b WHERE b IN (:books)") })
public class Book {

	public static final String FIND_BOOKS_BY_AUTHOR_ID = "getAllBooksByAuthor";
	public static final String FIND_ALL_BOOKS = "returnAllBooks";
	public static final String FIND_ALL_AVAILABLE_BOOKS = "getAllAvailableBooks";
	public static final String FIND_BOOKS_WITH_RATING = "getBooksWithRating";
	public static final String FIND_BOOK_BY_ID = "getBookById";
	public static final String FIND_BOOK_DATA_SIZE = "findAllCountBook";
	public static final String FIND_COUNT_BOOKS_BY_AUTHOR = "findCountBooksByAuthor";
	public static final String FIND_BOOKS_COUNT_BITWEEN_RATING = "findBooksCountBitweenRating";
	public static final String FIND_BOOK_BY_ISBN = "findBookByISBN";
	public static final String FIND_ALL_BOOKS_SORTED_BY_RATING = "findAllBooksSortedByRating";
	public static final String FIND_BOOKS_BY_STATUS = "findBooksByStatus";
	public static final String REMOVE_BOOKS = "removeBooks";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookId", unique = true, nullable = false)
	private Integer bookId;

	@Size(min = 4, max = 50)
	@Column(name = "name")
	private String name;

	@Column(name = "yearPublished")
	private Integer yearPublished;

	@Column(name = "description")
	private String description;

	@Pattern(regexp = "^([0-9]{3})([-])([0-9]{1})([-])([0-9]{4})([-])([0-9]{4})([-])([0-9]{1})", message = "Not valid isbn, XXX-X-XXXX-XXXX-X")
	@Column(name = "isbn", unique = true)
	private String isbn;

	@Size(min = 4, max = 50)
	@Column(name = "publisher")
	private String publisher;

	@Column(name = "createDate")
	private Date createDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Column(name = "averageRating")
	private Double averageRating;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "booksAuthor", joinColumns = { @JoinColumn(name = "bookId") }, inverseJoinColumns = {
			@JoinColumn(name = "authorId") })
	private List<Author> authors = new ArrayList<Author>();

	@OrderBy("createDate DESC")
	@OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Review> reviews;

	@Transient
	private boolean isSelected;

	public Book() {
	}

	public Book(Integer bookId, String name) {
		this.bookId = bookId;
		this.name = name;
	}

	@PrePersist
	private void onCreate() {
		this.averageRating = 0d;
		this.createDate = new Date();
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(Integer yearPublished) {
		this.yearPublished = yearPublished;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Review> getReviews() {
		Collections.reverse(reviews);
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "BookId= " + bookId + ", name= " + name;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Author))
			return false;

		Book book = (Book) obj;

		if (new Integer(this.bookId).equals(new Integer(book.bookId))) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		Integer x = new Integer(this.bookId);
		return (x.hashCode());
	}

}
