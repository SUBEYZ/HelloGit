package cn.bl.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * 链接数据库，关闭链接
 * jdbc:mysql://localhost:3306/test
 * root --> 000
 */
public class JDBCUtils {
	private JDBCUtils() {}//工具类私有化构造
	private static Connection connection = null;
	static {
		InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("conf/jdbc.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		String url = pro.getProperty("url");
		String username = pro.getProperty("username");
		String password = pro.getProperty("password");
		String driver = pro.getProperty("driver");
		try {
			Class.forName(driver);//---    ---
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new RuntimeException("链接数据库失败："+e);
		}catch (ClassNotFoundException e1) {
			throw new RuntimeException("链接数据库失败："+e1);
		}
	}
	public static Connection getConnection() {
		return connection;
	}
	public static void close(Connection connection,Statement statement) {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection connection,Statement statement,ResultSet resultSet) {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(resultSet!=null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
