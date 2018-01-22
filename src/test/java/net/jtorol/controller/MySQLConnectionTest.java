package net.jtorol.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {
	private static final String Driver = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://gondr.iptime.org:3306/Y20112?useSSL=false&serverTimezone=Asia/Seoul";
	//private static final String URL = "jdbc:mysql://jt3205.cafe24.com:3306/root?useSSL=false&serverTimezone=Asia/Seoul";
	private static final String user = "Y20112";
	private static final String pass = "1234";
	
	@Test
	public void test() {
		System.out.println("test");
	}
	
	@Test
	public void testConnection() throws Exception {
		Class.forName(Driver);
		
		try(Connection conn = DriverManager.getConnection(URL, user, pass)){
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
