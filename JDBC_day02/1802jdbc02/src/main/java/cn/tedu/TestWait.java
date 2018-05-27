package cn.tedu;

import java.sql.Connection;

public class TestWait {
	/**
	 * 验证多线程获取连接等待的效果
	 */
	public static void main(String[] args) {    
		DemoThread t1 = new DemoThread();
		t1.start();
		
		DemoThread t2 = new DemoThread();
		t2.start();
		
		DemoThread t3 = new DemoThread();
		t3.start();
		
		DemoThread t4 = new DemoThread();
		t4.start();
		
		DemoThread t5 = new DemoThread();
		t5.start();
		
		DemoThread t6 = new DemoThread();
		t6.start();
	}

	
//	public static void main(String[] args) throws Exception {
//		Connection conn1 = DBUtils.getConn();
//		System.out.println("1号连接线启动....");
//		Connection conn2 = DBUtils.getConn();
//		System.out.println("2号连接线启动....");
//		Connection conn3 = DBUtils.getConn();
//		System.out.println("3号连接线启动....");
//		Thread.sleep(5000);
//		conn3.close();  //此处的close并不是关闭连接,还是归还连接到连接池....
//		Connection conn4 = DBUtils.getConn();
//		System.out.println("4号连接线启动....");
//		
//		Thread.sleep(5000);
//		conn4.close();
//		Connection conn5 = DBUtils.getConn();
//		System.out.println("5号连接线启动....");
//		
//		Thread.sleep(5000);
//		conn5.close();
//		Connection conn6 = DBUtils.getConn();
//		System.out.println("6号连接线启动....");
//		
//		Thread.sleep(5000);
//		conn6.close();
//		Connection conn7 = DBUtils.getConn();
//		System.out.println("7号连接线启动....");
//	}
}
class DemoThread extends Thread{
	public void run() {
		try {
			Connection conn = DBUtils.getConn();
			System.out.println("连接计划已启动.....");
			Thread.sleep(5000);
			conn.close();
			System.out.println("连接归还等待再次启动.....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}