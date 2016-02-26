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

import com.softserve.dao.ReviewDAO;
import com.softserve.manager.ReviewManager;
import com.softserve.model.Review;

public class ReviewTest extends Arquillian {

	@EJB
	private ReviewManager reviewManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive war = ShrinkWrap.create(WebArchive.class, "ReviewManager.war");
		war.addPackages(true, Review.class.getPackage());
		war.addPackages(true, ReviewDAO.class.getPackage());
		war.addPackages(true, ReviewManager.class.getPackage());
		war.addAsResource("META-INF/persistence.xml");
		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return war;
	}

	@Test
	@UsingDataSet("datasets/review/init-review-data.xml")
	@ShouldMatchDataSet(value = "datasets/review/after-update-review.xml")
	public void update() {
		Review review = reviewManager.findReviewById(101);
		review.setCommenterName("Test");
		reviewManager.update(review);
	}

	@Test
	@UsingDataSet("datasets/review/init-review-data.xml")
	@ShouldMatchDataSet(value = "datasets/review/after-remove-review.xml")
	public void remove() {
		reviewManager.removeByPk(101);
	}

	@Test
	@UsingDataSet("datasets/review/init-review-data.xml")
	@ShouldMatchDataSet(value = "datasets/review/after-create-review.xml", excludeColumns = { "reviewId",
			"createDate" })
	public void create() {
		Review review = new Review();
		review.setCommenterName("Test3");
		reviewManager.create(review);
	}
	
}
