package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class DBUtilsTest {
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	@Test
	public void insert() {
		try {
		conn = DBUtils.getConn();
		stat = conn.createStatement();
		String sql = "insert into t_jdbc values"+"(1,'小乔')"+",(2,'周瑜')"+",(3,'大乔'),"+"(4,'孙策');";
		int row = stat.executeUpdate(sql);
		System.out.println("生效行数:"+row);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn,stat,null);
		}
	}
	@Test
	public void delete() {
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			int row = stat.executeUpdate("delete from t_jdbc");
			System.out.println("生效行数:"+row);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, stat, null);
		}
	}
	@Test
	public void update() {
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			int row = stat.executeUpdate("update t_jdbc set name='司马懿' where id=2;");
			System.out.println("生效行数:"+row);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn,stat,null);
		}
	}
	@Test
	public void findAll() {
		try {
		conn = DBUtils.getConn();
		stat = conn.createStatement();
		rs = stat.executeQuery("select * from t_jdbc;");
		while(rs.next()) {
			System.out.println("id:"+rs.getInt("id")+",name:"+rs.getString("name"));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}
