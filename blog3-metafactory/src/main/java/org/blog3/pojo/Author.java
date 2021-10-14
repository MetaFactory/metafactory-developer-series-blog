package org.blog3.pojo;

import java.util.Objects;

/**
 * Author - Created by java/pojo/fields-clean.xml Created by java/pojo/equals-and-hashcode-java8.xml Created by java/pojo/tostring-java8.xml
 * 
 * @author - marnix
 */
public class Author {

	private String firstName;

	private String lastName;

	private Book book;

	/**
	 * equals - Fields used as business key: 1) firstName 2) lastName 3) book.
	 * 
	 * @param other The reference object with which to compare.
	 * @return boolean Return true if this object is the same as the argument object, otherwise return false.
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

		result = result && Objects.equals(getBook(), otherAuthor.getBook());

		return result;
	}

	/**
	 * hashCode - Fields used as business key: 1) firstName 2) lastName 3) book.
	 * 
	 * @return integer A hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getFirstName(), getLastName(), getBook());
	}

	/**
	 * toString - Fields used as business key: 1) firstName 2) lastName 3) book.
	 * 
	 * @return String Return String representation of Author
	 */
	@Override
	public String toString() {
		return "Author [firstName=" + getFirstName() + ", lastName=" + getLastName() + ", book=" + getBook() + "]";
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
