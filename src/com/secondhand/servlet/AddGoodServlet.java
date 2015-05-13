package com.secondhand.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.secondhand.dao.PictureDao;
import com.secondhand.vo.Picture;


/**
 * 
 *
 * ��Ӧ Android�ͻ��˷���������
 */
public class AddGoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String good_id  = null;
		
		String file_save_name = null;
		String file_save_path = null;
		int pic_num = 0;
		System.out.println("dd"+request.toString());
		
		try {
			// 1. �жϱ��Ƿ��ǻ�ϱ�
			boolean tag = ServletFileUpload.isMultipartContent(request);
			// 2. �ж�
			if (tag) {

				// 3. ����Ŀ¼
				file_save_path = this.getServletContext().getRealPath("upload");
				System.out.println("path = " + file_save_path);
				File dir = new File(file_save_path);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				// 4. ����ServletFileUpload����
				FileItemFactory factory  = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);

				// 5. �������󣬽�ÿһ��������з�װFileItem
				List<FileItem> list = upload.parseRequest(request);

				// 6. ѭ������
				for (FileItem item : list) {
					// 7. �ж�
					if (item.isFormField()) {
						// ��ͨ�ֶ�
						String name = item.getFieldName();
						String value = item.getString("utf-8");
						System.out.println(name + "=" + value);
						if(name.equals("good_id")){
							good_id = value;
						}
					} else {
						// �ļ��ֶ�
						String fileName = item.getName();
						file_save_name = System.currentTimeMillis()
											+ "_" + fileName;
						System.out.println(file_save_name);
						
						
						item.write(new File(dir, System.currentTimeMillis()
								+ "_" + fileName));
					}
				}
			} else {
				System.out.println("��ͨ�����ݴ���...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(good_id);
		// ת��Ϊ�ַ���
		PictureDao picDao = new PictureDao();
		Picture pic = new Picture();
		pic.setGood_id(Integer.parseInt(good_id));
		pic.setPic_path(file_save_name);
		
		picDao.add(pic);
	
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
		System.out.println("������===============��½");
	}
	public void init() throws ServletException {
	}
	
	public AddGoodServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
