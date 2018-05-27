package cn.tedu;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static BasicDataSource dataSource;
	 
	private static String initSize;
	private static String maxSize;
	
	static {
		//创建属性对象
		Properties prop = new Properties();
		//通过反射得到文件的输入流
		InputStream ips = DBUtils.class.getClassLoader().getResourceAsStream("jdbc_day03.properties");
		//把流交给属性对象
			try {
				prop.load(ips);
				driver = prop.getProperty("className");
				url = prop.getProperty("url");
				username = prop.getProperty("username");
				password = prop.getProperty("password");	
				
				initSize = prop.getProperty("initSize");
				maxSize = prop.getProperty("maxSize");
				
				dataSource = new BasicDataSource();
				dataSource.setDriverClassName(driver);
				dataSource.setUrl(url);
				dataSource.setUsername(username);
				dataSource.setPassword(password);
				
				dataSource.setInitialSize(Integer.parseInt(initSize));  //默认创建有3个连接
				dataSource.setMaxActive(Integer.parseInt(maxSize));		//最大连接数量
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
	//获取连接:
	public static Connection getConn() throws Exception {
			return dataSource.getConnection();
	}
	
	//关闭资源:
	public static void close(Connection conn,Statement stat,ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stat!=null) {
				stat.close();
			}
			if(conn!=null) {
				//保证还回来的连接的事务都是关闭的.(打开自动提交)
				conn.setAutoCommit(true);
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
