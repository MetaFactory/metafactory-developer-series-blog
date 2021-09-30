package org.metafactory.model.runtime.domainmodel;

import com.metafactory.api.InvalidPatternException;
import com.metafactory.api.model.ModelAttribute;
import com.metafactory.api.model.ModelObject;
import com.metafactory.api.model.ModelPackage;
import com.metafactory.api.model.ModelReference;

/**
 * Book - Created by MetaFactory: Automation of Software Development
 * 
 * @author - marnix
 */
public class Book {

	private static Book instance;

	private final ModelPackage modelPackage;

	private final ModelObject modelObject;

	private final ModelAttribute name;

	private final ModelAttribute description;

	private ModelReference author;

	private Author authorRuntime;

	/**
	 * getInstance -
	 * 
	 * @param bookModelObject The model object named ${${object.name}}
	 * @return Book The runtime model representation of model object named Book
	 * @throws InvalidPatternException
	 */
	public static Book getInstance(final ModelObject bookModelObject) throws InvalidPatternException {
		if (instance == null) {

			new Book(bookModelObject); // instance is set in constructor
		}
		return instance;
	}

	/**
	 * constructor -
	 * 
	 * @param modelObject A reference to model-api ModelObject for entity Book
	 * @throws InvalidPatternException
	 */
	public Book(final ModelObject modelObject) throws InvalidPatternException {
		// Reference to this must be set immediately to prevent a loop during initialization
		this.instance = this;
		this.modelObject = modelObject;
		this.modelPackage = modelObject.getModelPackage();

		// Initialize all ModelAttribute fields
		this.name = modelObject.findAttributeByName("name");
		this.description = modelObject.findAttributeByName("description");

		// Initialize all ModelReference fields
		this.author = modelObject.findReferenceByName("author");
		this.authorRuntime = Author.getInstance(modelPackage.findObjectByName("Author"));
	}

	/**
	 * Getter for property modelObject.
	 * 
	 * @return value of property modelObject
	 */
	public ModelObject getModelObject() {
		return this.modelObject;
	}

	/**
	 * Getter for property name.
	 * 
	 * @return value of property name
	 */
	public ModelAttribute getName() {
		return this.name;
	}

	/**
	 * Getter for property description.
	 * 
	 * @return value of property description
	 */
	public ModelAttribute getDescription() {
		return this.description;
	}

	/**
	 * Getter for property author.
	 * 
	 * @return value of property author
	 */
	public ModelReference getAuthor() {
		return this.author;
	}

	/**
	 * Getter for property authorRuntime.
	 * 
	 * @return value of property authorRuntime
	 */
	public Author getAuthorRuntime() {
		return this.authorRuntime;
	}

}
