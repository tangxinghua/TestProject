/**  
 * @Title: TestSQLite.java
 * @Package com.test.sqlite
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.sqlite;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: TestSQLite
 * @Description: TODO
 */
public class TestSQLite {

	private static final Logger logger = LoggerFactory.getLogger(TestSQLite.class);

	private static Properties pro = new Properties();

	static {
		try {
			InputStream in = TestSQLite.class.getClassLoader().getResourceAsStream("db-config.properties");
			pro.load(in);
		} catch (Exception e) {
			logger.error(e.getClass().getName(), e.getMessage());
			throw new ExceptionInInitializerError();
		}
	}

	/**
	 * 获取sqlite数据库连接
	 * 
	 * @param
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws Exception {
		Class.forName(pro.getProperty("sqliteDriver"));
		Connection conn = DriverManager.getConnection(pro.getProperty("sqliteUrl"));
		conn.setAutoCommit(false);
		logger.info("Opened database successfully");
		return conn;
	}

	/**
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;

		// CREATE TABLE
		/*try {
			c = getConnection();
			stmt = c.createStatement();
			String sql = "CREATE TABLE COMPANY " + "(ID INT PRIMARY KEY     NOT NULL,"
					+ " NAME           TEXT    NOT NULL, " + " AGE            INT     NOT NULL, "
					+ " ADDRESS        CHAR(50), " + " SALARY         REAL)";
			stmt.executeUpdate(sql);
			c.commit();
			close(rs, stmt, c);
		} catch (Exception e) {
			logger.error(e.getClass().getName(), e.getMessage());
			System.exit(0);
		}
		logger.info("Table created successfully");*/

		// INSERT
		/*try {
			c = getConnection();
			stmt = c.createStatement();
			String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
					+ "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
			stmt.executeUpdate(sql);
			c.commit();
			close(rs, stmt, c);
		} catch (Exception e) {
			logger.error(e.getClass().getName(), e.getMessage(), e.getMessage());
			System.exit(0);
		}
		logger.info("INSERT Records successfully");*/

		// SELECT
		try {
			c = getConnection();
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM COMPANY;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println("ADDRESS = " + address);
				System.out.println("SALARY = " + salary);
				System.out.println();
			}
			close(rs, stmt, c);
		} catch (Exception e) {
			logger.error(e.getClass().getName(), e.getMessage());
			System.exit(0);
		}
		logger.info("SELECT Operation done successfully");

		// UPDATE
		try {
			c = getConnection();
			stmt = c.createStatement();
			String sql = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
			stmt.executeUpdate(sql);
			c.commit();

			rs = stmt.executeQuery("SELECT * FROM COMPANY;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println("ADDRESS = " + address);
				System.out.println("SALARY = " + salary);
				System.out.println();
			}
			close(rs, stmt, c);
		} catch (Exception e) {
			logger.error(e.getClass().getName(), e.getMessage());
			System.exit(0);
		}
		logger.info("UPDATE Operation done successfully");

		// DELETE
		try {
			c = getConnection();
			stmt = c.createStatement();
			String sql = "DELETE from COMPANY where ID=2;";
			stmt.executeUpdate(sql);
			c.commit();

			rs = stmt.executeQuery("SELECT * FROM COMPANY;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				float salary = rs.getFloat("salary");
				System.out.println("ID = " + id);
				System.out.println("NAME = " + name);
				System.out.println("AGE = " + age);
				System.out.println("ADDRESS = " + address);
				System.out.println("SALARY = " + salary);
				System.out.println();
			}
			close(rs, stmt, c);
		} catch (Exception e) {
			logger.error(e.getClass().getName(), e.getMessage());
			System.exit(0);
		}
		logger.info("DELETE Operation done successfully");
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 * @return void
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException ex) {
				logger.error(null, ex);
			}
			rs = null;
		}
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				logger.error(null, ex);
			}
			stmt = null;
		}
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException ex) {
				logger.error(null, ex);
			}
			conn = null;
		}
	}

}
