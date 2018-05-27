package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCException {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6","root","root");
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from t_jdbc");
			while(rs.next()) {
				String name = rs.getString(2);
				System.out.println("name:"+name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(rs!=null){
					rs.close();
				}
				if(stat!=null){
					stat.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
	}
}
