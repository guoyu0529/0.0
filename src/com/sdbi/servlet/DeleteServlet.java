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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private DataSource dataSource = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int id = Integer.parseInt(request.getParameter("id"));
		 System.out.println("id = " + id);
		try {
			dataSource = new ComboPooledDataSource();
			conn = dataSource.getConnection();
			String sql = "DELETE FROM users WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int row = pstmt.executeUpdate();
		if (row > 0) {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print("<h2>�ɹ�ɾ����" + row + "�����ݣ�</h2>");
			response.getWriter().print("<a href='adm_menu.jsp'>���ع���ҳ��</a>");
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
protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
}
}
