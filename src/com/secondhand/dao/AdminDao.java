package com.secondhand.dao;

import java.sql.ResultSet;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondhand.db.DB_Conn;
import com.secondhand.vo.User;




public class AdminDao {
	DB_Conn db_conn=new DB_Conn();
	//添加新用户函数
	public int add(User user){
		db_conn.ConnectDB();
		try{
			String sql="insert into admin(admin_name,admin_password)"
				+" values('" + user.getUser_name() +"','"+user.getUser_password()
				+"')";
				
				
			//sql=new String(sql.getBytes("ISO-8859-1"),"GB2312");
			System.out.println(sql);
			int i=db_conn.sm.executeUpdate(sql);
			//System.out.println("Sucess");
			return 1;
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		finally{
			db_conn.CloseDB();
		}
	}
	
	public boolean login(String login_name, String login_password){
		boolean isLogin = false;
		ResultSet rs = null;
		db_conn.ConnectDB();
		try{
			/*String sql= "select * from user where user_name='"+login_name+
				"' and user_password ='" + login_password + "' and user_type =" + type
				+ " and user_state = " + 1;*/
			String sql= "select * from admin where admin_name='"+login_name+
				"' and admin_password ='" + login_password+"'";
			
			System.out.println(sql);
			rs = db_conn.sm.executeQuery(sql);
			
			if (rs.next())
			{
				isLogin = true;
				System.out.println("find");
			}
			return isLogin;
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
			return isLogin;
		}
		catch(Exception e){
			e.printStackTrace();
			return isLogin;
		}
		finally{
			db_conn.CloseDB();
		}
	}
	
	
	public static void main(String []args){
		AdminDao  adminDao = new AdminDao();
		
		//userDao.find_all_restaurant();
		//userDao.login("du", "323");
		
	}

}
