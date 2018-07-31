package com.ps.security.auth.client.web.async;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class MockQueue {
	private static final Logger logger = LoggerFactory.getLogger(MockQueue.class);
	private String placeHolder;
	private String completeHolder;
	public String getPlaceHolder() {
		return placeHolder;
	}
	public void setPlaceHolder(String placeHolder) throws Exception {
		logger.info("Receive order message..." + placeHolder);
		this.completeHolder = placeHolder;
		TimeUnit.SECONDS.sleep(1);
		logger.info("Order processed done." + placeHolder);
	}
	public String getCompleteHolder() {
		return completeHolder;
	}
	public void setCompleteHolder(String completeHolder) {
		this.completeHolder = completeHolder;
	}
}
