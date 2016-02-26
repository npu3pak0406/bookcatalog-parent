package com.softserve.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.dao.GenericDAO;

@Stateless
public class AbstractGenericDAOImpl<E, I extends Serializable> implements GenericDAO<E, I> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericDAOImpl.class);

	@PersistenceContext(unitName = "persistence", type = PersistenceContextType.TRANSACTION)
	protected EntityManager em;

	protected Class<E> entityClass;

	public AbstractGenericDAOImpl() {
	}

	public AbstractGenericDAOImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(E entity) {
		try {
			LOGGER.info("void create({})", entity.getClass());
			em.persist(entity);
		} catch (Exception e) {
			LOGGER.error("Exception: {}", e);
		}
	}

	public E readById(I id) {
		LOGGER.info(entityClass.getCanonicalName() + " readbyPk({})", id);
		E entity = (E) em.find(entityClass, id);
		return entity;

	}

	public void update(E entity) {
		try {
			LOGGER.info("void update({})", entity.getClass());
			em.merge(entity);
		} catch (Exception e) {
			LOGGER.error("Exception: {}", e);
		}
	}

	public void removeByPk(I id) {
		try {
			LOGGER.info("remove {} by pk {}", entityClass, id);
			E e = em.find(entityClass, id);
			em.remove(e);
		} catch (Exception e) {
			LOGGER.error("Exception: {}", e);
		}
	}

}
