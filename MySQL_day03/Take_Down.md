# Day03:

### 创建4张表、导入一堆数据
### 其后验证4张表是否都有数据：
		select * from emp;
		select * from dept;
		select * from t_item;
		select * from t_item_category;

## 没有条件的查询：
		select * from 表名；
		select 字段1,字段2.....表名;

## 带条件的查询 ：
- 列值为null和不为Null：
		1. 查询没有上级领导的员工编号，姓名，工资:
		select empno,ename,sal from emp where mgr is null;

		2.查询有上级领导的员工编号，姓名，领导编号:
		select empno,ename,mgr from emp where mgr is not null;

		3.查询没有奖金(comm)的员工信息：
		select *from emp where comm is null;

- 别名 ：
		select empno as '员工编号',ename as '姓名' from emp;
		select empno '员工编号',ename '姓名' from emp;
		select empno 员工编号,ename 姓名 from emp;

- 去重 ：distinct
		查询emp表中所有员工的职位（去重）：
		select distinct job from emp;

- 比较运算符 ：  >  <  =   >=  <=  !=  <>(大于小于)
		1.查询工资高于2000的所有员工编号，姓名，职位，工资：
		select empno,ename,job,sal from emp where sal>2000;

		2.查询工资小于等于1600的所有员工编号，姓名，工资：
		select empno,ename,sal from emp where sal<1600;

		3.查询部门编号是20的所有员工的姓名，职位，部门编号：
		select ename,job,deptno from emp where deptno=20;

		4.查询职位是manager的所有员工姓名，职位：
		select ename,job from emp where job= 'manager';

		5.查询不是10号部门的所有员工姓名，工资，部门编号（两种写法）：
		select ename,sal,deptno from emp where deptno!=10;
		select ename,sal,deptno from emp where deptno<>10;

		6.查询单价等于23的商品信息：
		select * from t_item where price = 23;

		7.查询单价不等于8443的商品信息：
		select * from t_item where price!=8443;
		select * from t_item where price<>8443;

- and 和 or :
  ->and 等效java的 &&
  ->or  等效java的 ||
		1.查询不是10号部门，工资小于3000的员工编号，姓名，工资，部门编号：
		select empno,ename,sal,deptno from emp where deptno!=10 and sal<3000;

		2.查询部门是30或者上级领导为7698的所有员工的姓名，部门编号，上级领导编号：
		select ename,deptno,mgr from emp where deptno=30 or mgr=7698;

- in :
  ->如果查询字段的值为多个的时候可以使用In关键字：
		1.查询person表中 年龄(age)是25，28，30，22岁的所有信息：
		select * from person where age in (25,28,30,22);

		2.查询员工工资是800，950，1600的员工名字和工资：
		select ename,sal from emp where sal in(800,950,1600);

- between x and y ：
  ->在某两个数值之间，包含and两边的数值：
		1.查询员工工资在500-1000的所有员工名字和工资：
		select ename,sal from emp where sal between 500 and 1000;

- like ：
  -> _ ：这个下划线它代表单个未知字符
  -> % ：这个百分号它代表多个位置字符
  ->举例：
       * 以a开头 （a%）
       * 以a结尾 （%a）
       * 第二个字符是a （_a%）
       * 包含a  （%a%）
       * 倒数第三个字符是a （%a__）
       * 第二个和最后一个是a (_a%a)

	   1.查询名字以K开头的所有员工名字：
	   select ename from emp where ename like 'k%';

	   2.查询商品标题包含记事本的商品：
	   select title from t_item where title like '%记事本%';

	   3.查询单价低于100的记事本：
	   select * from t_item where price<100 and item_type like'%记事本%';

	   4.查询有图片的得力商品：
	   select * from t_item where title like '%得力%' and image is not null;

	   5.查询单价介于50到200之间的得力商品：
	   select * from t_item where title like '%得力%' and price between 50 and 200;

	   6.查询有赠品的dell商品：
	   select * from t_item where title like '%Dell%' and sell_point like '%赠%';

	   7.查询标题不包含得力的商品：
	   select * from t_item where title not like '%得力%';

	   8.查询价格介于50和200之外的所有商品：
	   select * from t_item where price not between 50 and 200;

## 查询结果的排序 order by
- 格式： order by 字段名：
- 默认升序查询，指定升序是：asc   降序：desc
		1.查询员工的名称和工资，按照工资降序排序。
		select ename,sal from emp order by sal desc;

		2.查询单价在100一下的商品名称和价格，按照价格降序排序：
		select title,price from t_item where price<100 order by price desc;

- 多字段排序，当第一个字段有相同值时，第二个字段排序开始：
		1.查询所有员工名称，部门编号，工资，部门编号降序排序，工资升序排序。
		select ename,deptno,sal from emp order by deptno desc,sal;

		2.查询所有dell商品，按分类id升序，单价降序排序。
		select title,category_id,price from t_item where title like '%dell%' order by category_id ,price desc;

- 分页查询：limit
  -> limit 跳过条数，每页条数(需要查询的条数)
  -> 查询第三页 每页20条：     limit 40,20
		1.查询商品表中商品名称和价格，第二页数据每页5条。
		select title,price from t_item limit 5,5;

		2.查询所有商品，单价升序，显示第三页，每页3条数据。
		select title,price from t_item order by price limit 6,3;

		3.查询拿最高工资的员工信息。
		select * from emp order by sal desc limit 0,1;

## 数值计算: + - * / %  (mod())
- % 和 mod 都是取余的作用：
		7%2  等效 mod(7,2)
		select sal+1 from emp;

		1.查询所有商品的单价，库存，总价。
		select price,num,price*num from t_item;

## 日期相关函数：
- 获取当前日期+时间（年月日 时分秒） ： now()
		select now();
- 获取当前日期（年月日）：  curdate()
		select curdate();
- 获取当前时间（时分秒）： curtime()
		select curtime();
- 从日期和时间中提取日期（年月日）：
  - select data(字段名);
		select date(now());
- 从日期和时间中提取时间（时分秒）：
  - select time(字段名);
		select time(now());

		1.查询商品的创建时间.
		select date(created_time) from t_item;
- 单独提取年 月 日 时 分 秒 ：
		select extract(时间单词 from 字段名)
		select extract(year from now());
		select extract(month from now());
		select extract(day from now());
		select extract(hour from now());
		select extract(minute from now());
		select extract(second from now());

		案例：查询员工入职的年份：
		select extract(year from hiredate) from emp;

- 日期格式化 date_format()：
  -> 格式： date_format(时间，格式)
		%Y ：代表4位年
		%y ：代表2位年
		%m ：代表2位月
		%c ：代表1位月
		%d ：代表日
		%H ：代表24小时制
		%h ：代表12小时制
		%i ：代表分
		%s ：代表秒

		案例： select date_format(now(),'%Y年%m月%d日 %H时%i分%s秒');
		输出: +------------------------------------------------+
			  | date_format(now(),'%Y年%m月%d日 %H时%i分%s秒') |
			  +------------------------------------------------+
			  | 2018年05月18日 16时09分50秒                    |
			  +------------------------------------------------+

		1.查询商品的创建日期(created_time)年月日。
		select date_format(created_time,'%Y年%m月%d日') from t_item;

- 把不规则日期格式转成标准格式：
  ->格式： str_to_date(日期字符串，格式)
		select str_to_date('25号12月2015年','%d号%m月%Y年');

### ifnull():
- 格式： age=ifnull(x,y) 判读x是否位Null、如果是则age=y，如果不是则age=x;
		案例：把员工表没有奖金的则把奖金修改为0.
		update emp set comm=ifnull(comm,0);

## 聚合函数：
- 对多行数据进行合并统计：
	- 求和     sum(字段名);    例如：求工资总合  sum(sal);
	- 平均值	avg(字段名);    例如：求工资平均值 avg(sal);
	- 最大值   max(字段名);    例如：求奖金最大值 max(comm);
	- 最小值	min(字段名);	 例如：求年龄最小值 min(age);
	- 统计数量 count(*);
	  ->例如：查询20岁以下有多少人;
	  select count(*) form emp whrer age < 20;


		1.查询员工表的平均工资。
		select avg(sal) from emp;

		2.查询10号部门的最高工资。
		select max(sal) from emp where deptno = 10;

		3.查询dell商品的库存总和。
		select sum(num) from t_item where title like '%dell%';

		4.查询得力商品的条数。
		select count(*) from t_item where title like '%得力%';

		5.查询员工姓名中包含a的最低工资。
		select min(sal) from emp where ename like '%a%';

## 字符串相关函数：
- concat(a,b)：字符串连接的函数，将a和b拼接；
		select concat('a','b')
		案例：查询每个员工的工资，工资后面显示元。
		select ename,concat(sal,'元') from emp;

- char_length(str)：获取字符串的长度；
		案例：获取每个员工姓名的长度。
		select ename,char_length(ename) from emp;

- instr(str,subStr)：获取subStr在Str中的位置：
		案例：查询NBA中的A的位置：
		select instr('nba','a');

- locate(subStr,str)：获取subStr在Str中的位置：
		案例：查询LPL中的P的位置：
		select locate('p','lpl');

- insert(str,start,length,newstr)：插入字符串：
		案例：select insert('abcdefg',3,2,'m');
		输出结果： abmefg

- lower(str)：转小写：
		案例：select lower('NbA');

- upper(str)：转大写：
		案例：select upper('lPl');

- trim(str)：去空白：
		案例：select trim('N   b A  ');

- left(str,length)：从左边截取多少个字符：
		案例：select left('avcdefg',3);

- right(str,length)：从右边截取多少个字符：
		案例：select right('abcdefg',3);

- substring(str,index,length)：截取字符串：
		案例：select substring('abcdefg',2,3);

- replace(str,odl,new)：替换字符串：
		案例：select replace('人工智能','智能','智障');

- repeat(str,count)：重复：
		案例：select repeat('人工智障',5);

- reverse(str)：反转：
		案例：select reverse('keke');

## 数学相关函数：
- 向下取整： floor(num)
		select floor(3.88);           输出：3

- 四舍五入： round(num,m)
		select round(2.68);		   输出：3
		select round(3.1415926,2);    输出：3.14（取小数点后面两位）

- 非四舍五入： truncate(num,m)
		select truncate(3.1415926,1); 输出：3.1

- 随机数： rand()  0-1
		案例：取100以内的整数：
		select floor(rand()*100);