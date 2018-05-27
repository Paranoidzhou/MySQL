package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;

public class TestUpdate {
	@Test
	public void statementUpdate() {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入要修改的ID:");
		String id = scan.nextLine();
		System.out.println("请输入要修改的名字:");
		String name = scan.nextLine();
		
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			 conn = DBUtils.getConn();
			 stat = conn.createStatement();
			 String sql = "update t_jdbc set name='"+name+"'where id="+id;
			 stat.executeUpdate(sql);
			 System.out.println("执行成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
	@Test
	public void preparedStatement() {
		String name = "流星雨";
		int id = 1002;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			/** 准备sql  需要凭借出来的变量用 ?号 代替*/
			String sql = "update t_jdbc set name=? where id=?";
			stat = conn.prepareStatement(sql);
			//替换掉sql中的?
			stat.setString(1,name);
			stat.setInt(2,id);
			//执行sql
			stat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}