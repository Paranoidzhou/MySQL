package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 *  封装工具类
 */
public class DBUtils{

	//1.获取连接的方法
	public static Connection getConn() throws Exception{
		//注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6","root","root");
		return conn;
	}
	//2.关闭资源的方法
	public static void close(Connection conn,Statement stat,ResultSet rs) {
		try {
			if(rs!=null) {
			rs.close();
			}
			if(stat!=null) {
			stat.close();
			}
			if(conn!=null) {
			conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
