package com.softserve.manager;

import java.util.List;

import javax.ejb.Local;

import com.softserve.model.Author;
import com.softserve.model.Book;

@Local
public interface AuthorManager {

	public Author findById(Integer id);

	public void removeByPk(Integer id);

	public void create(Author newAuthor);

	public void update(Author currentAuthor);
	
	public List<Author> findAllAuthors();

	public Integer countAllAuthors();

	public void removeAllByPk(List<Integer> selectedAuthors);

	public void calculateAuthorRate(Book book);


}
