package org.blog2.pojo;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Author - Created by java/pojo/fields-clean.xml Created by java/pojo/fields-lombok.xml Created by java/pojo/equals-and-hashcode-java8.xml
 * 
 * @author - marnix
 */
@Getter
@Setter
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
	 * hashCode - Fields used as business key: 1) firstName 2) lastName.
	 * 
	 * @return integer A hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getFirstName(), getLastName());
	}

}
