package com.softserve.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({ @NamedQuery(name = Author.FIND_ALL_AUTHORS, query = "SELECT a FROM Author a ORDER BY a.firstName"),
		@NamedQuery(name = Author.FIND_AUTHOR_BY_ID, query = "SELECT a FROM Author a WHERE a.authorId= :id"),
		@NamedQuery(name = Author.FIND_AUTHORS_BY_BOOK_ID, query = "SELECT a FROM Author a INNER JOIN a.books b where b.bookId = :id"),
		@NamedQuery(name = Author.FIND_AUTHOR_BY_FIRST_SECOND_NAME, query = "SELECT a FROM Author a WHERE a.firstName= :first AND a.secondName= :second"),
		@NamedQuery(name = Author.FIND_AUTHORS_COUNT, query = "SELECT COUNT(a) FROM Author a"),
		@NamedQuery(name = Author.FIND_AUTHORS_BY_RATING, query = "SELECT a FROM Author a WHERE a.averageRating >= :minRating AND a.averageRating< :maxRating"),
		@NamedQuery(name = Author.REMOVE_AUTHORS, query = "DELETE FROM Author a WHERE a IN (:authors)") })

public class Author {

	public static final String FIND_AUTHORS_BY_BOOK_ID = "findAuthorsByBookId";
	public static final String FIND_AUTHOR_BY_ID = "findById";
	public static final String FIND_ALL_AUTHORS = "findAllAuthors";
	public static final String FIND_AUTHOR_BY_FIRST_SECOND_NAME = "findAuthorByName";
	public static final String FIND_AUTHORS_COUNT = "countALLAuthors";
	public static final String FIND_AUTHORS_BY_RATING = "findAuthorsByRating";
	public static final String REMOVE_AUTHORS = "removeAuthors";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authorId", unique = true, nullable = false)
	private Integer authorId;

	@Size(min = 4, max = 50)
	@Column(name = "firstName")
	private String firstName;

	@Size(min = 4, max = 50)
	@Column(name = "secondName")
	private String secondName;

	@Column(name = "createDate")
	private Date createDate;
	@OrderBy("name")
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors", cascade = CascadeType.DETACH)
	private List<Book> books = new ArrayList<Book>();

	@Column(name = "averageRating")
	private Double averageRating;

	@Transient
	private boolean isSelected;

	public Author() {
	}

	@PrePersist
	private void onCreate() {
		this.averageRating = 0d;
		this.createDate = new Date();
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return firstName + " " + secondName;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Author))
			return false;
		Author author = (Author) obj;
		if (new Integer(this.authorId).equals(new Integer(author.authorId))) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		Integer x = new Integer(this.authorId);
		return (x.hashCode());
	}

}
