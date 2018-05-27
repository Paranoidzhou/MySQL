package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class PageTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入查询的页数:");
		int page = scan.nextInt();
		System.out.println("请输入每页的条数:");
		int size = scan.nextInt();
		
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "select * from student limit ?,?";
			stat = conn.prepareStatement(sql);			
			//设置跳过的条数
			stat.setInt(1, (page-1)*size);
			//设置每页的数量    
			stat.setInt(2, size);
			rs = stat.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println("id:"+id+",name:"+name);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}
