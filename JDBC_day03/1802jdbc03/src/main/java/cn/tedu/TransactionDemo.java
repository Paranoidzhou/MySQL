package cn.tedu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionDemo {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			//关闭自动提交,相当于开启了事务
			conn.setAutoCommit(false);
			//先让超人加2000
			String sql1="update person set"+" money = money+2000 where id=1";
			//再让蝙蝠侠减2000
			String sql2="update person set"+" money = money-2000 where id=2";
			stat.executeUpdate(sql1);
			stat.executeUpdate(sql2);
			//查询蝙蝠侠的钱是否大于等于0
			String sql3="select money from person where id=2;";
			rs = stat.executeQuery(sql3);
			while(rs.next()) {
				int money = rs.getInt("money");
				if(money>=0) {
					//提交
					conn.commit();
					System.out.println("转账成功....");
				}else {
					//失败则回滚到转账前
					conn.rollback();
					System.out.println("蝙蝠侠钱不够,转账失败....");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}
