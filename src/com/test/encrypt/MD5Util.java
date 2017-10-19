/**  
 * @Title: MD5Util.java
 * @Package com.test.encrypt
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.encrypt;

import java.security.MessageDigest;

/**
 * @ClassName: MD5Util
 * @Description: 采用MD5加密
 */
public class MD5Util {

	/**
	 * @Description: MD5加密 生成32位md5码
	 * @param inStr 待加密字符串
	 * @return 返回32位md5码
	 * @throws Exception
	 */
	public static String md5Encode(String inStr) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * @Description: 测试主函数
	 * @param args
	 * @return void
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String str = new String("args");
		System.out.println("原始：" + str);
		System.out.println("MD5后：" + md5Encode(str));
	}

}
