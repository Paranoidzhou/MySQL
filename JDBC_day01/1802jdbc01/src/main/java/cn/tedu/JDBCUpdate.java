package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

public class JDBCUpdate {
	@Test
	public void insert() throws Exception {
		//1.注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2.获取连接对象
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6","root","root");
		//3.创建sql执行对象
		Statement stat = conn.createStatement();
		//4.准备sql语句
		String sql = "insert into t_jdbc values"+"(null,'刘德华')"
												+",(null,'张学友')"
												+",(null,'郭富城')"
												+",(null,'黎明')"
												+",(null,'周润发');";
		//5.执行sql语句
		int row = stat.executeUpdate(sql);
		System.out.println("执行生效:"+row);
	}
	
	@Test
	public void delete() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6","root","root");
		Statement s = con.createStatement();
		String sql = "delete from t_jdbc;";
		int row = s.executeUpdate(sql);
		System.out.println("执行生效:"+row);
	}
	
	@Test
	public void update() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6","root","root");
		Statement s = con.createStatement();
		String sql = "update t_jdbc set"+" name='jerry' where id=15;";
		int row = s.executeUpdate(sql);
		System.out.println("生效行数:"+row);
	}
}
