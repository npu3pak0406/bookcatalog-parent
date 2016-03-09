package com.softserve.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.softserve.manager.BookManager;
import com.softserve.manager.ReviewManager;
import com.softserve.model.Status;

@ManagedBean
@RequestScoped
public class BeanHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ReviewManager reviewManager;

	@EJB
	private BookManager bookManager;

	private List<Integer> bookCount = new ArrayList<Integer>();
	private static final Integer YEAR_HELPER = 100;
	private static Integer MIN_YEAR;
	private static Integer MAX_YEAR;

	private List<Integer> years = new ArrayList<Integer>();

	public BeanHelper() {
		MAX_YEAR = Calendar.getInstance().get(Calendar.YEAR);
		MIN_YEAR = MAX_YEAR - YEAR_HELPER;
		fillYears();
	}

	private void fillYears() {
		for (int year = MAX_YEAR; year >= MIN_YEAR; year--)
			years.add(year);

	}

	public SelectItem[] getStatusValues() {
		SelectItem[] items = new SelectItem[Status.values().length];
		int i = 0;
		for (Status status : Status.values()) {
			items[i++] = new SelectItem(status, status.getLabel());
		}
		return items;
	}

	private void fillBookCount() {
		for (int i = 0; i < 10; i++) {
			bookCount.add(bookManager.findBooksCountBitweenRating((double) i, (double) (i + 1)));
		}
	}

	public List<Integer> getBookCount() {
		fillBookCount();
		return bookCount;
	}

	public void setBookCount(List<Integer> bookCount) {
		this.bookCount = bookCount;
	}

	public static Integer getMAX_YEAR() {
		return MAX_YEAR;
	}

	public static void setMAX_YEAR(Integer mAX_YEAR) {
		MAX_YEAR = mAX_YEAR;
	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	public static Integer getMinYear() {
		return MIN_YEAR;
	}

}
