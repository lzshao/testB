package com.secondhand.dao;

import java.sql.ResultSet;




import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.secondhand.db.DB_Conn;
import com.secondhand.vo.Classify;






public class ClassifyDao {
	DB_Conn db_conn=new DB_Conn();
	//添加新用户函数
	public int add(Classify classify){
		db_conn.ConnectDB();
		try{
			String sql="insert into classify(parent_id, name)"
				+" values(" + classify.getParent_id() + ",'"+ classify.getName()+"')";
				
				
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
	
	public void delete_by_id(int classify_id){
		
		int rs;
		db_conn.ConnectDB();
		try{
			
			String sql;
			sql="delete from classify where parent_id="+classify_id;
				
			rs = db_conn.sm.executeUpdate(sql);
		
			sql = "delete from classify where id="+classify_id;
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
	public List<Classify> find_all_classify(){

		ResultSet rs = null;
		List<Classify> classify_list = new ArrayList<Classify>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from classify";
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Classify tmp = new Classify();
				tmp.setId(rs.getInt(1));
				tmp.setParent_id(rs.getInt(2));
				tmp.setName(rs.getString(3));
				System.out.println(rs.getString(3));
				classify_list.add(tmp);
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
		return classify_list;
	}
	
	public List<Classify> find_all_first(){

		ResultSet rs = null;
		List<Classify> classify_list = new ArrayList<Classify>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from classify where parent_id="+0;
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Classify tmp = new Classify();
				tmp.setId(rs.getInt(1));
				tmp.setParent_id(rs.getInt(2));
				tmp.setName(rs.getString(3));
				classify_list.add(tmp);
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
		return classify_list;
	}
	public List<Classify> find_second_by_classify_id(int classify_id){

		ResultSet rs = null;
		List<Classify> classify_list = new ArrayList<Classify>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from classify where parent_id="+classify_id;
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Classify tmp = new Classify();
				tmp.setId(rs.getInt(1));
				tmp.setParent_id(rs.getInt(2));
				tmp.setName(rs.getString(3));
				classify_list.add(tmp);
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
		return classify_list;
	}
	public List<Classify> find_all_second(){

		ResultSet rs = null;
		List<Classify> classify_list = new ArrayList<Classify>();
		db_conn.ConnectDB();
		try{
			String sql= "select * from classify where parent_id!="+0;
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				Classify tmp = new Classify();
				tmp.setId(rs.getInt(1));
				tmp.setParent_id(rs.getInt(2));
				tmp.setName(rs.getString(3));
				System.out.println(rs.getString(3));
				classify_list.add(tmp);
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
		return classify_list;
	}
	public Classify find_classify_by_id(int id){

		ResultSet rs = null;
		Classify tmp = null;
		db_conn.ConnectDB();
		try{
			String sql= "select * from classify where id="+id;
			rs = db_conn.sm.executeQuery(sql);
			while (rs.next())
			{
				tmp = new Classify();
				tmp.setId(rs.getInt(1));
				tmp.setParent_id(rs.getInt(2));
				tmp.setName(rs.getString(3));
				
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
	public static void main(String []args){
		ClassifyDao dao = new ClassifyDao();
		dao.find_all_classify();
		//userDao.find_all_restaurant();
		//userDao.login("du", "323");
		
	}

}
