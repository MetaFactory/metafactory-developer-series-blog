package org.blog3.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Book - Created by java/pojo/fields-clean.xml Created by java/pojo/fields-lombok.xml Created by java/pojo/equals-and-hashcode-lombok.xml Created by
 * java/pojo/tostring-lombok.xml
 * 
 * @author - marnix
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Book {

	private String name;

	private String description;

	private Author author;

}
