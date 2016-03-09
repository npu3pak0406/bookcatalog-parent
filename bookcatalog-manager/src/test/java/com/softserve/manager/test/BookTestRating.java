package com.softserve.manager.test;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

import com.softserve.dao.BookDAO;
import com.softserve.dao.ReviewDAO;
import com.softserve.manager.BookManager;
import com.softserve.manager.ReviewManager;
import com.softserve.model.Book;
import com.softserve.model.Review;

public class BookTestRating extends Arquillian {

	@EJB
	private BookManager bookManager;

	@EJB
	private ReviewManager reviewManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive war = ShrinkWrap.create(WebArchive.class, "BookManager.war");
		war.addPackages(true, Book.class.getPackage());
		war.addPackages(true, BookDAO.class.getPackage());
		war.addPackages(true, BookManager.class.getPackage());
		war.addPackages(true, Review.class.getPackage());
		war.addPackages(true, ReviewDAO.class.getPackage());
		war.addPackages(true, ReviewManager.class.getPackage());

		war.addAsResource("META-INF/persistence.xml");
		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return war;
	}

	@Test()
	@UsingDataSet("datasets/book/init2-book-data.xml")
	@ShouldMatchDataSet(value = "datasets/book/after-calcrating-book.xml", excludeColumns = { "reviewId" })
	public void calcRating() {

		Book book = bookManager.findById(301);

		Review newReview = new Review();
		newReview.setRating(5);
		newReview.setCommenterName("newReview");
		newReview.setBook(book);
		reviewManager.create(newReview);

		bookManager.calculateBookRate(book);

	}
}
