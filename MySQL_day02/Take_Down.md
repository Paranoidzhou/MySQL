##  ## 主键约束：

- 什么是约束：就是创建表的时候给表字段添加的限制条件。

- 主键约束的特点：唯一并且非空、一个表中只有一个主键。

- 如何使用：


 	create table 表名(id int primary key,name varchar(10));

 	 --> 以下会报错，因为Id重复了：
		insert into t1 values('1','小明');
		insert into t1 values('1','小红');

	 --> 以下会报错，因为主键不能为Null:
		insert into t1(name)values ('小花');



## 主键+自增：

- 如何使用： create table t2(id int primary key auto_increment,age int);
- 自增特性：
  1. 当自增字段的值为Null时，会自动赋值并自增。
  2. 以表中会出现的最大值+1。
  3. 删除数据自增数值不减。

## 使用Eclipse执行SQL乱码问题：

?useUnicode=true&characterEncoding=UTF-8

## 注释：Comment

- 在创建表的时候可以通过Comment对字段进行描述：
- 如何使用：

```
create table t3(id int primary key auto_increment comment '这是主键ID' ,comm int comment '这是奖金');
```

- 如何查看注释：

```
show create table t3;
```

## ` 和 ' 的区别：

- （ `）键盘Esc下面那个键；

- （ ' ）单引号；

- ( ` ) 是在创建表时，修饰表名和字段的名的，可以省略：


  ```
  create table `t4`(id int,`age` int);
  ```

- ' 时用来表示字符串的：

```
  create table `t4`(id int,`age` int);
```

## 数据冗余

- 如果数据库设计不合理，保存大量数据后会出现大量的重复数据，这种现象称为数据的冗余，通过拆分表格的形式把可能大量重复的数据用单独的一张表保存，在原表中只需要通过id建立关系即可。

## 事务

- 什么是事务：事务是数据库中执行SQL语句的最小工作单元,在同一个事务中的SQL语句要么同时成功，要么同时失败。

- mysql数据库默认sql语句是自动提交的。

- 关闭与开启数据库的提交。

  ​	-->    查看自动提交的状态：show variables like '%autocommit%';

  ​	-->    关闭自动提交：set autocommit = 0;

  ​	-->    开启自动提交：set autocommit = 1;

  ​	-->    手都提交：commit;

- 如何开启事务：

		create table person(id int,name varchar(10),money int);
		insert into person values(1,'超人',500),(2,'钢铁侠',1000);

- 超人找钢铁侠借300块钱：

  1. 超人+300；

     update person set money=800 where id=1;

  2. 钢铁侠-300；

     update person set money=700 where id=2;

- 回滚：rollback

  ​      执行rollback会回滚到上次提交的点或者关闭自动提交时的点。

- 保存回滚点：savepoint s1(标识);

  ​	update person set money = money-200 where id=1;

  ​	savepoint s1;

  ​	update person set money = money+200 where id=1;

  ​	rollback  to s1;

## SQL分类：

- DDL：Data Definition Language 数据定义语言。

   --> 包括：create 、drop、 alter、truncate

  > 它们都不支持事务。

  ```
  truncate :
  
  -->格式： truncate table 表名；
  
  作用：删除表并创建一张空表，auto_increment数值清零
  
  ```

  - DML：Data Manipulation Language 数据操作语言。

    -->包括：insert 、update、delete、select()

    > 它们都支持事务

  - DQL ：Data Query Language 数据查询语言。

    -->只有：select

    > 它也属于DML

  - TCL ：Transaction Control Language 事务控制语言

    -->包括： commit、rollback、savepoint、rollback to

    

  - DCL ：Data Control Language 数据库控制语言

    -->分配用户权限的相关sql 

  ## 数据库类型：

  ### 整型常用：int(m)  bigint(m)    其中M代表显示长度，如果字段数值长度不到M时，会在数值的前面补零，但是一定要和zerofill结合使用。

  -->例如：  create table T_int(num int(10)zerofill);

  ​		 insert into T_int values(15);

  ​		 select * from T_int;

  输出结果为：0000000015 

  ### 浮点数常用： double(m,d) 其中M代表总长度，D代表小数长度。

  -->例如：  72.123        m=2 ,d=3

  ### decimal(m,d)  超高精度小数，需要涉及到到高精度运算的时候，用此类型。

  ### 字符串常用：char 、varchar 、text.

  - char (m)：其中M代表长度，长度不可变，声明多长，所占长度为多长。（执行效率高、最大值：255）
  - varchar(m)：其中M代表长度，长度可变，声明N个长度，所占长度为实际字符长度。（节省空间、最大值：65535，但是超过255建议使用text）
  - text：可变长度，最大65535
  - longtext :更大。

  ## 日期类型 

  - date ：只能保存年月日;
  - time ：只能保存时分秒；
  - datatime：保存年月日时分秒；支持保存9999-12-31，默认值为空。
  - timestamp：年月日时分秒；（1970年到现在的毫秒数）支持保存到2038-01-19,默认值为当前时间。

  创建时间表：

  ​         create table t_date(d1 date,d2 time,d3 datetime,d4 timestamp);

  ​	 insert into t_date values('2018-03-22',null,null,null);

  ​	

  ```
  mysql> select * from t_date;
  +------------+------+------+---------------------+
  | d1         | d2   | d3   | d4                  |
  +------------+------+------+---------------------+
  | 2018-03-22 | NULL | NULL | 2018-05-17 17:37:27 |
  +------------+------+------+---------------------+
  ```

  ​	insert into t_date values('2018-05-17','17:41:22','2008-08-08 18:08:18',null);

  ```
  +------------+----------+---------------------+---------------------+
  | d1         | d2       | d3                  | d4                  |
  +------------+----------+---------------------+---------------------+
  | 2018-05-17 | 17:41:22 | 2008-08-08 18:08:18 | 2018-05-17 17:40:53 |
  +------------+----------+---------------------+---------------------+
  ```

  