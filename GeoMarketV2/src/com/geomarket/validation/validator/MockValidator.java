package com.geomarket.validation.validator;

import com.geomarket.validation.AbstractValidator;

import android.content.Context;


/**
 * Just a mock validator to use as a coding place holder. Always returns true.
 * 
 * @author Dixon D'Cunha (Exikle)
 */
public class MockValidator extends AbstractValidator {

	/**
	 * Sets the error as "NO ERROR"
	 */
	private static final String DEFAULT_ERROR_MESSAGE_RESOURCE = "NO ERROR";

	public MockValidator(Context c) {
		super(c, DEFAULT_ERROR_MESSAGE_RESOURCE);
	}

	@Override
	public boolean isValid(String value) {
		return true;
	}

}
