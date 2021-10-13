package org.blog2.pojo;

/**
 * Author - Created by java/pojo/fields-clean.xml
 * 
 * @author - marnix
 */
public class Author {

	private String firstName;

	private String lastName;

	private Book book;

	/**
	 * Getter for property firstName.
	 * 
	 * @return value of property firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Setter for property firstName.
	 * 
	 * @param firstName new value of property firstName
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for property lastName.
	 * 
	 * @return value of property lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Setter for property lastName.
	 * 
	 * @param lastName new value of property lastName
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for property book.
	 * 
	 * @return value of property book
	 */
	public Book getBook() {
		return this.book;
	}

	/**
	 * Setter for property book.
	 * 
	 * @param book new value of property book
	 */
	public void setBook(final Book book) {
		this.book = book;
	}

}
