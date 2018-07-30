/**
 * 
 */
package com.ps.security.auth.client.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ps.security.auth.client.service.IConstraintService;

/**
 * @author Administrator
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	private static final Logger logger = LoggerFactory.getLogger(MyConstraintValidator.class);
	@Autowired
	private IConstraintService constraintService;

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		this.constraintService.constraint();
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		logger.info("Demo constraint validator.");
		return false;
	}

}
