package cn.tedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCHelloWorld {
	public static void main(String[] args) throws Exception {
		//1.注册驱动:
		Class.forName("com.mysql.jdbc.Driver");
		//2.获取连接对象:
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6","root","root");
		//3.创建sql执行对象:
		Statement stat = conn.createStatement();
		//4.准备sql语句:             if not exists如果不存在表则创建,存在也不报错.
		String sql = "create table t_jdbc(id int primary key auto_increment,name varchar(10));";
		//5.执行sql语句
		boolean b = stat.execute(sql);
		if(b) {
			System.out.println("有结果集");
		}else {
			System.out.println("没有结果集");
		}
		//上面方法返回值为true表示有结果集
		//上面方法返回值为false表示没有结果集
		//查询的sql语句才有结果集
		System.out.println("执行成功");
	}
}
