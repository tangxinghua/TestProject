/**  
 * @Title: Client.java
 * @Package com.test.net
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Description: TODO
 */
public class Client {

	/**
	 * @Description: TODO
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		//1.创建客户端Socket，指定服务器地址和端口
		Socket socket = new Socket("localhost", 8888);
		//获取输出流，向服务器端发送信息
		OutputStream os = socket.getOutputStream();//字节输出流
		PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
		pw.write("用户名：admin;密码：123");
		pw.flush();
		socket.shutdownOutput();//关闭输出流
		//3.获取输入流，并读取服务端的响应信息
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while ((info = br.readLine()) != null) {
			System.out.println("我是客户端，服务器说：" + info);
		}
		//4.关闭资源
		br.close();
		is.close();
		os.close();
		pw.close();
		socket.close();
	}

}
