package com.softserve.dao;

public interface GenericDAO<E, I> {

	/**
	 * Make an instance managed and persistent.
	 * 
	 * @param entity
	 *            - entity instance
	 */
	public void create(E entity);

	/**
	 * Find by primary key.
	 * 
	 * @param id-
	 *            primary key
	 * @return the found entity instance
	 */
	public E readById(I id);

	/**
	 * Merge the state of the given entity
	 * 
	 * @param entity
	 *            - entity instance
	 */
	public void update(E entity);

	/**
	 * Remove the entity instance by primary key.
	 * 
	 * @param id
	 *            - entity's primary key
	 */
	public void removeByPk(I id);

}
