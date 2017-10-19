/**  
 * @Title: TestBean.java
 * @Package com.test
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * @Description: TODO
 */
public class TestBean {

	@SerializedName("name")
	private String stuName;

	@SerializedName(value = "class", alternate = { "other", "className" })
	private String className;

	private double w_score;

	private double r_score;

	private Date date;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	/**
	 * @return the stuName
	 */
	public String getStuName() {
		return stuName;
	}

	/**
	 * @param stuName
	 *            the stuName to set
	 */
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the w_score
	 */
	public double getW_score() {
		return w_score;
	}

	/**
	 * @param w_score
	 *            the w_score to set
	 */
	public void setW_score(double w_score) {
		this.w_score = w_score;
	}

	/**
	 * @return the r_score
	 */
	public double getR_score() {
		return r_score;
	}

	/**
	 * @param r_score
	 *            the r_score to set
	 */
	public void setR_score(double r_score) {
		this.r_score = r_score;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
