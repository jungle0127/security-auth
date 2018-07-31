package com.ps.security.auth.client.web.async;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private String placeHolder;
	private String completeHolder;

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) throws Exception {
		new Thread(() -> {
			logger.info("Receive order message..." + placeHolder);
			this.completeHolder = placeHolder;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				logger.info(e.getMessage());
			}
			logger.info("Order processed done." + placeHolder);
		}).start();
	}

	public String getCompleteHolder() {
		return completeHolder;
	}

	public void setCompleteHolder(String completeHolder) {
		this.completeHolder = completeHolder;
	}
}
