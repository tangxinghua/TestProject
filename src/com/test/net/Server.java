/**  
 * @Title: Server.java
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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: TODO
 */
public class Server {

	/**
	 * @Description: TODO
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//1.创建一个服务器端Socket,即ServerSocket，指定绑定的端口，并监听此端口
		ServerSocket serverSocket = new ServerSocket(8888);
		System.out.println("***服务器即将启动，等待客户端的连接***");
		//2.调用accept()方法开始监听，等待客户端的连接
		Socket socket = serverSocket.accept();
		//3.获取输入流，并读取客户端信息
		InputStream is = socket.getInputStream();//字节输入流
		InputStreamReader isr = new InputStreamReader(is);//将字节流转换为字符流
		BufferedReader br = new BufferedReader(isr);//为输入流添加缓冲
		String info = null;
		while ((info = br.readLine()) != null) {//循环读取客户端的信息
			System.out.println("我是服务器，客户端说：" + info);
		}
		socket.shutdownInput();//关闭输入流
		//4.获取输出流，响应客户端的请求
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);//包装为打印流
		pw.write("欢迎您！");
		pw.flush();//调用flush()方法将缓冲输出
		//5.关闭资源
		pw.close();
		os.close();
		br.close();
		is.close();
		isr.close();
		socket.close();
		serverSocket.close();

		//1.创建一个服务器端Socket,即ServerSocket，指定绑定的端口，并监听此端口
		serverSocket = new ServerSocket(8888);
		socket = null;
		int count = 0;
		System.out.println("***服务器即将启动，等待客户端的连接***");
		//循环监听等待客户端的连接
		while (true) {
			//2.调用accept()方法开始监听，等待客户端的连接    
			socket = serverSocket.accept();
			//创建一个新的线程
			ServerThread serverThread = new ServerThread(socket);
			//启动线程
			serverThread.start();
			count++;
			System.out.println("客户端的数量：" + count);//统计客户端的数量
			InetAddress address = socket.getInetAddress();
			System.out.println("当前客户端的IP：" + address.getHostAddress());
		}
	}

}
