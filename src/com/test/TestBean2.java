/**  
 * @Title: TestBean2.java
 * @Package com.test
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test;

/**
 * @ClassName: TestBean2
 * @Description: TODO
 */
public class TestBean2 {

	private int bookId;
	private String name;
	private String author;
	private float price;
	private String isbn;
	private String pubName;
	private byte[] preface;

	public TestBean2(int bookId, String name, String author, float price, String isbn, String pubName, byte[] preface) {
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.pubName = pubName;
		this.preface = preface;
	}

	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * @param bookId
	 *            the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn
	 *            the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the pubName
	 */
	public String getPubName() {
		return pubName;
	}

	/**
	 * @param pubName
	 *            the pubName to set
	 */
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}

	/**
	 * @return the preface
	 */
	public byte[] getPreface() {
		return preface;
	}

	/**
	 * @param preface
	 *            the preface to set
	 */
	public void setPreface(byte[] preface) {
		this.preface = preface;
	}
}
