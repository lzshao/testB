package com.secondhand.servlet;

import java.io.IOException;







import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondhand.dao.ClassifyDao;
import com.secondhand.vo.Classify;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;





/**
 * 
 *
 * 响应 Android客户端发来的请求
 */
public class ClassfiyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action"); 
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// 转换为字符串
		ClassifyDao classifyDao = new ClassifyDao();
		
		Classify tmpVo = new Classify();
		
		System.out.println("11111");
		if(action.equals("1")){
			List<Classify> classify_list = classifyDao.find_all_first();
			JSONArray array = new JSONArray();
			for(int i = 0; i < classify_list.size(); i++){
				tmpVo = classify_list.get(i);

				JSONObject obj = new JSONObject();
				try{
		
					obj.put("id", String.valueOf(tmpVo.getId()));
					obj.put("name", tmpVo.getName());
				
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
			int first_id = Integer.parseInt(request.getParameter("first_id"));
			List<Classify> classify_list = classifyDao.find_second_by_classify_id(first_id);
			JSONArray array = new JSONArray();
			for(int i = 0; i < classify_list.size(); i++){
				tmpVo = classify_list.get(i);

				JSONObject obj = new JSONObject();
				try{
		
					obj.put("id", String.valueOf(tmpVo.getId()));
					obj.put("name", tmpVo.getName());
				
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
		else if(action.equals("3")){
			
			List<Classify> classify_list = classifyDao.find_all_second();
			JSONArray array = new JSONArray();
			for(int i = 0; i < classify_list.size(); i++){
				tmpVo = classify_list.get(i);

				JSONObject obj = new JSONObject();
				try{
		
					obj.put("id", String.valueOf(tmpVo.getId()));
					obj.put("name", tmpVo.getName());
				
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
		
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		System.out.println("！！！===============登陆");
	}
	public void init() throws ServletException {
	}
	
	public ClassfiyServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
