package cn.lmq.dmsbyservlet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lmq.dmsbyservlet.bean.Document;
import cn.lmq.dmsbyservlet.dao.DocumentDAO;

/**
 * 查询所有已经公开发布的文档列表
 */
@WebServlet("/user/docsPublished")
public class DocsPublishedServlet extends HttpServlet {
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
		
		//System.out.println("docsPublished");
		DocumentDAO dao = new DocumentDAO();
		List<Document> docs = new ArrayList<>();
		try {
			docs = dao.getList(true);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("docList", docs);
		String url = "/user/readArea.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

}
