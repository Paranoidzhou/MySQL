# JDBC day01

## JDBC
- 什么是JDBC?
  Java DataBase Connectivity : Java数据库连接,实际上JDBC是Java中一套和数据库进行交互的API(Application program interface)应用程序编程接口.

##### 为什么使用JDBC :
- 因为java程序员可能有需求连接各种数据库(oracle,mysql,db2...),为了避免java程序员连每一种数据库都学习一套新的API,SUN公司提出了一个叫JDBC的接口,各数据库厂商根据这个接口去写实现类,这个实现类就叫驱动.这样的话java程序员只需要掌握jdbc接口的调用,就可以操作任意数据库.

##### Maven
- Maven是项目管理工具,可以构建工程,通过使用坐标的形式导入jar包.使用Maven会自动导入依赖jar包.

##### 如何使用JDBC :
1. 创建Maven工程
2. 下载Mysql相关Jar包,登录到Maven.tedu.cn
3. 导入jar包
		<dependency>
		  <groupId>MySQL</groupId>
		  <artifactId>mysql-connector-java</artifactId>
		  <version>5.1.6</version>
		</dependency>
4. 创建一个JDBCHelloWord类.

#### JDBC使用流程:
- 1.建Maven项目
- 2.找到mysql相关jar包,并导入Maven项目
- 3.新建一个包和类
- 4.类中执行以下步骤代码:
```
	//1.注册驱动:
	Class.forName("com.mysql.jdbc.Driver");

	//2.获取连接对象:
	Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/db6","root","root");

	//3.创建sql执行对象:
	Statement statement = conn.createStatement();

	//4.准备sql语句:
	String sql = "create table TestJdbc(id int primary key auto_increment,name varchar(10),gender char(5));";

	//5.执行sql语句:
	statement.execute(sql);
	System.out.println("创建成功");
```

##### execute:
- 此方法可以执行任意sql,返回值true代表有结果集(查询语句时会有结果集),false为没有结果集.通常情况下execute方法用于执行DDL(数据定义语言);

##### executeUpdate:
- 此方法执行增删改操作,返回值代表生效的行数.

##### executeQuery:
- 此方法执行查询结果,返回值为ResultSet,里面保存查询到的所有结果.

##### ResultSet:
- 此对象里面装着查询到的结果数据.
- 凡是见到ResultSet就用while遍历.
- next() 方法会先判断是否有下一条数据,有则返回true,没有就返回false,如果返回true则内部游标会往下一条数据移动.

##### 从ResultSet对象中获取数据:
- 通过字段的名称获取数据
- 通过字段的位置获取数据,从1开始

##### 数据库字段类型与java类型对比:
|数据库|java|
|----|----|
|int|getInt|
|varchar|getString|
|double|getDouble|
|datetime|getDate|
|timestamp|getDate|

##### 关闭资源:
- 关闭Connection,使用完之后的连接要及时关闭,避免浪费资源.
- 关闭Statement,它会占用内存空间,而且Statement有上限.
- 关闭ResultSet,里面保存的是查询结果,用完就释放,节省空间.
- 关闭顺序:先开的后关
		ResultSet → Statement → Connection
- 如何关闭?在哪里关?:每个方法都有close()方法,在执行完所有代码后关闭.

##### 封装工具类
- 封装了工具类,可以实现代码复用.