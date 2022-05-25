package org.metafactory.model.runtime;

import org.metafactory.model.runtime.domainmodel.RuntimeDomainModelModelPackage;

import com.metafactory.api.InvalidPatternException;
import com.metafactory.api.model.ModelRoot;

public class RuntimeModel {

	private final ModelRoot modelRoot;

	private final RuntimeDomainModelModelPackage domain_model;

	/**
	 * constructor -
	 * 
	 * @param modelRoot A reference to model-api ModelRoot containing the complete model including all model packages.
	 * @throws InvalidPatternException
	 */
	public RuntimeModel(final ModelRoot modelRoot) throws InvalidPatternException {
		this.modelRoot = modelRoot;

		this.domain_model = new RuntimeDomainModelModelPackage(modelRoot.findPackageByName("domain_model"));
	}

	/**
	 * Getter for property domain_model.
	 * 
	 * @return value of property domain_model
	 */
	public RuntimeDomainModelModelPackage getDomain_model() {
		return this.domain_model;
	}

}
