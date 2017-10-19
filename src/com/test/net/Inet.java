/**  
 * @Title: Inet.java
 * @Package com.test.net
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @Description: TODO
 */
public class Inet {

	/**
	 * @Description: TODO
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// 获取本机的InetAddress实例
		InetAddress address = InetAddress.getLocalHost();
		System.out.print("计算机名：" + address.getHostName());
		System.out.println("/IP地址：" + address.getHostAddress());
		System.out.println("********************************************");
		//获取字节数组形式的IP地址
		byte[] bytes = address.getAddress();
		System.out.println("字节数组形式的IP：" + Arrays.toString(bytes));
		//直接输出InetAddress对象
		System.out.println(address);
		System.out.println("********************************************");
		//根据机器名获取InetAddress实例
		InetAddress address2 = InetAddress.getByName("Tang");
		System.out.print("计算机名：" + address2.getHostName());
		System.out.println("/IP地址：" + address2.getHostAddress());
		System.out.println("********************************************");
		InetAddress address3 = InetAddress.getByName("192.168.1.102");
		System.out.print("计算机名：" + address3.getHostName());
		System.out.println("/IP地址：" + address3.getHostAddress());
		System.out.println("********************************************");
	}

}
