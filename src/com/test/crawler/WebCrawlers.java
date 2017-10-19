/**  
 * @Title: WebCrawlers.java
 * @Package com.test.crawler
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.crawler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @ClassName: WebCrawlers
 * @Description: TODO
 */
public class WebCrawlers {

	/**
	 * @Description: TODO
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/*System.setProperty("http.maxRedirects", "50");
		System.getProperties().setProperty("proxySet", "true");
		String ip = "代理服务器地址";
		System.getProperties().setProperty("http.proxyHost", ip);
		System.getProperties().setProperty("http.proxyPort", "代理的端口");*/

		/*final String url = "https://www.cnblogs.com/";
		Document doc = Jsoup.connect(url).get();
		Elements list = doc.getElementById("post_list").select(".post_item");
		for (Element fixLi : list) {
			Document li = Jsoup.parse(fixLi.toString());
			Elements title = li.select(".titlelnk");
			System.out.println("标题" + title.text()); //标题
			System.out.println("详情链接" + title.attr("href")); //标题下的链接
			Elements author = li.select(".lightblue");
			System.out.println("作者" + author.text()); //作者
		}

		String unsafe = "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
		String safe = Jsoup.clean(unsafe, Whitelist.basic());
		System.out.println(safe);*/

		//download(imgSrc, files.getPath() + "//" + pageNow + imgSrc.substring(imgSrc.length() - 4));
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

	public static void classFix() {
		final String url = "https://www.cnblogs.com/";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements container = doc.getElementsByClass("container");
			Document containerDoc = Jsoup.parse(container.toString());
			Elements module = containerDoc.getElementsByClass("module-list");
			Document moduleDoc = Jsoup.parse(module.toString());
			//Elements clearfix = moduleDoc.getElementsByClass("clearfix");  //DOM的形式
			Elements clearfix = moduleDoc.select(".clearfix"); //选择器的形式

			for (Element clearfixli : clearfix) {
				Document clearfixliDoc = Jsoup.parse(clearfixli.toString());
				Elements kind = clearfixliDoc.select(".board-tag"); //选择器的形式
				Elements title = clearfixliDoc.select(".tit-post");
				Elements author = clearfixliDoc.select("span a");

				System.out.println("类别" + kind.text()); //分类
				System.out.println("标题" + title.text()); //标题
				System.out.println("作者" + author.text()); //作者
				System.out.println("详情链接" + title.attr("href")); //标题下的链接
				System.out.println("=====================");
			}
			//  String title = clearfixli.getElementsByTag("a").text();
			//  System.out.println(clearfix);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cmtsFix() {
		final String url = "http://sex.guokr.com/post/1100992/";

		try {

			Document doc = Jsoup.connect(url).get();
			Elements container = doc.getElementsByClass("container");
			Document containerDoc = Jsoup.parse(container.toString());

			String articleTitle = containerDoc.getElementById("articleTitle").text();
			String authorName = containerDoc.getElementById("authorName").text();
			String time = containerDoc.select("span").first().text();
			String imgphotoUrl = containerDoc.select("img").get(1).attr("src");
			System.out.println("标题：" + articleTitle); //标题
			System.out.println("作者：" + authorName); //作者
			System.out.println("发布时间：" + time); //发布时间
			System.out.println("作者头像的url：" + imgphotoUrl); //发布时间

			Element articleContent = containerDoc.getElementById("articleContent");
			Document articleContentDoc = Jsoup.parse(articleContent.toString());

			int size = articleContentDoc.select("p").size();
			System.out.println("段落数：" + size);

			System.out.println("帖子内容：");

			for (int i = 0; i < size; i++) {
				String content = articleContentDoc.select("p").get(i).text();
				System.out.println(content);
			}

			System.out.println("================================================");
			System.out.println("帖子评论区域（按照楼层分布）");
			Elements cmts = containerDoc.getElementsByClass("cmts");
			Document cmtsDoc = Jsoup.parse(cmts.toString());
			System.out.println("评论楼层：" + cmtsDoc.select("span").first().text());

			Elements cmtslist = cmtsDoc.getElementsByClass("cmts-list");

			for (Element clearfix : cmtslist) {
				String user = clearfix.select("a").get(1).text();
				String userPhotoUrl = clearfix.select("img").get(0).attr("src");
				String replyTime = clearfix.select("a").get(3).text();
				String floor = clearfix.select("span").text();

				System.out.println("评论者：" + user + "\n" + "评论者头像url：" + userPhotoUrl + "\n" + "回复时间：" + replyTime
						+ "\n" + "所在楼层：" + floor);
				Document replyContentDoc = Jsoup.parse(clearfix.toString());
				Elements replyContent = replyContentDoc.getElementsByClass("cmt-content");
				System.out.println("评论内容：");
				int s = replyContent.select("p").size();
				for (int j = 0; j < s; j++) {
					String replycontent = replyContent.select("p").get(j).text();
					System.out.println(replycontent);

				}
				System.out.println("================================================");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void articleFix() throws IOException {
		final String URL = "http://blog.csdn.net/guolin_blog";
		Connection conn = Jsoup.connect(URL)
				.userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:50.0) Gecko/20100101 Firefox/50.0")
				.timeout(5000).method(Connection.Method.GET);
		Document doc = conn.get();
		Element body = doc.body();

		//获取总页数
		String totalPageStr = body.getElementById("papelist").select("span:eq(0)").text();
		String regex = ".+共(\\d+)页";
		totalPageStr = totalPageStr.replaceAll(regex, "$1");
		int totalPage = Integer.parseInt(totalPageStr);
		int pageNow = 1;

		List<Article> articleList = new ArrayList<Article>();
		for (pageNow = 1; pageNow <= totalPage; pageNow++) {
			articleList.addAll(getArtitcleByPage(URL, pageNow));
		}

		//遍历输出博主所有的文章
		for (Article article : articleList) {
			System.out.println("文章标题:" + article.getTitle());
			System.out.println("文章绝对路劲地址:http://blog.csdn.net" + article.getAddress());
			System.out.println("文章简介:" + article.getDesption());
			System.out.println("发表时间:" + article.getTime());
		}
	}

	public static List<Article> getArtitcleByPage(String URL, int pageNow) throws IOException {
		Connection conn = Jsoup.connect(URL + "/article/list/" + pageNow)
				.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:47.0) Gecko/20100101 Firefox/47.").timeout(5000)
				.method(Connection.Method.GET);
		Document doc = conn.get();
		Element body = doc.body();
		List<Article> resultList = new ArrayList<Article>();

		Element articleListDiv = body.getElementById("article_list");
		Elements articleList = articleListDiv.getElementsByClass("list_item");
		for (Element article : articleList) {
			Article articleEntity = new Article();
			Element linkNode = (article.select("div h1 a")).get(0);
			Element desptionNode = (article.getElementsByClass("article_description")).get(0);
			Element articleManageNode = (article.getElementsByClass("article_manage")).get(0);

			articleEntity.setAddress(linkNode.attr("href"));
			articleEntity.setTitle(linkNode.text());
			articleEntity.setDesption(desptionNode.text());
			articleEntity.setTime(articleManageNode.select("span:eq(0").text());

			resultList.add(articleEntity);
		}
		return resultList;
	}

}
