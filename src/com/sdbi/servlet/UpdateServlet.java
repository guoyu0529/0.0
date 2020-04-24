package com.sdbi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet{
		//�������ӣ�Ԥ����
		private DataSource dataSource = null;
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8"); 
	
	// �����ַ��� UTF-8
	 int id = Integer.parseInt(request.getParameter("id"));
	 String newPassword = request.getParameter("newPassword");
	 System.out.println(id + ":" + newPassword);
	
	 try {//�õ�����Դ
	dataSource = new ComboPooledDataSource();
	conn = dataSource.getConnection();//���Ӷ���--�����ӳصõ�����
	
	
	String sql = "UPDATE users SET password = ? WHERE id = ?";
	pstmt = conn.prepareStatement(sql);//Ԥ����SQl���
	//��Ӳ���
	pstmt.setString(1, newPassword);
	pstmt.setInt(2, id);
	int row = pstmt.executeUpdate();//ִ����䣬���з���ֵ--��Ӱ�������
	
	if (row > 0) {	//���³ɹ�
		response.setContentType("text/html;charset=utf-8");//�����ʽΪutf-8
		response.getWriter().print("<h2>�ɹ��޸���" + row + "�����ݣ�</h2>");//����Ӧ�г������ģ���ֹ�������룬Ӧ����������ݣ�
		response.getWriter().print("<a href='adm_menu.jsp'>���ع���Աҳ��</a>");
	}
	// �ر�
	if (pstmt != null) pstmt.close();
	if (conn != null) conn.close();
		} catch (Exception e) {
		e.printStackTrace();
	} finally {
	try {
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
		}
	}
}
	@Override
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	}

