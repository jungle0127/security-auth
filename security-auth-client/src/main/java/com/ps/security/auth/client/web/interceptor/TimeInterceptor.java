package com.ps.security.auth.client.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class TimeInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

	/**
	 * When the controller method finished.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("starttime");
		logger.info("Time interceptor takes time: " + (new Date().getTime() - startTime));
		logger.info("After the controller method finished..");
		if(ex != null) {
			logger.info("exp is: " + ex.getMessage());
		}
	}

	/**
	 * After the controller method, if no exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		logger.info("After the controller method, if no exception.");
		long startTime = (Long) request.getAttribute("starttime");
		logger.info("Time interceptor takes time: " + (new Date().getTime() - startTime));
	}

	/*
	 * Before the controller method
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Before the controller method.");
		logger.info(((HandlerMethod) handler).getBean().getClass().getName());
		logger.info(((HandlerMethod) handler).getMethod().getName());
		request.setAttribute("starttime", new Date().getTime());
		return true;
	}

}
