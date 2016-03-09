package com.softserve.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
		@NamedQuery(name = Review.FIND_REVIEWS_BY_BOOK_ID, query = "SELECT r FROM Review r JOIN r.book b WHERE b.bookId= :id ORDER BY r.createDate DESC"),
		@NamedQuery(name = Review.FIND_ALL_REVIEWS, query = "SELECT r FROM Review r ORDER BY r.createDate"),
		@NamedQuery(name = Review.FIND_BOOK_REVIEWS_SIZE, query = "SELECT COUNT(r) FROM Review r JOIN r.book b WHERE b.bookId= :id") })
public class Review {

	public static final String FIND_REVIEWS_BY_BOOK_ID = "findReviewsByBookId";
	public static final String FIND_BOOK_REVIEWS_SIZE = "getBookReviewsSize";
	public static final String FIND_ALL_REVIEWS = "findAllReviews";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reviewId", unique = true, nullable = false)
	private Integer reviewId;

	@NotNull
	@Size(min = 4, max = 15)
	@Column(name = "commenterName", nullable = false)
	private String commenterName;

	@Size(min = 1, max = 255)
	@Column(name = "comment")
	private String comment;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "createDate")
	private Date createDate;

	@Column(name = "updatedDay")
	private Date updatedDay;

	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book book;

	public Review() {
	}

	@PreUpdate
	private void onUpdate() {
		this.updatedDay = new Date();
	}

	@PrePersist
	private void onCreate() {
		this.createDate = new Date();
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDay() {
		return updatedDay;
	}

	public void setUpdatedDay(Date updatedDay) {
		this.updatedDay = updatedDay;
	}

	@Override
	public int hashCode() {
		Integer x = new Integer(this.reviewId);
		return (x.hashCode());
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Review))
			return false;

		Review review = (Review) obj;

		if (new Integer(this.reviewId).equals(new Integer(review.reviewId))) {
			return true;
		}
		return false;
	}

}
