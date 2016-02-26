package com.softserve.dao;

import java.util.List;

import javax.ejb.Local;

import com.softserve.model.Author;

@Local
public interface AuthorDAO extends GenericDAO<Author, Integer> {
	
	/**
	 * This method returns all authors from Data Base
	 * @return
	 */
	public List<Author> findAllAuthors();

	/**
	 * This method returns Author by unique field (id)
	 * @return
	 */
	public Author findById(Integer id);

	/**
	 * This method returns number of Authors saved in Data Base
	 * @return
	 */
	public Integer countAllAuthors();

	/**
	 * This method removes authors by primary keys
	 * @return
	 */
	public void removeAllById(List<Integer> pks);

	

}
