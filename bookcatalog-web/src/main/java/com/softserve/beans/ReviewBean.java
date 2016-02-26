package com.softserve.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.softserve.manager.BookManager;

@ManagedBean
@ViewScoped
public class ReviewBean implements Serializable {

	private static final long serialVersionUID = -7215527941113740773L;

	@EJB
	private BookManager bookManager;

	List<Integer> bookCount = new ArrayList<Integer>();

	private void fillBookCount() {
		 for (int i = 0; i < 10; i++) {
		 bookCount.add(bookManager.findBooksCountBitweenRating((double)i, (double)(i + 1)));
		 }
	}

	public List<Integer> getBookCount() {
		fillBookCount();
		return bookCount;
	}

	public void setBookCount(List<Integer> bookCount) {
		this.bookCount = bookCount;
	}

}
