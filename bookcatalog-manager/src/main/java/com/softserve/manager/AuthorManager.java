package com.softserve.manager;

import java.util.List;

import javax.ejb.Local;

import com.softserve.model.Author;
import com.softserve.model.Book;

@Local
public interface AuthorManager {

	public Author findById(Integer id);

	public void removeById(Integer id);

	public void create(Author author);

	public void update(Author author);

	/**
	 * This method returns all authors from Data Base
	 * 
	 * @return list of authors
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
	 * @param authors
	 *            - authors which will be removed
	 * 
	 */
	public void removeAuthors(List<Author> authors);

	/**
	 * This method refresh author's rating which related with current book
	 * 
	 * @param book
	 */

	public void calculateAuthorRate(Book book);

	/**
	 * ReCalculate authors rating which were related to book which was removed.
	 * 
	 * @param book
	 *            - entity which will be removed
	 */

	public void recalcRatingOndelete(Book book);

}
