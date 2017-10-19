/**  
 * @Title: TestLog4j2.java
 * @Package com.demo.test.log
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @ClassName: TestLog4j2
 * @Description: TODO
 */
public class TestLog4j2 {

	private static final Logger logger = LogManager.getLogger(TestLog4j2.class.getName());

	/**
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		// trace<debug<info<warn<error<fatal
		logger.entry(); // trace级别的信息，单独列出来是希望你在某个方法或者程序逻辑开始的时候调用，和logger.trace("entry")基本一个意思
		logger.info("我是info信息");
		logger.debug("我是debug信息");
		logger.warn("我是warn信息");
		logger.error("Did it again!");
		logger.fatal("我是fatal信息");
		logger.log(Level.INFO, "我是INFO信息"); // 这个就是制定Level类型的调用：谁闲着没事调用这个，也不一定哦！
		logger.exit(); // 和entry()对应的结束方法，和logger.trace("exit");一个意思
	}
}
