package com.secondhand.servlet;

import java.io.IOException;





import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondhand.dao.UserDao;
import com.secondhand.vo.User;





import net.sf.json.JSONObject;





/**
 * 
 *
 * ��Ӧ Android�ͻ��˷���������
 */
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UserDao userDao = new UserDao();
		// ��ÿͻ����������
		String username = request.getParameter("account");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		System.out.println("����������===============ע��");
		User userVo = new User();
		userVo.setUser_name(username);
		userVo.setUser_password(password);
		userVo.setUser_email(email);
		
		int  u = userDao.add(userVo);
		if(u == 1){
			// ��Ӧ�ͻ������ݣ���¼�ɹ�
			//JSONObject json = JSONObject.fromObject(u);
			//System.out.println(json.toString());
			//out.println(json.toString());
			out.print("success");
		}else{
			// ��Ӧ�ͻ������ݣ���¼ʧ��
			out.print("0");
		}
		out.flush();
		out.close();
	}
	
	/*
	private String build(User u){
		String userMsg = "";
		userMsg+="id="+u.getId();
		userMsg+=";";
		userMsg+="name="+u.getName();
		return userMsg;
	}*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		System.out.println("������===============��½");
	}
	public void init() throws ServletException {
	}
	
	public RegisterServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
