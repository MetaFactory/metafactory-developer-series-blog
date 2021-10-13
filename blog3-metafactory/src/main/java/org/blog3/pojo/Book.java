package org.blog3.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Book - Created by java/pojo/fields-clean.xml Created by java/pojo/equals-and-hashcode-apache.xml Created by java/pojo/tostring-apache.xml
 * 
 * @author - marnix
 */
public class Book {

	private String name;

	private String description;

	private Author author;

	/**
	 * equals - Fields used as business key: 1) name 2) description.
	 * 
	 * @param other The reference object with which to compare.
	 * @return boolean Return true if this object is the same as the argument object, otherwise return false.
	 */
	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}

		if (!(other instanceof Book)) {
			return false;
		}

		final Book otherBook = (Book) other;
		return new EqualsBuilder().append(this.getName(), otherBook.getName())

				.append(this.getDescription(), otherBook.getDescription())

				.isEquals();
	}

	/**
	 * hashCode - Fields used as business key: 1) name 2) description.
	 * 
	 * @return integer A hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getName())

				.append(this.getDescription())

				.toHashCode();
	}

	/**
	 * toString -
	 * 
	 * @return String Return String representation of Book
	 */
	@Override
	public String toString() {
		return new ToStringBuilder().append("name", getName()).append("description", getDescription()).toString();
	}

	/**
	 * Getter for property name.
	 * 
	 * @return value of property name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter for property name.
	 * 
	 * @param name new value of property name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Getter for property description.
	 * 
	 * @return value of property description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Setter for property description.
	 * 
	 * @param description new value of property description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Getter for property author.
	 * 
	 * @return value of property author
	 */
	public Author getAuthor() {
		return this.author;
	}

	/**
	 * Setter for property author.
	 * 
	 * @param author new value of property author
	 */
	public void setAuthor(final Author author) {
		this.author = author;
	}

}
