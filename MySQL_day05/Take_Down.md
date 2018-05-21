# day5

## 表设计之关联关系
#### 一对一：
- 什么是一对一：有两张表A和B，A表中有一条数据对应B表中的一条数据称为一对一关系。
- 应用场景：用户表和用户信息扩展表....
- 如何建立关系：在从表中添加一个字段记录主表的id。用户表和用户信息扩展表中，用户表为主表，信息扩展表为从表，用户表中的id称为主键，从表中记录主表id的字段称为外键。
- 主键是用来表示数据的唯一性，外键用来和其他表建立关系。

		案例：创建user(id,username,password)和userinfo(id,nick,age,phone)两张表然后往两个表里面各插入3条有关系的数据。
		1.创建 user 表:
		create table user(id int primary key auto_increment,username varchar(10),password varchar(10));
		2.创建 userinfo 表:
		create table userinfo(id int,nick varchar(10),age int,phone varchar(11));
		3.插入数据到user表---userinfo表:
		insert into user values
		(null,'libai','admin'),
		(null,'lisi ','admin'),
		(null,'liubei','123');

		insert into userinfo values
		(1,'李白',25,'11749653110'),
		(2,'李四',30,'25884386751'),
		(3,'刘备',40,'37158846112');

		(1)查询每一个用户的用户名，昵称，电话。
		select s1.username,s2.nick,s2.phone
		from user s1 join userinfo s2
		on s1.id = s2.id;

		(2)查询名字叫李白的所有信息。
		select *
		from user s1 join userinfo s2
		on s1.id = s2.id;
		where s2.nick like'李白';

#### 一对多：
- 什么是一对多：AB两张表中A表中一条数据对应B表中多条数据，并且B表中一条数据对应A表中一条数据，两张表的关系称为一对多。
- 应用场景：部门和员工，商品和分类等.....
- 如何建立关系：一对多的两张表，在多的表中添加一个字段记录另外一张表的id

		案例：创建t_emp(id,name,sal,deptid),t_dept(id,name,loc)
		1.创建 t_emp 表
		create table t_emp(id int primary key auto_increment,name varchar(10),sal int,deptid int);
		2.创建t_dept 表
		create table t_dept(id int primary key auto_increment,name varchar(10),loc varchar(10));
		3.插入数据到t_emp表----t_dept表
		insert into t_emp values
		(1101,'猪八戒',1024,1),
		(1102,'孙悟空',4396,1),
		(1103,'蜘蛛精',4321,2),
		(1104,'白骨精',1080,2);

		insert into t_dept values
		(1,'神仙部','西天'),
		(2,'妖怪部','盘丝洞');

		(1)查询每个部门对应的每个员工
		select e.name,d.name
		from t_emp e join t_dept d
		on e.deptid = d.id;

		(2)查询每个员工对应的每个部门，只显示工资大于2000的信息，
		select e.name,e.sal,d.name
		from t_emp e join t_dept d
		on e.deptid = d.id
		where sal > 2000;

#### 多对多：
- 什么是多对多：AB两张表中A表中一条数据对应B表中多条数据，同时B表的一条数据对应A表的多条数据，这种关系称为多对多。
- 应用场景：老师表和学生表，用户表和角色表等.....
- 如何建立关系：需要创建一个关系表，在关系表中，记录两个表的Id.
		案例：创建老师表(id,name)和学生表(id,name)，创建老师和学生关系表t_s(tid,sid).
		1.创建老师表teacher:
		create table teacher(id int,name varchar(10));

		2.创建学生表student:
		create table student(id int,name varchar(10));

		3.创建老师和学生关系表t_s:
		create table t_s(tid int,sid int);

		4.插入数据至三张表中：
		insert into teacher values
		(1,'唐僧'),(2,'刘备'),(3,'苍老师');

		insert into student values
		(1,'悟空'),(2,'关羽'),(3,'小明');

		//关系表中插入数据：唐僧是悟空和小明的老师，刘备是关羽的老师，苍老师是所有人的老师;
		insert into t_s values(1,1),(1,3),(2,2),(3,1),(3,2),(3,3);

		(1)查询老师和学生表中，每个学生名字和对应的老师名字。
		select t.name,s.name
		from teacher t join t_s ts
		on t.id=ts.tid
		join student s
		on ts.sid=s.id;

		(2)查询苍老师的所有学生姓名
		select s.name
		from teacher t join t_s ts
		on t.id=ts.tid
		join student s
		on ts.sid=s.id
		where t.name='苍老师';

		(3)查询小明的所有老师
		select t.name
		from teacher t join t_s ts
		on t.id=ts.tid
		join student s
		on ts.sid=s.id
		where s.name='小明';

## 自关联
- 什么是自关联：在一张表中有一个字段指向当前表的id，这种称为自关联。
- 什么时候使用自关联：当保存的数据由层级关系并且不确定有多少层的时候使用自关联.
		1. 查询员工姓名和对应的上级姓名
		select e.ename,m.ename
		from emp e join emp m
		on e.mgr = m.empno;

## 连接方式和关联关系
- 连接方式：包括内连接和外连接（左外和右外）,是指通过sql查询两张表数据时使用的查询方。
- 关联关系：一对一、一对多、多对多，它是指设计表时两张表存在的逻辑关系。

#### 案例：权限管理的表设计：
- 首先创建db5数据库并使用：
		create database db5;
		use db5;

- 然后创建5张表：user(id,name),role(id,name),module(id,name),用户和角色关系表u_r(uid,rid),角色和权限关系表r_m(rid,mid)：
		1. 创建 user 表：
		create table user(id int primary key auto_increment,name varchar(10));

		2. 创建 role 表：
		create table role(id int primary key auto_increment,name varchar(10));

		3. 创建 module 表：
		create table module(id int primary key auto_increment,name varchar(10));

		4. 创建 u_r 表：
		create table u_r(uid int,rid int);

		5. 创建 r_m 表：
		create table r_m(rid int,mid int);

- 插入数据user：刘德华，张学友，凤姐
		insert into user values
		(1011,'刘德华'),(1012,'张学友'),(1013,'凤姐');

- 插入数据role：男游客，男会员，女游客，女会员，女管理员
		insert into role values(2011,'男游客'),(2012,'男会员'),(2013,'女游客'),(2014,'女会员'),(2015,'女管理员');

- 插入数据module：男浏览，男发帖，女浏览，女发帖，女删帖
		insert into module values(3011,'男浏览'),(3012,'男发帖'),(3013,'女浏览'),(3014,'女发帖'),(3015,'女删帖');

- 建立关系：刘德华是男游客，张学友是男会员，凤姐是女游客和男会员，男游客对应男浏览，男会员对应男浏览和男发帖，女游客对应女浏览，女管理员对应女浏览女发帖和女删帖;
		insert into u_r values(1011,2011),(1012,2012),(1013,2013),(1013,2012);
		insert into r_m values(2011,3011),(2012,3011),(2012,3012),(2013,3013),(2015,3013),(2015,3014),(2015,3015);

		1.查询每个用户对应的所有权限
		select u.name,m.name
		from user u join u_r ur
		on u.id=ur.uid
		join r_m rm
		on ur.rid=rm.rid
		join module m
		on rm.mid=m.id;

		2.查询刘德华对应的所有权限
		select u.name,m.name
		from user u join u_r ur
		on u.id=ur.uid
		join r_m rm
		on ur.rid=rm.rid
		join module m
		on rm.mid=m.id
		where u.name='刘德华';

		3.有男发帖的用户都有哪些
		select u.name,m.name
		from user u join u_r ur
		on u.id=ur.uid
		join r_m rm
		on ur.rid=rm.rid
		join module m
		on rm.mid=m.id
		where m.name='男发帖';