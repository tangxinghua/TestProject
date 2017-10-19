/**  
 * @Title: Test.java
 * @Package com.test
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName: Test
 * @Description: TODO
 */
public class Test {

	/**
	 * @throws IOException
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) throws IOException {
		Properties pro = new Properties();
		InputStream in = Test.class.getClassLoader().getResourceAsStream("db-config.properties");
		pro.load(in);
		System.out.println(pro.getProperty("sqliteDriver"));

	}

}
