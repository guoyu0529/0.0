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
@WebServlet("/AddServlet") 
public class AddServlet extends HttpServlet{
		private DataSource dataSource = null;
		private Connection conn = null;
		private PreparedStatement pstmt = null;
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
	try {
		dataSource = new ComboPooledDataSource();
		conn = dataSource.getConnection();
		String sql = "INSERT INTO users VALUES (?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
	 
		request.setCharacterEncoding("UTF-8"); // �����ַ��� UTF-8
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	int sex = Integer.parseInt(request.getParameter("sex"));
	String email = request.getParameter("email");
	String birthday = request.getParameter("birthday");
	
	System.out.println(name + "," + password + "," + sex + "," + email +"," + birthday);
	
	pstmt.setString(1, name);
	pstmt.setString(2, password);
	pstmt.setInt(3, sex);
	pstmt.setString(4, email);
	pstmt.setString(5, birthday);
	int row = pstmt.executeUpdate();
	
	if (row > 0) {
	 response.setContentType("text/html;charset=utf-8");
	response.getWriter().print("<h2>�ɹ������" + row + "�����ݣ�</h2>");
	response.getWriter().print("<a href='adm_menu.jsp'>���ع���ҳ��</a>");
	
	}
	// �ر�
	if (pstmt != null)
		pstmt.close();
	if (conn != null)
		conn.close();
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

