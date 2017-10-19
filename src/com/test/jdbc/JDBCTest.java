/**  
 * @Title: JDBCTest.java
 * @Package com.test.jdbc
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: JDBCTest
 * @Description: TODO
 */
public class JDBCTest {

	/**
	 * @Description: TODO
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = null;
		// 预编译的Statement，使用预编译的Statement提高数据库性能
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// 加载数据库驱动
			/*Class.forName("com.mysql.jdbc.Driver");*/
			Class.forName("org.sqlite.JDBC");
			// 通过驱动管理类获取数据库链接
			/*connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8",
					"root", "root");*/
			connection = DriverManager.getConnection("jdbc:sqlite:db/test.db", "", "");
			// 定义sql语句 ?表示占位符
			String sql = "select * from company where id = ?";
			//获取预处理statement
			preparedStatement = connection.prepareStatement(sql);
			// 设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
			preparedStatement.setString(1, "3");
			// 向数据库发出sql执行查询，查询出结果集
			resultSet = preparedStatement.executeQuery();
			// 遍历查询结果集
			while (resultSet.next()) {
				System.out.println(resultSet.getString("id") + "  " + resultSet.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放资源
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
