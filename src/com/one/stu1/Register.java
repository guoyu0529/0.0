package com.one.stu1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import beans.Student;

@WebServlet("/Register")
public class Register extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");//��ȡ�˺�
		String password1 = request.getParameter("password1");//��ȡ��һ�����������
		String username = request.getParameter("username");//��ȡ����
		String password2 = request.getParameter("password2");//��ȡ�ڶ������������
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		System.out.print(userid);
		System.out.print(username);
		System.out.print(password1);
		System.out.print(password2);
		if(userid!=null&&username!=null&&password1!=null&&password2!=null){//ҳ����Ϣ�Ƿ���д����
			if(password1.equals(password2)){//�������������һ��
				try {
					Student student = UserDAO.findstudent(userid);
					if(student.getStudentid()!=null){//�˺Ŵ���
						out.print("<script>alert('���˺Ŵ��ڣ�ע��ʧ��!');"
								+ "window.location.href='./register.jsp'</script>");
					}else{
						student.setStudentid(userid);
						student.setStudentname(username);
						student.setPassword(password1);
						boolean flag=UserDAO.addStudent(student);
						if(flag){
							out.print("<script>alert('ע��ɹ�!����ȥ��¼');"
									+ "window.location.href='./index.jsp'</script>");
						}else{
							out.print("<script>alert('ע��ʧ��!�Ժ�����');"
									+ "window.location.href='./register.jsp'</script>");
						}
					}
				} catch (Exception e) {
			
					e.printStackTrace();
				}
			}else{
				out.print("<script>alert('�������벻һ�£���ȷ��');"
						+ "window.location.href='./register.jsp'</script>");
		}
		}else{
			out.print("<script>alert('�뽫��Ϣ��д����');"
					+ "window.location.href='./register.jsp'</script>");
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
