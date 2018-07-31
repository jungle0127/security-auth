package com.ps.security.auth.client.web.async;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {
	private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);
	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferResultHolder deferResultHolder;
	@RequestMapping("/order")
	public Callable<String> order() {
		logger.info("Main thread start...");
		Callable<String> result = new Callable<String>() {

			@Override
			public String call() throws Exception {
				logger.info("Vice thread start....");
				TimeUnit.SECONDS.sleep(1);
				logger.info("Vice thread end....");
				return "Success";
			}
		};
		logger.info("Main thread end");
		return result;
	}
	@RequestMapping("/order/deferred")
	public Callable<String>  orderDeferredResult() throws Exception {
		logger.info("Deferred asynchronized rest begin..");
		String orderNumber = RandomStringUtils.random(8);
		mockQueue.setPlaceHolder(orderNumber);
		DeferredResult<String> result = new DeferredResult<>();
		this.deferResultHolder.getMap().put(orderNumber, result);
		logger.info("Deferred asynchronized rest end...");
	}
}
