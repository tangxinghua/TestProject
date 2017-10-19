/**  
 * @Title: TestSlf4j.java
 * @Package com.test.log
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: TODO
 */
public class TestSlf4j {

	private static final Logger logger = LoggerFactory.getLogger(TestSlf4j.class);

	/**
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		logger.error("error");
		logger.debug("debug");
		logger.info("info");
		logger.trace("trace");
		logger.warn("warn");
		logger.error("error {}", "error");
	}
}
