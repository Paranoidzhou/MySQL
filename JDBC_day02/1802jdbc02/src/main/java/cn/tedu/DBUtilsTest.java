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
	public void insert(){
		try{
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			int row = stat.executeUpdate("insert into t_jdbc values"+"(5,'枭雄曹操'),(6,'韬晦司马懿');");
			System.out.println("执行成功,生效:"+row);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.close(conn,stat, null);
		}
	}

	@Test
	public void delete(){
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			int row = stat.executeUpdate("delete from t_jdbc;");
			System.out.println("生效行:"+row);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat,null);
		}
	}
	
	@Test
	public void update(){
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			int row = stat.executeUpdate("update t_jdbc set name='小乔' where name = '吕布'");
			System.out.println("执行成功,生效:"+row);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
	
	@Test
	public void findAll(){
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from t_jdbc;");
			while(rs.next()) {
				System.out.println("id:"+rs.getInt("id")+",name:"+rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}
