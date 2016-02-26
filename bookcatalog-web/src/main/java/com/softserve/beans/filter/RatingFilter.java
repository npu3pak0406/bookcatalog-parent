package com.softserve.beans.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.softserve.model.Book;

@ManagedBean
@ViewScoped
public class RatingFilter implements Serializable {

	private static final long serialVersionUID = -3344489645281112447L;

	private Long minRatingFilter = new Long(0);
	private Long maxRatingFilter = new Long(10);
	private Long firstUsedFilter = new Long(0);
	private Long secondUsedFilter = new Long(10);

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
		Long minRating = getFirstUsedFilter();
		Long maxRating = getSecondUsedFilter();
		if ((minRating == null || minRating == 0
				|| minRating.compareTo(currentBook.getAverageRating().longValue()) <= 0)
				&& (maxRating == null || maxRating.compareTo(currentBook.getAverageRating().longValue()) >= 0)) {
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

			minRatingFilter = Long.parseLong(minRat);
			maxRatingFilter = Long.parseLong(maxRat);
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

	public Long getMinRatingFilter() {
		return minRatingFilter;
	}

	public void setMinRatingFilter(Long minRatingFilter) {
		this.minRatingFilter = minRatingFilter;
	}

	public Long getMaxRatingFilter() {
		return maxRatingFilter;
	}

	public void setMaxRatingFilter(Long maxRatingFilter) {
		this.maxRatingFilter = maxRatingFilter;
	}

	public Long getFirstUsedFilter() {
		return firstUsedFilter;
	}

	public void setFirstUsedFilter(Long firstUsedFilter) {
		this.firstUsedFilter = firstUsedFilter;
	}

	public Long getSecondUsedFilter() {
		return secondUsedFilter;
	}

	public void setSecondUsedFilter(Long secondUsedFilter) {
		this.secondUsedFilter = secondUsedFilter;
	}

}
