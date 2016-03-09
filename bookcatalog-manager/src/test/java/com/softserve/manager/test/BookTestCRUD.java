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
import com.softserve.manager.BookManager;
import com.softserve.manager.ReviewManager;
import com.softserve.model.Book;

public class BookTestCRUD extends Arquillian {

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
		war.addAsResource("META-INF/persistence.xml");
		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return war;
	}

	@Test
	@UsingDataSet("datasets/book/init-book-data.xml")
	@ShouldMatchDataSet(value = "datasets/book/after-create-book.xml", excludeColumns = { "bookId", "createDate" })
	public void create() {
		Book book = new Book();
		book.setName("Java8");
		bookManager.create(book);
	}

	@Test
	@UsingDataSet("datasets/book/init-book-data.xml")
	@ShouldMatchDataSet(value = "datasets/book/after-update-book.xml")
	public void update() {
		Book book = bookManager.findById(301);
		book.setName("Java8");
		bookManager.update(book);
	}

	@Test
	@UsingDataSet("datasets/book/init-book-data.xml")
	@ShouldMatchDataSet(value = "datasets/book/after-remove-book.xml")
	public void remove() {
		bookManager.removeByPk(301);
	}

}
