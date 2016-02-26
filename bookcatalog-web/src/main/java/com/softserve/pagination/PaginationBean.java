package com.softserve.pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.softserve.manager.BookManager;
import com.softserve.manager.ReviewManager;
import com.softserve.model.Book;

@ManagedBean
@ViewScoped
public class PaginationBean {

	@EJB
	private ReviewManager reviewService;
	@EJB
	private BookManager bookManager;

	private List<Book> bookList = new ArrayList<Book>();
	private ArrayList<Integer> pageNumbers = new ArrayList<Integer>();
	private ArrayList<Integer> rowsOptions = new ArrayList<Integer>();
	private Integer totalReviewsCount = null;
	private Integer reviewsOnPage = 10;
	private Integer selectedPageNumber = 1;

	public List<Book> getBooksss() {

		totalReviewsCount = bookManager.findBookDataSize();
		bookList = bookManager.findBooks(selectedPageNumber, reviewsOnPage);

		fillPageNumbers(totalReviewsCount, reviewsOnPage);
		return bookList;
	}

	public void selectPage() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		selectedPageNumber = Integer.valueOf(params.get("page_number"));
		getBooksss();
	}

	private void fillPageNumbers(int totalReviewsCount, int booksCountOnPage) {
		int pageCount = totalReviewsCount > 0 ? (int) (totalReviewsCount / booksCountOnPage) : 0;
		pageNumbers.clear();
		if (pageCount == 0)
			pageNumbers.add(1);
		for (int i = 1; i <= pageCount; i++) {
			pageNumbers.add(i);
		}
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public ArrayList<Integer> getPageNumbers() {
		return pageNumbers;
	}

	public void setPageNumbers(ArrayList<Integer> pageNumbers) {
		this.pageNumbers = pageNumbers;
	}

	public ArrayList<Integer> getRowsOptions() {
		return rowsOptions;
	}

	public void setRowsOptions(ArrayList<Integer> rowsOptions) {
		this.rowsOptions = rowsOptions;
	}

	public Integer getTotalReviewsCount() {
		return totalReviewsCount;
	}

	public void setTotalReviewsCount(Integer totalReviewsCount) {
		this.totalReviewsCount = totalReviewsCount;
	}

	public Integer getReviewsOnPage() {
		return reviewsOnPage;
	}

	public void setReviewsOnPage(Integer reviewsOnPage) {
		this.reviewsOnPage = reviewsOnPage;
	}

	public Integer getSelectedPageNumber() {
		return selectedPageNumber;
	}

	public void setSelectedPageNumber(Integer selectedPageNumber) {
		this.selectedPageNumber = selectedPageNumber;
	}
}
