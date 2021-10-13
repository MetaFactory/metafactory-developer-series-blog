package org.metafactory.model.runtime.domainmodel;

import com.metafactory.api.InvalidPatternException;
import com.metafactory.api.model.ModelPackage;
import com.metafactory.api.model.ModelRoot;

/**
 * RuntimeDomainModelModelPackage - Created by MetaFactory: Automation of Software Development
 * 
 * @author - marnix
 */
public class RuntimeDomainModelModelPackage {

	private final ModelRoot modelRoot;

	private final ModelPackage modelPackage;

	/** A reference to the Book entity defined in the model package domain_model */
	private final Book book;

	/** A reference to the Author entity defined in the model package domain_model */
	private final Author author;

	/**
	 * constructor -
	 * 
	 * @param modelPackage A reference to model-api ModelPackage for model package domain_model
	 * @throws InvalidPatternException
	 */
	public RuntimeDomainModelModelPackage(final ModelPackage modelPackage) throws InvalidPatternException {
		this.modelPackage = modelPackage;
		this.modelRoot = modelPackage.getRoot();

		this.book = Book.getInstance(modelPackage.findObjectByName("Book"));
		this.author = Author.getInstance(modelPackage.findObjectByName("Author"));
	}

	/**
	 * Getter for property modelPackage.
	 * 
	 * @return value of property modelPackage
	 */
	public ModelPackage getModelPackage() {
		return this.modelPackage;
	}

	/**
	 * Getter for property book.
	 * 
	 * @return value of property book
	 */
	public Book getBook() {
		return this.book;
	}

	/**
	 * Getter for property author.
	 * 
	 * @return value of property author
	 */
	public Author getAuthor() {
		return this.author;
	}

}
