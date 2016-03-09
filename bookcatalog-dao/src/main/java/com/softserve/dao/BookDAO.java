package com.softserve.dao;

import java.util.List;

import com.softserve.model.Book;

/**
 * BookDAO interface for facade and read operations
 * 
 *
 */
public interface BookDAO extends GenericDAO<Book, Integer> {

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
	 * This method removes books 
	 * 
	 * @param books
	 *            - list of books which will be removed
	 */
	public void removeBooks(List<Book> books);

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
	 *            - unique number of book
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
	

	public List<Book> findBooksByAuthorId(Integer id);

}
