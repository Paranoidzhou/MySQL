# JDBC day02



## Properties : 属性配置对象

- 什么是Properties : 程序员可以把工程中的某些数据以配置文件的形式进行保存,此对象就是处理 *.properties文件的对象,数据是以键值对的形式进行保存的.

- 如何使用 :

		1.创建My.properties配置文件.
		文件内容:
			name = joker
			age = 18
			loc = \u8FBE\u5185
		2.创建属性对象:
			Properties prop = new Properties();
		3.通过反射得到文件的输入流:
			InputStream ips = PropertiesDemo
			                 .class.getClassLoader()
			                 .getResourceAsStream("my.properties");
		4.把流交给属性对象:
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

## DBUtils : 第二次封装

- 把连接数据库的信息存放到了配置文件中,这样换数据库的话只需要改配置文件即可,把读取数据放到了静态块中,保证读取数据的代码只执行一次.

## 数据库连接池: DBCP -> DataBase Connection pool

- 为什么使用数据库连接池:
   -> 如果没有数据库连接池,每次和数据库的交互都需要一次建立连接和断开连接操作,如果有1万次交互就有一万次建立连接和断开连接,频繁的开关连接非常消耗服务器资源,使用连接池,可以设置一个最大连接数量,如果需要连接就可以和连接池要,有空闲连接就给它并执行,没有则等待归还之后再执行,这样大大降低了开关连接的次数,把连接的利用率最大化.

- 如何使用数据库连接池:
  1.导入Maven jar包.
  2.BasicDataSource
  3.设置数据库连接信息 driver url username passwrod
  4.初始化连接数量 ,最大连接数量
  5.datasource.getConnection();

## PreparedStatement:
- PreparedStatement是Statement的子接口.
- 好处:
	1.代码可读性高,避免了凭借sql语句的麻烦.
	2.带有预编译效果,可以避免sql注入.因为预编译的时候已经把sql的逻辑固定,不允许再次修改,所以可以避免注入;

注入的内容 ' or '1'='1
- 得到的sql:select count(*) from user where username='aaa' and password='' or '1'='1'
- 创建用户表进行登录测试: create table user(id int,username varchar(10),password varchar(10)); insert into user values(1,'libai','admin'),(2,'liubei','123456');

## 什么是SQL注入?
- sql注入：利用现有应用程序，将（恶意）的SQL命令注入到后台数据库执行一些恶意的操作造成SQL注入的原因是因为程序没有有效过滤用户的输入，使攻击者成功的向服务器提交恶意的SQL查询代码，程序在接收后错误的将攻击者的输入作为查询语句的一部分执行，导致原始的查询逻辑被改变，额外的执行了攻击者精心构造的恶意代码
- 通俗讲就是通过在表单中入输入SQL语句,而不是指定用户输入的规范字符,导致源码被更改.