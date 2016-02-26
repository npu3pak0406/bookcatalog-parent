package com.softserve.manager;

import java.util.List;

import javax.ejb.Local;

import com.softserve.model.Author;
import com.softserve.model.Book;

@Local
public interface BookManager {

	public void create(Book book);

	public void update(Book book);

	public void removeByPk(Integer id);

	public Book findBookById(Integer id);

	public List<Book> getAllBooksByAuthor(Author a);

	public List<Book> returnAllBooks();

	public List<Book> getAllAvailableBooks();

	public List<Book> getBooksWithRating(Double rating);

	public Integer calculateCountOfBook(Integer rating);

	public List<Book> findBooks(int page, int maxPerPage);

	public Integer findBookDataSize();

	public void removeAllByPk(List<Integer> pks);

	public void calculateBookRate(Book book);

	public Integer findBooksCountBitweenRating(double minRating, double maxRating);

	public Book findBookByISBN(String val);

	public List<Book> findTopBooks();

	public List<Book> findBooksByStatus(String status);

}
