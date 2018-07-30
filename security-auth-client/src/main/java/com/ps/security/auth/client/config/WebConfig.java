package com.ps.security.auth.client.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ps.security.auth.client.filter.DemoFilter;

@Configuration
public class WebConfig {
	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean regBean = new FilterRegistrationBean();
		DemoFilter demoFilter = new DemoFilter();
		regBean.setFilter(demoFilter);
		List<String> urlList = new ArrayList<>();
		urlList.add("/*");
		regBean.setUrlPatterns(urlList);
		return regBean;
	}
}
