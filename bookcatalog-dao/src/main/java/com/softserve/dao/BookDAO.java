package com.softserve.dao;

import java.util.List;

import javax.ejb.Local;

import com.softserve.model.Author;
import com.softserve.model.Book;

@Local
public interface BookDAO extends GenericDAO<Book, Integer> {

	/**
	 * This method returns books by Author
	 * 
	 * @param a
	 *            - author
	 * @return list of books
	 */
	public List<Book> findAllBooksByAuthor(Author a);

	/**
	 * This method returns all books from data base
	 * 
	 * @return list of books
	 */
	public List<Book> findAllBooks();

	/**
	 * This method return all available books
	 * 
	 * @return list of books
	 */
	public List<Book> findAllAvailableBooks();

	/**
	 * This method return books by rating
	 * 
	 * @param rating
	 * @return list of books
	 */
	public List<Book> findBooksWithRating(Double rating);

	/**
	 * This method returns books by unique field
	 * 
	 * @param id
	 *            - primary key
	 * @return Book
	 */
	public Book findBookById(Integer id);

	/**
	 * This method returns books for one table page
	 * 
	 * @param startingAt
	 *            - number of page
	 * @param maxPerPage
	 *            - count of books on page
	 * @return list of books
	 */

	public List<Book> findBooks(int startingAt, int maxPerPage);

	/**
	 * This method returns number of books in data base
	 * 
	 * @return number of books
	 */
	public Integer countAllBooks();

	/**
	 * This method removes books by primary keys
	 * 
	 * @param pks
	 *            - list of primary keys which will be removed
	 */
	public void removeAllbyId(List<Integer> pks);

	/**
	 * This method returns number of books with identified ratings
	 * 
	 * @param minRating
	 *            - lower limit
	 * @param maxRating
	 *            -upper limit
	 * @return
	 */
	public Integer findBooksCountBitweenRating(double minRating, double maxRating);

	/**
	 * This method return 10 book with highest rating
	 * 
	 * @return list of books
	 */
	public List<Book> findTopBooks();

	/**
	 * This method search Book by ISBN
	 * 
	 * @param isbn
	 * @return Book
	 */
	public Book findBookByISBN(String isbn);

	/**
	 * This method search Books by status
	 * 
	 * @param status
	 *            - book status(available, unavailable)
	 * @return list of books
	 */
	public List<Book> findBooksByStatus(String status);

}
