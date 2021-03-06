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

import com.softserve.dao.AuthorDAO;
import com.softserve.manager.AuthorManager;
import com.softserve.manager.BookManager;
import com.softserve.model.Author;

public class AuthorTestCRUD extends Arquillian {

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
	@UsingDataSet("datasets/author/init-author-data.xml")
	@ShouldMatchDataSet(value = "datasets/author/after-update-author.xml")
	public void update() {
		Author author = authorManager.findById(301);
		author.setFirstName("First");
		authorManager.update(author);
	}

	@Test
	@UsingDataSet("datasets/author/init-author-data.xml")
	@ShouldMatchDataSet(value = "datasets/author/after-remove-author.xml")
	public void remove() {
		authorManager.removeById(301);
	}

	@Test
	@UsingDataSet("datasets/author/init-author-data.xml")
	@ShouldMatchDataSet(value = "datasets/author/after-create-author.xml", excludeColumns = { "authorId",
			"createDate" })
	public void create() {
		Author author = new Author();
		author.setFirstName("First3");
		authorManager.create(author);
	}

	@Test
	@UsingDataSet("datasets/author/init-author-data.xml")
	@ShouldMatchDataSet(value = "datasets/author/after-getbyid-author.xml")
	public void getById() {
		Author author = authorManager.findById(301);
		System.out.println(author.getAuthorId());
	}

}
