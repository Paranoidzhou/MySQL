package cn.tedu;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class MetaDataDemo {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			//得到数据库元数据
			DatabaseMetaData dbmd = conn.getMetaData();
			System.out.println("驱动版本:"+dbmd.getDriverMajorVersion());
			System.out.println("用户名:"+dbmd.getUserName());
			System.out.println("连接地址:"+dbmd.getURL());
			System.out.println("数据库厂商:"+dbmd.getDatabaseProductName());
			
			rs = stat.executeQuery("select * from student");
			//从结果集中得到表相关的元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			//得到表字段的数量
			int columCount = rsmd.getColumnCount();
			for (int i = 0; i < columCount; i++) {
				String name = rsmd.getColumnName(i+1);
				System.out.println(name);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}
