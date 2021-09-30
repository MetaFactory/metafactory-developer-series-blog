package org.metafactory.model.runtime.domainmodel;

import com.metafactory.api.InvalidPatternException;
import com.metafactory.api.model.ModelAttribute;
import com.metafactory.api.model.ModelObject;
import com.metafactory.api.model.ModelPackage;
import com.metafactory.api.model.ModelReference;

/**
 * Author - Created by MetaFactory: Automation of Software Development
 * 
 * @author - marnix
 */
public class Author {

	private static Author instance;

	private final ModelPackage modelPackage;

	private final ModelObject modelObject;

	private final ModelAttribute firstName;

	private final ModelAttribute lastName;

	private ModelReference book;

	private Book bookRuntime;

	/**
	 * getInstance -
	 * 
	 * @param authorModelObject The model object named ${${object.name}}
	 * @return Author The runtime model representation of model object named Author
	 * @throws InvalidPatternException
	 */
	public static Author getInstance(final ModelObject authorModelObject) throws InvalidPatternException {
		if (instance == null) {

			new Author(authorModelObject); // instance is set in constructor
		}
		return instance;
	}

	/**
	 * constructor -
	 * 
	 * @param modelObject A reference to model-api ModelObject for entity Author
	 * @throws InvalidPatternException
	 */
	public Author(final ModelObject modelObject) throws InvalidPatternException {
		// Reference to this must be set immediately to prevent a loop during initialization
		this.instance = this;
		this.modelObject = modelObject;
		this.modelPackage = modelObject.getModelPackage();

		// Initialize all ModelAttribute fields
		this.firstName = modelObject.findAttributeByName("firstName");
		this.lastName = modelObject.findAttributeByName("lastName");

		// Initialize all ModelReference fields
		this.book = modelObject.findReferenceByName("book");
		this.bookRuntime = Book.getInstance(modelPackage.findObjectByName("Book"));
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
	 * Getter for property firstName.
	 * 
	 * @return value of property firstName
	 */
	public ModelAttribute getFirstName() {
		return this.firstName;
	}

	/**
	 * Getter for property lastName.
	 * 
	 * @return value of property lastName
	 */
	public ModelAttribute getLastName() {
		return this.lastName;
	}

	/**
	 * Getter for property book.
	 * 
	 * @return value of property book
	 */
	public ModelReference getBook() {
		return this.book;
	}

	/**
	 * Getter for property bookRuntime.
	 * 
	 * @return value of property bookRuntime
	 */
	public Book getBookRuntime() {
		return this.bookRuntime;
	}

}
