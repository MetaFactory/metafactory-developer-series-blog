package org.blog1.pojo;

import java.util.Objects;

public class Author {

	private String firstName;

	private String lastName;

	private Book book;

	/**
	 * equals - Fields used as businesskey: 1) firstName 2) lastName.
	 * 
	 * @param other the reference object with which to compare
	 * @return boolean true if this object is the same as the obj argument; false otherwise
	 */
	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof Author)) {
			return false;
		}

		final Author otherAuthor = (Author) other;

		boolean result;
		result = Objects.equals(getFirstName(), otherAuthor.getFirstName());

		result = result && Objects.equals(getLastName(), otherAuthor.getLastName());

		return result;
	}

	/**
	 * hashCode - Fields used as businesskey: 1) firstName 2) lastName.
	 * 
	 * @return integer a hash code value for this object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getFirstName(), getLastName());
	}

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
