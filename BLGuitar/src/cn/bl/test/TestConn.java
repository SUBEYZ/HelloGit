package cn.bl.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import cn.bl.util.JDBCUtils;

public class TestConn {
	@Test
	public void test() throws SQLException {//OK
		Connection connection = JDBCUtils.getConnection();
		System.out.println(connection.createStatement());
	}
}
