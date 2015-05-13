package com.secondhand.dao;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondhand.db.DB_Conn;
import com.secondhand.vo.Good;
import com.secondhand.vo.Picture;


public class PictureDao {
	DB_Conn db_conn=new DB_Conn();
	//添加新用户函数
	public int add(Picture pic){
		db_conn.ConnectDB();
		try{
			String sql="insert into picture(good_id,pic_path)"
				+" values(" + pic.getGood_id() +",'"+pic.getPic_path()+"')";
				
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
	
	public void delete(int pic_id){
		
		int rs;
		db_conn.ConnectDB();
		try{
			String sql="delete from picture where id="+pic_id;
				
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
	public List<Picture> find_good_picture(int good_id){

		ResultSet rs = null;
		List<Picture> pic_list = new ArrayList<Picture>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from picture where good_id ="+ good_id;
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Picture tmp = new Picture();
				tmp.setId(rs.getInt(1));
				tmp.setGood_id(rs.getInt(2));
				tmp.setPic_path(rs.getString(3));
			
				pic_list.add(tmp);
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
		return pic_list;
	}
	
	public static void main(String []args){
		PictureDao  dao = new PictureDao();

		//userDao.find_all_restaurant();
		//userDao.login("du", "323");
		
	}

}
