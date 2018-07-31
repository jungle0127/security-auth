package com.ps.security.auth.client.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ps.security.auth.client.filter.DemoFilter;
import com.ps.security.auth.client.web.interceptor.TimeInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private TimeInterceptor timeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}
	// TODO:

	// @Override
	// public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
	// configurer.registerCallableInterceptors(interceptors);
	// configurer.registerDeferredResultInterceptors(interceptors);
	// }

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
