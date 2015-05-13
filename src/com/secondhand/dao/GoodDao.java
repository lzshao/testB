package com.secondhand.dao;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondhand.db.DB_Conn;
import com.secondhand.vo.Good;


public class GoodDao {
	DB_Conn db_conn=new DB_Conn();
	//添加新用户函数
	public int add(Good good){
		db_conn.ConnectDB();
		try{
			String sql="insert into good(good_name,type_id,good_price, good_intro, good_state, add_name, add_date)"
				+" values('" + good.getGood_name() +"',"+good.getType_id()+ ","+good.getGood_price()
				+",'" + good.getGood_intro()+ "',"+good.getGood_state()+",'"
				+good.getAdd_name()+"','"+good.getAdd_date()+"')";
				
			//sql=new String(sql.getBytes("ISO-8859-1"),"GB2312");
			System.out.println(sql);
			int i=db_conn.sm.executeUpdate(sql);
			//System.out.println("Sucess");
			
			ResultSet rs = null;
			String sql2 = " select max(id) as id  from good ";
			rs = db_conn.sm.executeQuery(sql2);
			if(rs.next()){
				int id = rs.getInt(1);
				return id;
			}
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
	
	public void delete(int good_id){
		
		int rs;
		db_conn.ConnectDB();
		try{
			String sql="delete from good where id="+good_id;
				
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
	public List<Good> find_good_by_classfity(int classify_id){

		ResultSet rs = null;
		List<Good> good_list = new ArrayList<Good>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from good where type_id ="+ classify_id +" and good_state="+1;
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Good tmp = new Good();
				tmp.setId(rs.getInt(1));
				tmp.setGood_name(rs.getString(2));
				tmp.setType_id(rs.getInt(3));
				tmp.setGood_price(rs.getFloat(4));
				tmp.setGood_intro(rs.getString(5));
				tmp.setGood_state(rs.getInt(6));
				tmp.setAdd_name(rs.getString(7));
				tmp.setAdd_date(rs.getString(8));
				System.out.println(rs.getString(2));
				good_list.add(tmp);
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
		return good_list;
	}
	public List<Good> find_good_by_keyword(String keyword){

		ResultSet rs = null;
		List<Good> good_list = new ArrayList<Good>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from good where good_name like '"+ "%"+keyword+"%"+"' and good_state="+1;
			System.out.println(sql);
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Good tmp = new Good();
				tmp.setId(rs.getInt(1));
				tmp.setGood_name(rs.getString(2));
				tmp.setType_id(rs.getInt(3));
				tmp.setGood_price(rs.getFloat(4));
				tmp.setGood_intro(rs.getString(5));
				tmp.setGood_state(rs.getInt(6));
				tmp.setAdd_name(rs.getString(7));
				tmp.setAdd_date(rs.getString(8));
				System.out.println("search find");
				good_list.add(tmp);
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
		return good_list;
	}
	
	public List<Good> find_good_checked(){

		ResultSet rs = null;
		List<Good> good_list = new ArrayList<Good>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from good where good_state="+0;
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Good tmp = new Good();
				tmp.setId(rs.getInt(1));
				tmp.setGood_name(rs.getString(2));
				tmp.setType_id(rs.getInt(3));
				tmp.setGood_price(rs.getFloat(4));
				tmp.setGood_intro(rs.getString(5));
				tmp.setGood_state(rs.getInt(6));
				tmp.setAdd_name(rs.getString(7));
				tmp.setAdd_date(rs.getString(8));
			
				good_list.add(tmp);
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
		return good_list;
	}
	public List<Good> find_good_issued(){

		ResultSet rs = null;
		List<Good> good_list = new ArrayList<Good>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from good where good_state="+1;
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Good tmp = new Good();
				tmp.setId(rs.getInt(1));
				tmp.setGood_name(rs.getString(2));
				tmp.setType_id(rs.getInt(3));
				tmp.setGood_price(rs.getFloat(4));
				tmp.setGood_intro(rs.getString(5));
				tmp.setGood_state(rs.getInt(6));
				tmp.setAdd_name(rs.getString(7));
				tmp.setAdd_date(rs.getString(8));
			
				good_list.add(tmp);
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
		return good_list;
	}
	public Good find_by_good_id(int good_id){

		ResultSet rs = null;
		Good tmp = null;
		db_conn.ConnectDB();
		try{
			String sql= "select * from good where id="+good_id;
			rs = db_conn.sm.executeQuery(sql);
			if (rs.next())
			{
				tmp = new Good();
				tmp.setId(rs.getInt(1));
				tmp.setGood_name(rs.getString(2));
				tmp.setType_id(rs.getInt(3));
				tmp.setGood_price(rs.getFloat(4));
				tmp.setGood_intro(rs.getString(5));
				tmp.setGood_state(rs.getInt(6));
				tmp.setAdd_name(rs.getString(7));
				tmp.setAdd_date(rs.getString(8));
			
				
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
		return tmp;
	}
	
	public int update_good_state(int good_id, int state){


		db_conn.ConnectDB();
		try{
			String sql="update good set good_state="+state+" where id="+good_id;
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
	public int update_good_info(Good good){

		
		db_conn.ConnectDB();
		try{
			String sql="update good set good_name='"+good.getGood_name()+"',type_id="+good.getType_id()
			            +",good_price="+ good.getGood_price()+",good_intro='"+good.getGood_intro()
			            +"',good_state="+good.getGood_state()+",add_name='"+good.getAdd_name()
			            +"',add_date='"+good.getAdd_date()+"' where id="+good.getId();
			           
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
	
	public List<Good> find_good_by_user_name(String user_name){

		ResultSet rs = null;
		List<Good> good_list = new ArrayList<Good>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from good where add_name ='"+ user_name+"'";
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Good tmp = new Good();
				tmp.setId(rs.getInt(1));
				tmp.setGood_name(rs.getString(2));
				tmp.setType_id(rs.getInt(3));
				tmp.setGood_price(rs.getFloat(4));
				tmp.setGood_intro(rs.getString(5));
				tmp.setGood_state(rs.getInt(6));
				tmp.setAdd_name(rs.getString(7));
				tmp.setAdd_date(rs.getString(8));
			
				good_list.add(tmp);
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
		return good_list;
	}
	public static void main(String []args){
		GoodDao  dao = new GoodDao();

		//userDao.find_all_restaurant();
		//userDao.login("du", "323");
		
	}

}
