package com.ps.security.auth.client.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
	private static final Logger logger = LoggerFactory.getLogger(TimeAspect.class);
	
	@Around("execution(* com.ps.security.auth.client.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("AOP start");
		
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg: args) {
			logger.info("arg is: " + arg);
		}
		
		long startTime = new Date().getTime();
		Object object = proceedingJoinPoint.proceed();		
		logger.info("AOP takes: " + (new Date().getTime() - startTime));
		logger.info("AOP end.");
		return object;
	}
}
