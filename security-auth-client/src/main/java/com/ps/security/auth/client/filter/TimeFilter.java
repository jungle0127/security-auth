package com.ps.security.auth.client.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
//@Component
public class TimeFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(TimeFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("Init demo filter.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("time filter start");
		chain.doFilter(request, response);
		logger.info("time filter finished.");
	}

	@Override
	public void destroy() {
		logger.info("filter destroyed.");
	}

}
