package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;


public class StudentDemo {
	@Test
	public void insert(){
		String name = "小米";
		int age = 18;
		String gender = "女";
		
		//把零散数据封装到对象中
		Student s = new Student();
		s.setName(name);
		s.setAge(age);
		s.setGender(gender);
		
		//调用Dao里面的插入方法
		StudentDao sDao = new StudentDao();
		sDao.insert(s);
	}
	
	@Test
	public void findAll(){
		StudentDao sDao = new StudentDao();
		List<Student> students = sDao.findAll();
		//遍历集合
		for (Student student : students) {
			System.out.println(student);
		}
	}
	
	@Test
	public void update(){
		
		String name = "魅族";
		int age = 13;
		String gender = "男";
		int id = 1;
		
		Student s = new Student(id,name,age,gender);
		StudentDao sDao = new StudentDao();
		sDao.update(s);
	}
	
	@Test
	public void delete(){
		int id = 2;
		
		//把零散数据封装到对象中
		Student s = new Student();
		s.setId(id);

		//调用Dao里面的插入方法
		StudentDao sDao = new StudentDao();
		sDao.delete(s);
	}
}
