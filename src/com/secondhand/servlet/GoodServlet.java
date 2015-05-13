package com.secondhand.servlet;

import java.io.IOException
;






import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondhand.dao.ClassifyDao;
import com.secondhand.dao.CollectionDao;
import com.secondhand.dao.GoodDao;
import com.secondhand.dao.PictureDao;
import com.secondhand.dao.UserDao;
import com.secondhand.vo.Good;
import com.secondhand.vo.Picture;
import com.secondhand.vo.User;




import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 *
 * 响应 Android客户端发来的请求
 */
public class GoodServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action"); 
		System.out.println("come here :::::::::::::::::::action:"+action);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// 转换为字符串
		GoodDao goodDao = new GoodDao();
		Good goodVo = new Good();
		ClassifyDao classifyDao = new ClassifyDao();
		CollectionDao collectionDao = new CollectionDao();
		UserDao userDao = new UserDao();
		User userVo = new User();
		if(action.equals("1")){
			String username = request.getParameter("username"); 
			List<Good> good_list = goodDao.find_good_by_user_name(username);
			JSONArray array = new JSONArray();
			
			for(int i = 0; i < good_list.size(); i++){
				goodVo = good_list.get(i);
				
				JSONObject obj = new JSONObject();
				try{
				
					obj.put("id", goodVo.getId());
					obj.put("name", goodVo.getGood_name());
					obj.put("type", classifyDao.find_classify_by_id(goodVo.getType_id()).getName());
					obj.put("price", String.valueOf(goodVo.getGood_price()));
					obj.put("intro", goodVo.getGood_intro());
				
					obj.put("collected_time", String.valueOf(collectionDao.find_collection_by_good_id(goodVo.getId()).size()));
					obj.put("add_name", goodVo.getAdd_name());
					obj.put("add_date", goodVo.getAdd_date());
					
					userVo = userDao.find_by_user_name(goodVo.getAdd_name());
					if(userVo.getUser_phone()!=null){
						obj.put("user_phone", userVo.getUser_phone());
					}else{
						obj.put("user_phone", "无");
					}
					if(userVo.getUser_qq()!=null){
						obj.put("user_qq", userVo.getUser_qq());
					}else{
						obj.put("user_qq", "无");
					}
					
					PictureDao picDao = new PictureDao();
					List<Picture> pic_list = picDao.find_good_picture(goodVo.getId());
					obj.put("pic_num", String.valueOf(pic_list.size()));
					for(int j=0; j<pic_list.size(); j++){
						Picture tmpPic = pic_list.get(j);
						obj.put("pic_"+String.valueOf(j), tmpPic.getPic_path());
					}
					//System.out.println(info.getTitle());
				}catch(Exception e){
					
				}
				array.add(obj);
			}
			//String msg = DBUtil.buildJson(infolist);
			// 返回给客户端
			out.print(array.toString());
			out.flush();
			out.close();
		}
		else if(action.equals("2")){
			int type_id = Integer.parseInt(request.getParameter("type_id")); 
			List<Good> good_list = goodDao.find_good_by_classfity(type_id);
			System.out.println("$$$$$$$$$$$"+type_id);
			JSONArray array = new JSONArray();
			
			for(int i = 0; i < good_list.size(); i++){
				goodVo = good_list.get(i);
				
				JSONObject obj = new JSONObject();
				try{
				
					obj.put("id", goodVo.getId());
					
					obj.put("name", goodVo.getGood_name());
					obj.put("type", classifyDao.find_classify_by_id(goodVo.getType_id()).getName());
					obj.put("price", String.valueOf(goodVo.getGood_price()));
					obj.put("intro", goodVo.getGood_intro());
					
					obj.put("collected_time", String.valueOf(collectionDao.find_collection_by_good_id(goodVo.getId()).size()));
					obj.put("add_name", goodVo.getAdd_name());
					obj.put("add_date", goodVo.getAdd_date());
					
					userVo = userDao.find_by_user_name(goodVo.getAdd_name());
					if(userVo.getUser_phone()!=null){
						obj.put("user_phone", userVo.getUser_phone());
					}else{
						obj.put("user_phone", "无");
					}
					if(userVo.getUser_qq()!=null){
						obj.put("user_qq", userVo.getUser_qq());
					}else{
						obj.put("user_qq", "无");
					}
					
					PictureDao picDao = new PictureDao();
					List<Picture> pic_list = picDao.find_good_picture(goodVo.getId());
					obj.put("pic_num", String.valueOf(pic_list.size()));
					for(int j=0; j<pic_list.size(); j++){
						Picture tmpPic = pic_list.get(j);
						obj.put("pic_"+String.valueOf(j), tmpPic.getPic_path());
						
					}
					System.out.println("good——name:" + goodVo.getGood_name());
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				array.add(obj);
			}
			//String msg = DBUtil.buildJson(infolist);
			// 返回给客户端
			out.print(array.toString());
			out.flush();
			out.close();
		}
		else if(action.equals("3")){
			String keyword = request.getParameter("keyword");
			List<Good> good_list = goodDao.find_good_by_keyword(keyword);
			System.out.println("action3:"+good_list.size());
			JSONArray array = new JSONArray();
			
			for(int i = 0; i < good_list.size(); i++){
				goodVo = good_list.get(i);
				
				
				JSONObject obj = new JSONObject();
				try{
				
					obj.put("id", goodVo.getId());
					obj.put("name", goodVo.getGood_name());
					obj.put("type", classifyDao.find_classify_by_id(goodVo.getType_id()).getName());
					obj.put("price", String.valueOf(goodVo.getGood_price()));
					obj.put("intro", goodVo.getGood_intro());
					obj.put("state", String.valueOf(goodVo.getGood_state()));
					obj.put("collected_time", String.valueOf(collectionDao.find_collection_by_good_id(goodVo.getId()).size()));
					obj.put("add_name", goodVo.getAdd_name());
					obj.put("add_date", goodVo.getAdd_date());
					userVo = userDao.find_by_user_name(goodVo.getAdd_name());
					if(userVo.getUser_phone()!=null){
						obj.put("user_phone", userVo.getUser_phone());
					}else{
						obj.put("user_phone", "无");
					}
					if(userVo.getUser_qq()!=null){
						obj.put("user_qq", userVo.getUser_qq());
					}else{
						obj.put("user_qq", "无");
					}
					
					PictureDao picDao = new PictureDao();
					List<Picture> pic_list = picDao.find_good_picture(goodVo.getId());
					obj.put("pic_num", String.valueOf(pic_list.size()));
					for(int j=0; j<pic_list.size(); j++){
						Picture tmpPic = pic_list.get(j);
						obj.put("pic_"+String.valueOf(j), tmpPic.getPic_path());
					}
					System.out.println(userVo.getUser_phone());
				}catch(Exception e){
					
				}
				array.add(obj);
			}
			//String msg = DBUtil.buildJson(infolist);
			// 返回给客户端
			out.print(array.toString());
			out.flush();
			out.close();
		}
		else if(action.equals("4")){
			int good_id = Integer.parseInt(request.getParameter("good_id"));
			goodDao.delete(good_id);
		}
		else if(action.equals("5")){
			System.out.println("action 5");
			String good_name = request.getParameter("name");
			String good_price = request.getParameter("price");
			String good_intro = request.getParameter("intro");
			String add_name = request.getParameter("add_name");
			String type_id = request.getParameter("type_id");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
		    String add_date = sdf.format(date);
		    
			Good good = new Good();
			
			good.setGood_name(good_name);
			good.setGood_price(Float.parseFloat(good_price));
			good.setGood_intro(good_intro);
			good.setAdd_date(add_date);
			good.setAdd_name(add_name);
			good.setType_id(Integer.parseInt(type_id));
			good.setGood_state(0);
			
			int id = goodDao.add(good);
			System.out.println("action 5:"+id);
			out.print(String.valueOf(id));
			out.flush();
			out.close();
		}
		else if(action.equals("6")){
			int good_id = Integer.parseInt(request.getParameter("good_id"));
			goodVo = goodDao.find_by_good_id(good_id);
			
			JSONArray array = new JSONArray();

			JSONObject obj = new JSONObject();
			try {

				obj.put("id", goodVo.getId());
				obj.put("name", goodVo.getGood_name());
				obj.put("type", classifyDao.find_classify_by_id(goodVo.getType_id())
						.getName());
				obj.put("price", String.valueOf(goodVo.getGood_price()));
				obj.put("intro", goodVo.getGood_intro());
				obj.put("state", String.valueOf(goodVo.getGood_state()));
				obj.put("collected_time", String.valueOf(collectionDao
						.find_collection_by_good_id(goodVo.getId()).size()));
				obj.put("add_name", goodVo.getAdd_name());
				obj.put("add_date", goodVo.getAdd_date());
				userVo = userDao.find_by_user_name(goodVo.getAdd_name());
			
				if(userVo.getUser_phone()!=null){
					obj.put("user_phone", userVo.getUser_phone());
				}else{
					obj.put("user_phone", "无");
				}
				if(userVo.getUser_qq()!=null){
					obj.put("user_qq", userVo.getUser_qq());
				}else{
					obj.put("user_qq", "无");
				}
				
				PictureDao picDao = new PictureDao();
				List<Picture> pic_list = picDao.find_good_picture(goodVo
						.getId());
				obj.put("pic_num", String.valueOf(pic_list.size()));
				for (int j = 0; j < pic_list.size(); j++) {
					Picture tmpPic = pic_list.get(j);
					obj.put("pic_" + String.valueOf(j), tmpPic.getPic_path());
				}
				// System.out.println(info.getTitle());
			} catch (Exception e) {

			}
			array.add(obj);

			// String msg = DBUtil.buildJson(infolist);
			// 返回给客户端
			out.print(array.toString());
			out.flush();
			out.close();
			
		}
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		System.out.println("！！！===============商品信息");
	}
	public void init() throws ServletException {
	}
	
	public GoodServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
