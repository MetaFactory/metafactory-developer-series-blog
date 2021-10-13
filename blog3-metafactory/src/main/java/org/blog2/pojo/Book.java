package org.blog2.pojo;

/**
 * Book - Created by java/pojo/fields-clean.xml
 * 
 * @author - marnix
 */
public class Book {

	private String name;

	private String description;

	private Author author;

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
