package com.ps.security.auth.client.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class ControllerMethodsAspect {
	private static final Logger logger = LoggerFactory.getLogger(ControllerMethodsAspect.class);
	@Pointcut("within(com.ps.security.auth.client..controller.*)")
	public void controllerMethod() {
		
	}
	@Before("controllerMethod()")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Before the method");
	}
	@After("controllerMethod()")
	public void logAfter(JoinPoint joinPoint) {
		logger.info("After the method");
	}
}
