package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrimaryDemo {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			String sql = "insert into student values"+"(null,'程咬金',38,'男'),(null,'宋江',28,'男')";
			//执行sql并设置需要得到自增的值
			stat.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			//获取自增的值
			rs = stat.getGeneratedKeys();
			while(rs.next()) {
				int id = rs.getInt(1);
				System.out.println("id:"+id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}
