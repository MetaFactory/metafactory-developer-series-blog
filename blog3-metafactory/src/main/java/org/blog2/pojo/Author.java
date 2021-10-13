package org.blog2.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Author - Created by java/pojo/fields-clean.xml Created by java/pojo/equals-and-hashcode-apache.xml
 * 
 * @author - marnix
 */
public class Author {

	private String firstName;

	private String lastName;

	private Book book;

	/**
	 * equals - Fields used as business key: 1) firstName 2) lastName.
	 * 
	 * @param other The reference object with which to compare.
	 * @return boolean Return true if this object is the same as the argument object, otherwise return false.
	 */
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof Author)) {
			return false;
		}

		final Author otherAuthor = (Author) other;
		return new EqualsBuilder().append(this.getFirstName(), otherAuthor.getFirstName())

				.append(this.getLastName(), otherAuthor.getLastName())

				.isEquals();
	}

	/**
	 * hashCode - Fields used as business key: 1) firstName 2) lastName.
	 * 
	 * @return integer A hash code value for this object.
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(this.getFirstName())

				.append(this.getLastName())

				.toHashCode();
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
