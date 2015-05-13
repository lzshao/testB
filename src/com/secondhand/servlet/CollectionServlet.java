package com.secondhand.servlet;

import java.io.IOException
;






import java.io.PrintWriter;
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
import com.secondhand.vo.Collection;
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
public class CollectionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action"); 
		System.out.println("come here :::::::::::::::::::");
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
			List<Collection> collection_list = collectionDao.find_collection_by_user_name(username);
			
			JSONArray array = new JSONArray();
			
			for(int i = 0; i < collection_list.size(); i++){
				Collection tmpVo = collection_list.get(i);
				goodVo = goodDao.find_by_good_id(tmpVo.getGood_id());
				JSONObject obj = new JSONObject();
				try{
					obj.put("collection_id", tmpVo.getId());
					obj.put("good_id", goodVo.getId());
					
					obj.put("name", goodVo.getGood_name());
					obj.put("type", classifyDao.find_classify_by_id(goodVo.getType_id()).getName());
					obj.put("price", String.valueOf(goodVo.getGood_price()));
					obj.put("intro", goodVo.getGood_intro());
					
					obj.put("collected_time", String.valueOf(collectionDao.find_collection_by_good_id(goodVo.getId()).size()));
					obj.put("add_name", goodVo.getAdd_name());
					obj.put("add_date", goodVo.getAdd_date());
					
					userVo = userDao.find_by_user_name(goodVo.getAdd_name());
					obj.put("user_qq", userVo.getUser_qq());
					obj.put("user_phone", userVo.getUser_phone());
					
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
			int id = Integer.parseInt(request.getParameter("id")); 
			System.out.println("id:"+id);
			collectionDao.delete(id);
			
		}
		else if(action.equals("3")){
			int good_id = Integer.parseInt(request.getParameter("good_id"));
			String user_name = request.getParameter("username");
			Collection tmpVo = new Collection();
			tmpVo.setGood_id(good_id);
			tmpVo.setUser_name(user_name);
			collectionDao.add(tmpVo);
		}
		
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		System.out.println("！！！===============商品信息");
	}
	public void init() throws ServletException {
	}
	
	public CollectionServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
