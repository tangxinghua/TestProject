/**  
 * @Title: MhWebCrawlers.java
 * @Package com.test.crawler
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @ClassName: MhWebCrawlers
 * @Description: TODO
 */
public class MhWebCrawlers {

	/**
	 * @Description: TODO
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//180
		mhFix(538);
		/*mhFix(1317);
		mhFix(1678);
		mhFix(1881);
		mhFix(1062);
		mhFix(2243);*/
	}

	public static void mhFix(int ml) throws IOException {
		String url = "http://www.xieelao.com/slmh/" + ml + ".html";
		Document doc = Jsoup.connect(url).get();
		//String title = doc.select("h1.mhtitle").text();
		Element mh = doc.getElementById("mhshow");
		String imgSrc = mh.getElementById("imgshow").select("img").attr("src"); //doc.select("img[src$=.jpg]");
		String totalPageStr = mh.select("div.dede_pages_all").select("ul li:eq(0) a").text();
		String regex = ".+共(\\d+)页:";
		totalPageStr = totalPageStr.replaceAll(regex, "$1");
		int totalPage = Integer.parseInt(totalPageStr);
		//System.out.println(title + " : " + totalPage + " : " + imgSrc);
		//创建文件目录  
		File files = new File("D://Downloads//Temp//" + ml);
		if (!files.exists()) {
			files.mkdirs();
		}
		int pageNow = 1;
		download(imgSrc, files.getPath() + "//" + pageNow + imgSrc.substring(imgSrc.length() - 4));
		for (pageNow = 2; pageNow <= totalPage; pageNow++) {
			String pageUrl = url.replace(".html", "_" + pageNow + ".html");
			doc = Jsoup.connect(pageUrl).get();
			imgSrc = doc.getElementById("imgshow").select("img").attr("src");
			download(imgSrc, files.getPath() + "//" + pageNow + imgSrc.substring(imgSrc.length() - 4));
		}
	}

	/**
	 * @Description: 下载文件到本地
	 * @param urlString 被下载的文件地址
	 * @param filename 本地文件名
	 * @throws IOException 各种异常
	 */
	public static void download(String urlString, String filename) throws IOException {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 输入流
		InputStream is = con.getInputStream();
		// 输出的文件流
		OutputStream os = new FileOutputStream(filename);
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

}
