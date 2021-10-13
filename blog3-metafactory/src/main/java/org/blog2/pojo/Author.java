package org.blog2.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Author - Created by java/pojo/fields-clean.xml Created by java/pojo/fields-lombok.xml Created by java/pojo/equals-and-hashcode-lombok.xml
 * 
 * @author - marnix
 */
@Getter
@Setter
@EqualsAndHashCode
public class Author {

	private String firstName;

	private String lastName;

	private Book book;

}
