package com.ps.security.auth.client.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ps.security.auth.client.service.IConstraintService;
@Service
public class ConstraintService implements IConstraintService {
	private static final Logger logger = LoggerFactory.getLogger(ConstraintService.class);
	@Override
	public void constraint() {
		logger.info("Constraint service");
	}

}
