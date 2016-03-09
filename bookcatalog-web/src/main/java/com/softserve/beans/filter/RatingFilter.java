package com.softserve.beans.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.softserve.manager.BookManager;
import com.softserve.model.Book;

@ManagedBean
@ViewScoped
public class RatingFilter implements Serializable {

	private static final long serialVersionUID = -3344489645281112447L;

	@EJB
	private BookManager bookManager;

	private Double minRatingFilter = new Double(0);
	private Double maxRatingFilter = new Double(10);
	private Double firstUsedFilter = new Double(0);
	private Double secondUsedFilter = new Double(10);

	private List<Long> firstRatingOptions = new ArrayList<Long>();
	private List<Long> secondRatingOptions = new ArrayList<Long>();

	public RatingFilter() {
		if (firstRatingOptions.isEmpty())
			fillFirstRatingList(firstRatingOptions);
		if (secondRatingOptions.isEmpty())
			fillSecondRatingList(secondRatingOptions);
	}

	public boolean getRatingFilterImpl(Object book) {

		getMaxMinFilters();
		Book currentBook = (Book) book;
		Double minRating = getFirstUsedFilter();
		Double maxRating = getSecondUsedFilter();
		if ((minRating == null || minRating == 0 || minRating.compareTo(currentBook.getAverageRating()) <= 0)
				&& (maxRating == null || maxRating.compareTo(currentBook.getAverageRating()) >= 0)) {
			return true;
		}
		return false;
	}

	private void getMaxMinFilters() {
		getMinAndMaxRatFilters();
		if (minRatingFilter >= maxRatingFilter) {
			firstUsedFilter = maxRatingFilter;
			secondUsedFilter = minRatingFilter;
		} else {
			firstUsedFilter = minRatingFilter;
			secondUsedFilter = maxRatingFilter;
		}
	}

	/*
	 * filling lists
	 */
	private void fillFirstRatingList(List<Long> list) {
		for (long i = 0; i < 11; i++)
			list.add(i);
	}

	private void fillSecondRatingList(List<Long> list) {
		for (long i = 10; i > -1; i--)
			list.add(i);
	}

	public void getMinAndMaxRatFilters() {

		String minRat = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("minRating");
		String maxRat = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("maxRating");

		if ((minRat != null) && (maxRat != null)) {

			minRatingFilter = Double.parseDouble(minRat);
			maxRatingFilter = Double.parseDouble(maxRat);
		}
	}

	public List<Long> getFirstRatingOptions() {
		return firstRatingOptions;
	}

	public void setFirstRatingOptions(List<Long> firstRatingOptions) {
		this.firstRatingOptions = firstRatingOptions;
	}

	public List<Long> getSecondRatingOptions() {
		return secondRatingOptions;
	}

	public void setSecondRatingOptions(List<Long> secondRatingOptions) {
		this.secondRatingOptions = secondRatingOptions;
	}

	public Double getMinRatingFilter() {
		return minRatingFilter;
	}

	public void setMinRatingFilter(Double minRatingFilter) {
		this.minRatingFilter = minRatingFilter;
	}

	public Double getMaxRatingFilter() {
		return maxRatingFilter;
	}

	public void setMaxRatingFilter(Double maxRatingFilter) {
		this.maxRatingFilter = maxRatingFilter;
	}

	public Double getFirstUsedFilter() {
		return firstUsedFilter;
	}

	public void setFirstUsedFilter(Double firstUsedFilter) {
		this.firstUsedFilter = firstUsedFilter;
	}

	public Double getSecondUsedFilter() {
		return secondUsedFilter;
	}

	public void setSecondUsedFilter(Double secondUsedFilter) {
		this.secondUsedFilter = secondUsedFilter;
	}

	public int getBookCount() {
		return bookManager.findBooksCountBitweenRating(getFirstUsedFilter(), getSecondUsedFilter());
	}

}
