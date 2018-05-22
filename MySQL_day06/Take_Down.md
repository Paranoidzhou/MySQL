# day6

## 视图
- 数据库中存在多种对象，表和视图都是数据库中的对象 **表和视图不能重名**
- 视图是张虚拟的表，是通过sql的查询语句查询的结果的表保存成了一个视图
- 视图中的数据会随着原表中的数据改变而改变
- 视图实际上就是代表了一部分sql语句
- 创建视图的格式：
		create view 视图名 as 子查询;
		select * from 视图名;
		->例如：
		create view v_emp_10 as(select * from emp where deptno=10);
		select * from v_emp_10;
- 为什么使用视图：因为有些数据的查询需要书写大量的sql语句，每次书写比较麻烦，使用视图可以起到sql重用的作用，可以隐藏敏感信息。
		隐藏员工表的工资字段。
		create view v_emp_nosal as(select empno,ename,job,mgr,deptno from emp);

		1.创建emp表部门是20并且工资小于3000的视图
		create view v_emp_sal as(select * from emp where deptno=20 and sal<3000);

		2.创建emp表每个部门工资的总和，平均工资，最大工资，最小工资的视图
		create view v_emp_salcount as(select deptno,sum(sal),avg(sal),max(sal),min(sal) from emp group by deptno);

#### 视图的分类：
1.简单视图：在创建视图的子查询中不包含：关联查询、去重、函数、分组的视图称为简单视图,简单视图可以进行增，删，改数据。
2.复杂视图：和简单视图相反
- 在简单视图中进行增删改操作：
		1.视图中插入数据：
		insert into v_emp_10(empno,ename,deptno,sal) values (10001,'张三',10,300);

- 数据污染：往视图中插入一条视图中不显示，但是原表会显示的数据称为数据污染。
		insert into v_emp_10(empno,ename,deptno,sal) values(10002,'李四',20,400);
- 如果需要避免数据污染的出现，创建视图时需要使用with check option的关键字.
		create view v_emp_20 as(select*from emp where deptno=20) with check option;
		insert into v_emp_20(empno,ename,deptno,sal) values(10003,'赵六',20,400);	【插入成功】
		insert into v_emp_20(empno,ename,deptno,sal) values(10003,'赵六',10,400);	【插入报错：CHECK OPTION failed xxxxx】

- 测试在视图中修改和删除数据，修改和删除方式和table一样：
		1.修改v_emp_10视图中任意一条数据的工资为500;
		update v_emp_10 set sal = 4396 where empno=10001;

		2.删除v_emp_10中工资为500的员工;
		delete from v_emp_10 where sal=500;
> 修改和删除只能操作视图中存在的数据，不能作用于原表。

#### 修改视图：
- 格式：create or replace view 视图名 as 子查询;
		create or replace view v_emp_10 as(select*from emp where deptno=10 and sal<2000);

#### 删除视图：
		drop view v_emp_20;
		drop view if exists v_emp_20;(如果存在则删除，不存在也不会报错)

#### 视图别名：
		create view v_emp_name as(select ename name from emp);
		update v_emp_name set name='abc' where name='李四';

#### 视图总结：
1. 视图是数据库中的对象，代表一段sql语句;
2. 作用：重用sql,隐藏敏感信息;
3. 分类：简单视图（不包含函数，去重，分组，关联查询 | 可以支持增删改查操作）、复杂视图（不包含函数，去重，分组... | 只支持查询）。
4. 工作中一般使用视图时只进行查询操作，如果需要增删改查则直接对原表进行操作。

## 约束
- 什么是约束：约束就是对表字段添加的限制条件。

#### 非空： not null
- 添加非空约束的字段，值不能为null(空)
		create table t_null (id int,age int not null);

#### 唯一： unique
- 添加唯一约束的字段，值不能重复
		create table t_unique(id int,age int unique);
		insert into t_unique values(1,20);【成功】
		insert into t_unique values(2,20);【失败】

#### 主键约束：primary key
- 添加了主键约束的字段，值不能为Null、也不能重复。
- 创建表时添加主键约束
		create table t_pri (id int primary key);
- 创建表后添加主键约束
		create table t_pri (id int);
		alter table t_pri add primary key(id);
> 一个表只能有一个主键。
- 删除主键约束：
		alter table t_pri drop primary key;

#### 自增
1. 当字段赋值为null时，字段会自动增长。
2. 如果删除数据，自增数值不会减少。
3. 如果指定插入比较大的值，下次插入数据时会从最大值的基础上+1。
4. 如果使用delete删除全表数据，自增值不变。
5. 使用truncate关键字会致使自增数值清零。

#### 默认约束：default
- 给字段设置默认值，当字段不赋值的时候，默认值生效。
		create table t_def(id int,age int default 10);
#### 检查约束： check
- mysql不支持，但是语法通过，不会报错。
		create table t_check(id int,age int,check(age>10));

#### 外键约束：
- 什么是外键约束：用来保证两张表之间数据一致性和完整性的约束。
- 添加约束后，外键的值可以为null,可以重复，但不能是另外一张表不存在的数据。
- 添加约束后，外键指向的表不能先删除，如果需要删除表，要么删除外键约束，要么先删除存在外键的表。
- 添加约束后，外键指向的数据不能先删除。
- 外键的值通常指向另外一张表的主键
- 使用外键必须两张表使用相同的引擎（innodb）,myisam不支持外键。
- 工作中除非特殊情况，一般不使用外键约束，使用java代码通过逻辑对插入和删除的数据进行限制，因为加了外键约束后不方便测试。

##### 添加外键约束的格式：
		create table emp(id int,age int,deptid int,constraint 约束名　foreign key(deptid) reference 关联表名(关联的字段名))

- 创建部门和员工的两张表，创建一个db6数据库并使用
		create table dept(id int primary key auto_increment,name varchar(10));
		create table emp(id int primary key auto_increment,name varchar(10),deptid int,constraint fk_dept foreign key(deptid) references dept(id));
		insert into dept values(null,'神仙'),(null,'妖怪');
		insert into emp values(null,'如来',1);  【成功】
		insert into emp values(null,'观音',3);  【失败】

## 索引
- 导入数据：
		1.在终端中先登录mysql，然后在db6下面执行 source命令
		source 文件的绝对路径。
		2.导入完成后 测试执行：select count(*) from item2;

#### 什么是索引：
1. 索引是用来提高查询速度的技术，类似一个目录。
2. 为什么使用索引？：如果不使用索引数据会零散的保存在磁盘块中，磁盘块大小（4-8KB），查询数据时需要挨个遍历每一个磁盘块，知道找到数据为止，使用索引之后会在磁盘中将数据以树状结构进行保存，查询数据时从树状结构中进行查询，可以大大降低磁盘块的访问数量，从而提高查询速度。

#### 索引是越多越好吗？
- 索引会占用磁盘空间，所以创建时需谨慎，根据查询需求来决定创建什么索引。

#### 有索引就一定好吗？
- 索引需要建立在大量数据的表中，如果数据了不够大，有可能会降低查询效率。

#### 索引的分类(了解):
1.聚集索引(聚簇索引):数据保存在树状结构中,一张表只有一个聚集索引,数据库会自动为添加了有主键的表创建聚集索引.
2.非聚集索引:树状结构中没有数据,保存的是磁盘块的地址.

#### 如何创建索引(非聚集索引):
- 格式: create index 索引名 on 表名(字段名 [(长度)] );
- 创建索引之前先查询看时间: (1.27秒)
		select * from item2 where title='100';
- 创建title索引:
		create index_title on item2(title);
- 再次查询 看时间: 0.03 提高了无数倍.
		select * from item2 where title='100';

####　查看索引:
		show index from item2;

####　删除索引:
		drop index index_title on item2;

####　复合索引：
- 创建索引的时候添加多个字段，这种索引成为复合索引．
- 什么时候使用:当频繁使用多个字段作为查询条件的时候使用复合索引.
- 创建格式: create index index_title_price on item2(title,price);

#### 创建表时添加索引:
		create table t_index(id int,age int,index index_age(age));

##### 索引小总结:
1.索引会占磁盘空间,不是越多越好.
2.数据量小的表不要创建索引,否则会导致查询效率降低.
3.主键会自动创建聚集索引.
4.对经常出现在 where/order by/distinct后面的字段创建索引,可以是效果更好.
5.不要在修改太频繁的表中创建索引.数据修改,索引也得跟着修改,太麻烦.

## 事务:
- 什么是事务: 数据库执行sql语句的最小工作单元,不可拆分,同时成功或者同时失败.
- 事务的ACID特性  ****面试常考****
A : Atomicity    原子性,最小不可再次拆分.
C : Consistency  一致性,同时成功同时失败.
I : Isolation    隔离性,多个事务之间互不影响.
D : Durability   持久性,事务完成后数据持久保存.

- mysql中事务的指令:
1.查看自动提交的状态
		show variables like '%autocommit%';
2.设置自动提交的状态
		set aotucommit=0/1;
3.提交
		commit
4.回滚
		rollback
5.保存回滚点
		savepoint s1
6.回滚到某个点
		rollback to s1