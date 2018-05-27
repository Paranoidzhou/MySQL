package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCQuery { 
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6","root","root");
		Statement stat = conn.createStatement();
		String sql = "select * from t_jdbc;";
		ResultSet rs = stat.executeQuery(sql);
		while(rs.next()){
//			int id = rs.getInt("id");
//			String name = rs.getString("name");
			int id = rs.getInt(1);
			String name = rs.getString(2);
			System.out.println("id="+id+" ,name="+name);
		}
		rs.close();
		stat.close();
		conn.close();
	}
}
