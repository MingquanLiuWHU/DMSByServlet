package cn.lmq.dmsbyservlet.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lmq.dmsbyservlet.bean.Document;
import cn.lmq.dmsbyservlet.dao.DocumentDAO;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/common/showContent")
public class ShowContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id!=null&& !"".equals(id)) {
			DocumentDAO dao = new DocumentDAO();
			try {
				//查找一个document
				Document doc = dao.get(Integer.parseInt(id));
				//放入request参数
				request.setAttribute("doc", doc);
				//跳转到显示页面
				request.getRequestDispatcher("/common/docContent.jsp").forward(request, response);
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
