package com.geomarket.validation.validator;

import com.geomarket.validation.AbstractValidator;
import com.webileapps.navdrawer.R;

import android.content.Context;
/**
 * Validates if the inputed values are all numeric.
 * 
 * @author Dixon D'Cunha (Exikle)
 */
public class NumericValidator extends AbstractValidator {

	/**
	 * Default message if none specified
	 */
	private static final int DEFAULT_ERROR_MESSAGE_RESOURCE = R.string.validator_ip;

	public NumericValidator(Context c) {
		super(c, DEFAULT_ERROR_MESSAGE_RESOURCE);
	}

	public NumericValidator(Context c, int errorMessage) {
		super(c, errorMessage);
	}

	@Override
	public boolean isValid(String str) {
		return containsOnlyNumbers(str);
	}

	/**
	 * Checks if string has anything that isn't numbers.
	 * 
	 * @param str
	 * @return false only if null or not numeric
	 */
	private boolean containsOnlyNumbers(String str) {
		if (str == null || str.length() == 0)
			return false;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}
}
