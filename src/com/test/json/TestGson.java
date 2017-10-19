/**  
 * @Title: TestGson.java
 * @Package com.test.json
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.json;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.test.TestBean;

/**
 * @Description: TODO
 */
public class TestGson {

	/**
	 * @throws IOException
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		TestBean bean = new TestBean();
		bean.setStuName("liming");
		bean.setClassName("As178");
		bean.setW_score(87);
		bean.setR_score(78);
		bean.setDate(new Date());

		Gson gson = new Gson();
		System.out.println("---->javabean convert jsonStr:" + gson.toJson(bean));

		List<String> list = Arrays.asList("1", "a", "3", "rt", "5");
		System.out.println("---->list convert jsonStr:" + gson.toJson(list));

		Map<String, Object> content = new HashMap<String, Object>();
		content.put("name", "xuanyouwu");
		content.put("age", "26");
		System.out.println("---->map convert jsonStr:" + gson.toJson(content));

		/*@SerializedName 匹配*/
		String studentJsonStr = "{\"name\":\"liming\",\"className\":As178}";
		TestBean student = gson.fromJson(studentJsonStr, TestBean.class);
		System.out.println("---->json convert JavaBean:" + student);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "liming");
		jsonObject.addProperty("age", 26);
		System.out.println("---->create jsonObject:" + jsonObject);

		JsonArray jsonElements = new JsonArray();
		jsonElements.add("骑车");
		jsonElements.add("打游戏");
		jsonElements.add("看电视");
		jsonObject.add("hobby", jsonElements);
		System.out.println("---->create jsonObject inner JsonArray:" + jsonObject);
	}
}
