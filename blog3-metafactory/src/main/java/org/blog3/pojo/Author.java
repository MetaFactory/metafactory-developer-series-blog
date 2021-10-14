package org.blog3.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author - Created by java/pojo/fields-clean.xml Created by java/pojo/fields-lombok.xml Created by java/pojo/equals-and-hashcode-lombok.xml Created by
 * java/pojo/tostring-lombok.xml
 * 
 * @author - marnix
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Author {

	@EqualsAndHashCode.Include
	@ToString.Include
	private String firstName;

	@EqualsAndHashCode.Include
	@ToString.Include
	private String lastName;

	private Book book;

}
