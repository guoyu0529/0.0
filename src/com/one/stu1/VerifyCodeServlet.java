package com.one.stu1;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
		protected void doGet(HttpServletRequest request,
						HttpServletResponse response) throws ServletException, IOException {
			response.setHeader("pragma","no-cache");
			response.setHeader("cache-control","no-cache");
			response.setHeader("expires","0");
		response.setContentType("text/jpeg"); // ������Ӧ������
		VerifyCode code = new VerifyCode();
		BufferedImage image = code.createImage(); // ������֤��ͼƬ
		String text = code.getText(); // ��ȡ��֤���ı�
		//System.out.println("text = " + text);
		// ����֤���ı����浽 session ��
		request.getSession().setAttribute("sessionVerifyCode", text);
		VerifyCode.output(image, response.getOutputStream()); // ����֤��ͼƬ�����Ӧ��
		}

protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
