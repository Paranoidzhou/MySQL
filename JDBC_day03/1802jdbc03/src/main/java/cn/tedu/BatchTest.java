package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class BatchTest {
	@Test
	public void test01(){
		//往数据库中插入刘关张三个人的信息.
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			String sql1 = "insert into student values"+"(null,'刘备',45,'男')";
			String sql2 = "insert into student values"+"(null,'关羽',40,'男')";
			String sql3 = "insert into student values"+"(null,'张飞',35,'男')";
			//把要执行的操作添加到批量处理里面:
			stat.addBatch(sql1);
			stat.addBatch(sql2);
			stat.addBatch(sql3);
			//执行批量操作:
			stat.executeBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
	
	@Test
	public void test02(){
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "insert into student values"+"(null,?,?,?)";
			stat = conn.prepareStatement(sql);
			stat.setString(1, "猪八戒");
			stat.setInt(2,200);
			stat.setString(3,"女");
			stat.addBatch();
			
			stat.setString(1, "孙悟空");
			stat.setInt(2,500);
			stat.setString(3,"男");
			stat.addBatch();
			
			stat.setString(1, "唐僧");
			stat.setInt(2,299);
			stat.setString(3,"女");
			stat.addBatch();
			
			//返回值代表每一行sql语句执行生效的行数.
			
			
			stat.executeBatch();
			System.out.println("执行完毕.....");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
	
	@Test
	public void test03() {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "insert into student values"+"(null,?,?,?)";
			stat = conn.prepareStatement(sql);
			for (int i = 0; i < 199702; i++) {
				stat.setString(1,"name"+i);
				stat.setInt(2,i);
				stat.setString(3, i%2==0?"男":"女");
				stat.addBatch();
				System.out.println("执行第:"+i+"次");
				//每隔20条执行一次
				if(i%20==0) {   
					stat.executeBatch();
					//清空之前执行过的sql
					stat.clearBatch();
				}
			}
			stat.executeBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}
