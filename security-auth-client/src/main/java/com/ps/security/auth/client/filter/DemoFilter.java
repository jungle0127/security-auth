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

public class DemoFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(DemoFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("demo filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("demo filter start");
		chain.doFilter(request, response);
		logger.info("demo filter end.");
	}

	@Override
	public void destroy() {
		logger.info("demo filter destroy");
	}

}
