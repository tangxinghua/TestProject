/**  
 * @Title: Article.java
 * @Package com.test.crawler
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.crawler;

/**
 * @ClassName: Article
 * @Description: 文章的JavaBean.
 */
public class Article {

	/**
	 * 文章链接的相对地址
	 */
	private String address;

	/**
	 * 文章标题
	 */
	private String title;

	/**
	 * 文章简介
	 */
	private String desption;

	/**
	 * 文章发表时间
	 */
	private String time;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesption() {
		return desption;
	}

	public void setDesption(String desption) {
		this.desption = desption;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
