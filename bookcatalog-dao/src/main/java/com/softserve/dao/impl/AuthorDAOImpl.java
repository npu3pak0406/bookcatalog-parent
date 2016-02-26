package com.softserve.dao.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.AuthorDAO;
import com.softserve.model.Author;

@Stateful
public class AuthorDAOImpl extends AbstractGenericDAOImpl<Author, Integer> implements AuthorDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorDAO.class);

	public AuthorDAOImpl() {
		super(Author.class);
	}

	@SuppressWarnings("unchecked")
	public List<Author> findAllAuthors() {
		LOGGER.info("List<Author> findAllAuthors()");
		Query query = em.createNamedQuery(Author.FIND_ALL_AUTHORS);
		List<Author> authors = (List<Author>) query.getResultList();
		return authors;
	}

	public Author findById(Integer id) {
		LOGGER.info("Author findById({})", id);
		Query query = em.createNamedQuery(Author.FIND_BY_ID);
		query.setParameter(2, id);
		Author author = (Author) query.getSingleResult();
		return author;
	}

	@Override
	public Integer countAllAuthors() {
		LOGGER.info("Integer countAllAuthors()");
		Query query = em.createNamedQuery(Author.COUNT_ALL_AUTHORS);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public void removeAllById(List<Integer> pks) {
		LOGGER.info("void removeAllById(List<Integer>{})", pks);
		for (Integer pk : pks) {
			em.remove(em.find(entityClass, pk));
		}
	}

}
