package com.ps.security.auth.client.web.async;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);
	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferResultHolder deferResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		new Thread(() -> {
			while (true) {
				if (StringUtils.isNotBlank(this.mockQueue.getCompleteHolder())) {
					String orderNumber = mockQueue.getCompleteHolder();
					logger.info("Got order processed number: " + orderNumber);
					deferResultHolder.getMap().get(orderNumber).setResult("place order success");
					mockQueue.setCompleteHolder(null);
				} else {
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						logger.info(e.getMessage());
					}
				}
			}
		}).start();
	}
}
