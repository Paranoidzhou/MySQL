package cn.tedu;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDemo {
	public static void main(String[] args) throws Exception {
		//数据源对象
		BasicDataSource dataSource = new BasicDataSource();
		//设置数据库连接信息
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/dab6");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		
		//设置管理策略参数
		//初始化是连接数量
		dataSource.setInitialSize(3);  //默认创建有3个连接
		dataSource.setMaxActive(5);		//最大连接数量
		
		//获取连接多想
		Connection conn = dataSource.getConnection();
		
		Statement sta = conn.createStatement();
		String sql = "insert into t_jdbc values"+"(1,'大耳贼刘备')";
		sta.executeUpdate(sql);
		System.out.println("执行成功");
	}
}
