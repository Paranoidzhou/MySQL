package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeptEmpDemp {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			String sql1 = "insert into dept values"+"(null,'神仙')";
			stat.executeUpdate(sql1,Statement.RETURN_GENERATED_KEYS);
			rs = stat.getGeneratedKeys();
			int deptid = 0;
			while(rs.next()) {
			    deptid = rs.getInt(1);
			}
			String sql2 = "insert into emp values"+"(null,'观音',"+deptid+")";
			stat.executeUpdate(sql2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}
