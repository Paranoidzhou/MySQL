package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LoginDemo {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入用户名:");
		String username = scan.nextLine();
		System.out.println("请输入密码:");
		String password = scan.nextLine();
		
		//调用登录方法:
		boolean b =login(username,password);
		if(b) {
			System.out.println("登录成功");
		}else {
			System.out.println("登录失败");
		}
	}
	public static boolean login(String username,String password) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
//			stat = conn.createStatement();
//			/** 拼接登录sql*/
//			//select count(*) from user where username='libai' and password='admin';
//			String sql = "select count(*) from user where "+"username='"+username+"' and password='"+password+"'";
//			System.out.println(sql);
//			//执行sql
//			rs = stat.executeQuery(sql);
			String sql = "select count(*) from user"+" where username=? and password=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1,username);
			stat.setString(2,password);
			rs = stat.executeQuery();
			while(rs.next()) {
				//因为查询结果里面只有一条数据,只查询1就可以了.
				int count = rs.getInt(1);
//				if(count>0) {
//					return true;
//				}else {
//					return false;
//				}
				return count>0;   //等效上面if语句结果
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
		return false;
	}
}
