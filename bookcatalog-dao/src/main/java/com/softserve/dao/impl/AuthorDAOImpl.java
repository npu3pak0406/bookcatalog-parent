package com.softserve.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.AuthorDAO;
import com.softserve.model.Author;

@Stateless
public class AuthorDAOImpl extends AbstractGenericDAOImpl<Author, Integer> implements AuthorDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDAO.class);

	public AuthorDAOImpl() {
		super(Author.class);
	}

	@SuppressWarnings("unchecked")
	public List<Author> findAllAuthors() {
		LOGGER.info("List<Author> findAllAuthors()");
		Query query = em.createNamedQuery(Author.FIND_ALL_AUTHORS);
		return query.getResultList();
	}

	@Override
	public Integer countAllAuthors() {
		LOGGER.info("Integer countAllAuthors()");
		Query query = em.createNamedQuery(Author.FIND_AUTHORS_COUNT);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public void removeAuthors(List<Author> authors) {
		LOGGER.info("void removeAllById(List<Integer>{})", authors);
		em.createNamedQuery(Author.REMOVE_AUTHORS).setParameter("authors", authors).executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Author> findAuthorsByBookId(Integer id) {
		LOGGER.info("findAuthorsByBookId({})", id);
		Query query = em.createNamedQuery(Author.FIND_AUTHORS_BY_BOOK_ID);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
