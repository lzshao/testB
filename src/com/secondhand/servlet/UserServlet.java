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
 * ��Ӧ Android�ͻ��˷���������
 */
public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action"); 
		System.out.println("come here :::::::::::::::::::action:"+action);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// ת��Ϊ�ַ���
		UserDao dao = new UserDao();
		if(action.equals("1")){
			String username = request.getParameter("username");
			User user = dao.find_by_user_name(username);
			JSONArray array = new JSONArray();

			JSONObject obj = new JSONObject();
			try {

				obj.put("name", user.getUser_name());
				obj.put("password", user.getUser_password());
				if(user.getUser_email()!=null){
					obj.put("email", user.getUser_email());
				}else{
					obj.put("email", "");
				}
				if(user.getUser_phone()!=null){
					obj.put("phone", user.getUser_phone());
				}else{
					obj.put("phone", "��");
				}
				if(user.getUser_qq()!=null){
					obj.put("qq", user.getUser_qq());
				}else{
					obj.put("qq", "��");
				}
				// System.out.println(info.getTitle());
			} catch (Exception e) {

			}
			array.add(obj);

			// String msg = DBUtil.buildJson(infolist);
			// ���ظ��ͻ���
			out.print(array.toString());
			out.flush();
			out.close();
		}
		else if (action.equals("2")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String qq = request.getParameter("qq");
			User user = new User();
			user.setUser_name(username);
			user.setUser_password(password);
			user.setUser_email(email);
			user.setUser_phone(phone);
			user.setUser_qq(qq);
			dao.update_user_info(user);
		}
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		System.out.println("������===============��Ʒ��Ϣ");
	}
	public void init() throws ServletException {
	}
	
	public UserServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
