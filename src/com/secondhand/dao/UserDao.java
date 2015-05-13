package com.secondhand.dao;

import java.sql.ResultSet;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondhand.db.DB_Conn;
import com.secondhand.vo.User;




public class UserDao {
	DB_Conn db_conn=new DB_Conn();
	//添加新用户函数
	public int add(User user){
		db_conn.ConnectDB();
		try{
			String sql="insert into user(user_name,user_password, user_email)"
				+" values('" + user.getUser_name() +"','"+user.getUser_password()
				+ "','"+user.getUser_email()+"')";
				
				
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
			String sql= "select * from user where user_name='"+login_name+
				"' and user_password ='" + login_password+"'";
			
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
	public int update_user_info(User user){

		
		db_conn.ConnectDB();
		try{
			String sql="update user set user_password='"+user.getUser_password()
						+"',user_email='"+user.getUser_email()+"', user_phone='" 
						+user.getUser_phone()+"',user_qq='"+user.getUser_qq()
						+"' where user_name='"+user.getUser_name()+"'";
				
			
			//sql=new String(sql.getBytes("ISO8859-1"),"GB2312");
			System.out.println(sql);
			int i=db_conn.sm.executeUpdate(sql);
			return i;
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
			return -1;
		}
		catch(Exception e){
			e.printStackTrace();
			return -2;
		}
		finally{
			db_conn.CloseDB();
		}
	}
	public void delete(String name){
		
		int rs;
		db_conn.ConnectDB();
		try{
			String sql="delete from user where user_name='"+name+"'";
				
			rs = db_conn.sm.executeUpdate(sql);
		
		
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			db_conn.CloseDB();
		}
	}
	public List<User> find_all_user(){

		ResultSet rs = null;
		List<User> user_list = new ArrayList<User>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from user";
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				User user = new User();
				
				user.setUser_name(rs.getString(1));
				user.setUser_password(rs.getString(2));
				user.setUser_email(rs.getString(3));
				user.setUser_phone(rs.getString(4));
				user.setUser_pic(rs.getString(5));
				
				user_list.add(user);
			}
			
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			db_conn.CloseDB();
		}
		return user_list;
	}
	public User find_by_user_name(String user_name){

		ResultSet rs = null;
		User user = null;
		db_conn.ConnectDB();
		try{
			String sql= "select * from user";
			rs = db_conn.sm.executeQuery(sql);
			if (rs.next())
			{
				user = new User();
				
				user.setUser_name(rs.getString(1));
				user.setUser_password(rs.getString(2));
				user.setUser_email(rs.getString(3));
				user.setUser_phone(rs.getString(4));
				user.setUser_qq(rs.getString(5));
				user.setUser_pic(rs.getString(6));
				
			}
			
		}
		catch(SQLException sqlE){
			sqlE.printStackTrace();
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			db_conn.CloseDB();
		}
		return user;
	}
	
	public static void main(String []args){
		UserDao  userDao = new UserDao();
		
		//userDao.find_all_restaurant();
		//userDao.login("du", "323");
		
	}

}
