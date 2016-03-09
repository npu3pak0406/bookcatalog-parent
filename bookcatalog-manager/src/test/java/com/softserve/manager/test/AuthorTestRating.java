package com.softserve.manager.test;

import java.util.ArrayList;
import java.util.List;

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

import com.softserve.dao.AuthorDAO;
import com.softserve.manager.AuthorManager;
import com.softserve.manager.BookManager;
import com.softserve.model.Author;
import com.softserve.model.Book;

public class AuthorTestRating extends Arquillian {
	@EJB
	private AuthorManager authorManager;

	@EJB
	private BookManager bookManager;

	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive war = ShrinkWrap.create(WebArchive.class, "AuthorManager.war");
		war.addPackages(true, Author.class.getPackage());
		war.addPackages(true, AuthorDAO.class.getPackage());
		war.addPackages(true, AuthorManager.class.getPackage());
		war.addAsResource("META-INF/persistence.xml");
		war.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		return war;
	}

	@Test
	@UsingDataSet("datasets/author/init2-author-data.xml")
	@ShouldMatchDataSet("datasets/author/after-calcrating-author.xml")
	public void calcRating() {

		List<Author> authors = new ArrayList<Author>();

		Book book = bookManager.findById(301);
		Author author = authorManager.findById(305);

		authors = book.getAuthors();
		authors.add(author);

		book.setAuthors(authors);

		bookManager.update(book);

		authorManager.calculateAuthorRate(book);

	}

}
