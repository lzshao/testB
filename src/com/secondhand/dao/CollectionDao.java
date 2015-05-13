package com.secondhand.dao;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondhand.db.DB_Conn;
import com.secondhand.vo.Collection;
import com.secondhand.vo.Good;
import com.secondhand.vo.Picture;


public class CollectionDao {
	DB_Conn db_conn=new DB_Conn();
	//添加新用户函数
	public int add(Collection collection){
		if(find_good_collection(collection.getGood_id(), collection.getUser_name())){
			return 0;
		}
		db_conn.ConnectDB();
		try{
			String sql="insert into collection(good_id,user_name)"
				+" values(" + collection.getGood_id() +",'"+collection.getUser_name()+"')";
				
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
	public boolean find_good_collection(int good_id,String user_name){

		ResultSet rs = null;
		boolean flag = false;
		db_conn.ConnectDB();
		try{
			String sql= "select * from collection where good_id ="+ good_id+" and user_name='"+user_name+"'";
			rs = db_conn.sm.executeQuery(sql);
			if (rs.next())
			{
				flag = true;
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
		return flag;
	}
	public void delete(int id){
		
		int rs;
		db_conn.ConnectDB();
		try{
			String sql="delete from collection where id="+id;
				
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
	public void delete_all(){
		
		int rs;
		db_conn.ConnectDB();
		try{
			String sql="delete from collection";
				
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
	public List<Collection> find_collection_by_good_id(int good_id){

		ResultSet rs = null;
		List<Collection> collection_list = new ArrayList<Collection>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from collection where good_id ="+ good_id;
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Collection tmp = new Collection();
				tmp.setId(rs.getInt(1));
				tmp.setGood_id(rs.getInt(2));
				tmp.setUser_name(rs.getString(3));
			
				collection_list.add(tmp);
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
		return collection_list;
	}
	
	public List<Collection> find_collection_by_user_name(String user_name){

		ResultSet rs = null;
		List<Collection> collection_list = new ArrayList<Collection>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from collection where user_name ='"+ user_name +"'";
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Collection tmp = new Collection();
				tmp.setId(rs.getInt(1));
				tmp.setGood_id(rs.getInt(2));
				tmp.setUser_name(rs.getString(3));
			
				collection_list.add(tmp);
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
		return collection_list;
	}
	
	
	public static void main(String []args){
		CollectionDao  dao = new CollectionDao();

		//userDao.find_all_restaurant();
		//userDao.login("du", "323");
		
	}

}
