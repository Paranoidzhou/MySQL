package cn.tedu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDao {
	
	public void insert(Student s) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "insert into student values(null,?,?,?)";
			stat = conn.prepareStatement(sql);
			
			//替换?
			stat.setString(1,s.getName());
			stat.setInt(2, s.getAge());
			stat.setString(3, s.getGender());
			
			//执行sql
			stat.executeUpdate();
			System.out.println("增添执行成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
	
	public ArrayList<Student> findAll(){
		ArrayList<Student> students = new ArrayList<Student>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from student");
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				
				//把零散数据封装
				Student s = new Student(id,name,age,gender);
				//把对象装到集合中:
				students.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
		
		return students;
	}
	
	public void update(Student s) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "update student set name=?, age=?, gender=? where id=?";
			stat = conn.prepareStatement(sql);
			//替换?
			stat.setString(1, s.getName());
			stat.setInt(2, s.getAge());
			stat.setString(3, s.getGender());
			stat.setInt(4,s.getId());
			//执行sql
			stat.executeUpdate();
			System.out.println("修改成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
	
	public void delete(Student s) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			String sql = "delete from student where id=?";
			stat = conn.prepareStatement(sql);
			stat.setInt(1, s.getId());
			stat.executeUpdate();
			System.out.println("删除成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.close(conn, stat, rs);
		}
	}
}
