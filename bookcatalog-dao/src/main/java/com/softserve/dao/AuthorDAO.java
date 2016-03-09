package com.softserve.dao;

import java.util.List;
import javax.ejb.Local;

import com.softserve.model.Author;

/**
 * AuthorDAO interface for facade and read operations
 */
@Local
public interface AuthorDAO extends GenericDAO<Author, Integer> {

	/**
	 * This method returns all authors from Data Base
	 * 
	 * @return
	 */
	public List<Author> findAllAuthors();

	/**
	 * This method returns number of Authors saved in Data Base
	 * 
	 * @return
	 */
	public Integer countAllAuthors();

	/**
	 * This method removes authors
	 * 
	 * @return
	 */
	public void removeAuthors(List<Author> authors);

	/**
	 * This method returns Authors by Book id
	 * 
	 * @param id
	 *            - book's primary key
	 * @return
	 */
	public List<Author> findAuthorsByBookId(Integer id);
}
