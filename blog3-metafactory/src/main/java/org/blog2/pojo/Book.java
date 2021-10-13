package org.blog2.pojo;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Book - Created by java/pojo/fields-clean.xml Created by java/pojo/fields-lombok.xml Created by java/pojo/equals-and-hashcode-java8.xml
 * 
 * @author - marnix
 */
@Getter
@Setter
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

		boolean result;

		result = Objects.equals(getName(), otherBook.getName());

		result = result && Objects.equals(getDescription(), otherBook.getDescription());

		return result;
	}

	/**
	 * hashCode - Fields used as business key: 1) name 2) description.
	 * 
	 * @return integer A hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getName(), getDescription());
	}

}
