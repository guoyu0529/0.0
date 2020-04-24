package com.one.stu1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import beans.Admin;
import beans.Student;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
						doPost(request, response); // һ����Ҫ����
								}
	protected void doPost(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=gb2312");
				String userid = request.getParameter("userid");//��ȡ�˺�
				String password = request.getParameter("password");//��ȡ����
				String user=request.getParameter("user");//�ж��û���ݣ�ѧ�����ǹ���Ա
				String verifyCode = request.getParameter("verify_code");//��ȡ��֤��
				String sessionVerifyCode = (String) request.getSession().getAttribute("sessionVerifyCode");
				PrintWriter out = response.getWriter();
				
				//�ж���֤���Ƿ���ȷ
				if(verifyCode.equals(sessionVerifyCode)){
			
				if(user==null||password==null){//δ��������
					//System.out.println("��¼ʧ�ܣ�");
					out.print("<script>alert('��¼ʧ�ܣ��������˺ź�����');"+ "window.location.href='./index.jsp'</script>");
				}else{
					if(user.equals("student")){//ѧ����¼ʱ
						try {
							Student student=UserDAO.findstudent(userid);
							if(student.getPassword().equals(password)){
								HttpSession session = request.getSession();
								session.setAttribute("student",student);//�����û���
								
								//System.out.println("��¼�ɹ���");
								response.sendRedirect("./welcome.jsp");
							}else {
								System.out.println("��¼ʧ�ܣ�");
								out.print("<script>alert('��¼ʧ�ܣ���ȷ���˺ź�����');"
										+ "window.location.href='./index.jsp'</script>");
							}
						   } catch (Exception e) {
							e.printStackTrace();}
					}else{//����Ա��½ʱ
						try {
							Admin admin=UserDAO.findadmin(userid);
							if(admin.getPassword().equals(password)){
								HttpSession session = request.getSession();
								session.setAttribute("admin",admin);	//�����û���
								
								//System.out.println("��¼�ɹ���");
								response.sendRedirect("./adm_menu.jsp");
							}else{	
									//System.out.println("��¼ʧ�ܣ�");
								out.print("<script>alert('��¼ʧ�ܣ���ȷ���˺ź�����');"
										+ "window.location.href='./index.jsp'</script>");}
							} catch (Exception e) {
							e.printStackTrace();}
					}
				}
				
				}else{
					out.print("<script>alert('��¼ʧ�ܣ���������ȷ����֤��');"
							+ "window.location.href='./index.jsp'</script>");}
	}
				
}


//				if ("admin".equalsIgnoreCase(userid)&& "123".equalsIgnoreCase(password)&& sessionVerifyCode.equalsIgnoreCase(verifyCode)) {
//		// ��¼�ɹ�
//		HttpSession session = request.getSession();
//		session.setAttribute("username", userid); // �� HttpSession ���б����û���
//		response.sendRedirect("./welcome.jsp");
//				} else if (sessionVerifyCode.equalsIgnoreCase(verifyCode)) {
//					// ��¼ʧ��
//					response.getWriter().print("�û������������");
//					} else {
//					response.getWriter().print("��֤�����");
//					}
				

				
