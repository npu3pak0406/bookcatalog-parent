package com.softserve.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.softserve.model.Status;

@ManagedBean
@RequestScoped
public class BeanHelper implements Serializable {

	private static final long serialVersionUID = -927986150878814770L;
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
		for (int year = MIN_YEAR; year <= MAX_YEAR; year++)
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
