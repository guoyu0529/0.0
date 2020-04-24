package com.one.stu1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CourseDAO;
import DAO.ScoreDAO;
import DAO.UserDAO;
import beans.Course;
import beans.Student;

/**
 * Servlet implementation class AddScore
 */
@WebServlet("/AddScore")
public class AddScore extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=gb2312");
		String studentid=request.getParameter("studentid");//��ȡѧ��
		String studentname=request.getParameter("studentname");
		String courseid=request.getParameter("courseid");//��ȡ�γ̺�
		String coursename=request.getParameter("coursename");
		String score=request.getParameter("score");//��ȡ�ɼ�
		PrintWriter out = response.getWriter();
		try {
			Student student=UserDAO.findstudent(studentid);//����ѧ��
			Course course=CourseDAO.findclasses(courseid);//���ҿγ�
			System.out.print(student.getStudentid());
			if(student.getStudentid()!=null){//���ڸ�ѧ��
				if(course.getCourseid()!=null){//���ڸÿγ�
				 try {
			        	int i=ScoreDAO.addscore(studentid,studentname,courseid,coursename,score);
						if(i!=0){
							out.print("<script>alert('��ӳɹ�');"
									+ "window.location.href='./admin_addscore.jsp'</script>");
						}else{
							out.print("<script>alert('���ʧ��');"
									+ "window.location.href='./admin_addscore.jsp'</script>");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					out.print("<script>alert('�γ̲����ڣ����ʧ��');"
							+ "window.location.href='./admin_addscore.jsp'</script>");
				}
			}else{
				out.print("<script>alert('ѧ�������ڣ����ʧ��');"
						+ "window.location.href='./admin_addscore.jsp'</script>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
