/**  
 * @Title: Url.java
 * @Package com.test.net
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName: Url
 * @Description: TODO
 */
public class Url {

	/**
	 * @Description: TODO
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws Exception {
		// 创建一个URL的实例
		URL imooc = new URL("http://www.baidu.com");
		URL url = new URL(imooc, "index.html?username=tom#test");//?后面表示参数#后面表示
		System.out.println("协议：" + url.getProtocol());
		System.out.println("主机：" + url.getHost());
		//如果未指定端口号，则使用默认的端口号，此时getPort()方法返回值为-1
		System.out.println("端口：" + url.getPort());
		System.out.println("文件路径：" + url.getPath());
		System.out.println("文件名：" + url.getFile());
		System.out.println("相对路径：" + url.getRef());
		System.out.println("查询字符串：" + url.getQuery());

		// 创建一个URL实例
		InputStream is = url.openStream();
		//将字节输入流转换为字符输入流
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		//为字符输入流添加缓冲
		String data = br.readLine();//读取数据
		while (data != null) {//循环读取数据
			System.out.println(data);//输出数据
			data = br.readLine();
		}
		br.close();
		isr.close();
		is.close();

	}

}
