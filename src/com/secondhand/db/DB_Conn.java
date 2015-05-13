package com.secondhand.db;


import java.io.PrintWriter;
import java.sql.*;
public class DB_Conn {
	private String driverName="com.mysql.jdbc.Driver"; //����������
	private String userName="root";	//���ݿ��û���
	private String userPassword="123456";	//���ݿ��û�����
	private String dbName="secondhanddb";//���ݿ���
	private String url="jdbc:mysql://localhost/"+dbName+"?useUnicode=true&characterEncoding=GB2312"  ; //���ݿ������ַ���
	private Connection  conn=null;  //���ݿ����Ӷ���
	private PrintWriter out=null;
	
	public  Statement sm=null;	//���ݿ�������
	
	
	//�������ݿ����Ӻ���
	public void ConnectDB(){
		try{
			Class.forName(driverName).newInstance();//ʵ����
			conn=DriverManager.getConnection(url,userName,userPassword);//������ݿ����� 
			sm=conn.createStatement();
			System.out.println("connect success");
		}
		catch(Exception e){
			e.printStackTrace();
			out.print("���ݿ�����ʧ��!");
		}
	}
	
	//�ͷ����ݿ����Ӻ���
	public void CloseDB(){
		try{
			if(sm!=null){
				sm.close();
			}
			conn.close();	
		}
		catch(SQLException e){
			e.printStackTrace();
			out.print("���ݿ�ر�ʧ��!");
		}
	
	}
	public static void main(String []args){
		DB_Conn dbconn=new DB_Conn();
		dbconn.ConnectDB();
	}
	
		//DB_Conn dbconn=new DB_Conn();
		//dbconn.ConnectDB();
		
	/*	String str="d";
		String sql="insert into test(name,password) values('����','fdfdfddd')";
		
		try{
			
			//sql=new String(sql.getBytes("ISO8859-1"),"GB2312");
			dbconn.sm.executeUpdate(sql);
			sql = "select * from test";
			ResultSet rst = dbconn.sm.executeQuery(sql);
		      while (rst.next()) {
		          System.out.println(rst.getString("name"));
		          
		      }
			

		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			dbconn.CloseDB();
		}
	}*/
	
}

