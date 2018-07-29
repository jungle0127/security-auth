/**
 * 
 */
package com.ps.security.auth.client.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Administrator
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {


	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
