/**  
 * @Title: TestLog.java
 * @Package com.test.log
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.log;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: TODO
 */
public class TestLog {

	private static Logger log = LoggerFactory.getLogger(TestLog.class);

	/**
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		int messageSize = 100;
		int threadSize = 10;
		final int everySize = messageSize / threadSize;

		final CountDownLatch cdl = new CountDownLatch(threadSize);
		long startTime = System.currentTimeMillis();
		for (int ts = 0; ts < threadSize; ts++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int es = 0; es < everySize; es++) {
						log.info("======info");
					}
					cdl.countDown();
				}
			}).start();
		}
		cdl.await();
		long endTime = System.currentTimeMillis();
		System.out.println("log4j1:messageSize = " + messageSize + ",threadSize = " + threadSize + ",costTime = "
				+ (endTime - startTime) + "ms");
	}

}
