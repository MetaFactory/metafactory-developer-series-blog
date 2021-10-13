package org.blog2.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Book - Created by java/pojo/fields-clean.xml Created by java/pojo/fields-lombok.xml Created by java/pojo/equals-and-hashcode-lombok.xml
 * 
 * @author - marnix
 */
@Getter
@Setter
@EqualsAndHashCode
public class Book {

	private String name;

	private String description;

	private Author author;

}
