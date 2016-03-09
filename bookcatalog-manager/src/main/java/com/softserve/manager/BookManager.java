package com.softserve.manager;

import java.util.List;

import javax.ejb.Local;

import com.softserve.model.Book;

@Local
public interface BookManager {

	public void create(Book book);

	public void update(Book book);

	public void removeByPk(Integer id);

	public Book findById(Integer id);

	/**
	 * This method returns books by Author
	 * 
	 * @param a
	 *            - author
	 * @return list of books
	 */
	public List<Book> findBooksByAuthorId(Integer id);

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

	public List<Book> getAllAvailableBooks();

	/**
	 * This method return books by rating
	 * 
	 * @param rating
	 * @return list of books
	 */
	public List<Book> getBooksWithRating(Double rating);

	/**
	 * This method return number of books with corresponding rating
	 * 
	 * @param rating
	 * @return number of books
	 */

	public Integer calculateCountOfBook(Integer rating);

	/**
	 * This method returns number of books in data base
	 * 
	 * @return number of books
	 */
	public Integer findBookDataSize();

	/**
	 * This method removes books by primary keys
	 * 
	 * @param pks
	 *            - list of primary keys which will be removed
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
	 * This method search Book by ISBN
	 * 
	 * @param isbn
	 *            - unique number of book
	 * @return Book
	 */
	public Book findBookByISBN(String isbn);

	/**
	 * This method return 10 book with highest rating
	 * 
	 * @return list of books
	 */
	public List<Book> findTopBooks();

	/**
	 * This method search Books by status
	 * 
	 * @param status
	 *            - book status(available, unavailable)
	 * @return list of books
	 */
	public List<Book> findBooksByStatus(String status);

	/**
	 * This method calculate rating for existing book
	 * 
	 * @param book
	 */
	public void calculateBookRate(Book book);
}
