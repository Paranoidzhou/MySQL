package cn.tedu;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesDemo {
	public static void main(String[] args) {
		//创建属性对象
		Properties prop = new Properties();
		//通过反射得到文件的输入流
		InputStream ips = PropertiesDemo.class.getClassLoader().getResourceAsStream("my.properties");
		//把流交给属性对象
		try {
			prop.load(ips);
			//获取数据,获取到的数据类型为String
			String name = prop.getProperty("name");
			String age = prop.getProperty("age");
			String loc = prop.getProperty("loc");
			System.out.println("name:"+name+" ,age:"+age+" ,loc:"+loc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
