package cn.lmq.dmsbyservlet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lmq.dmsbyservlet.bean.Document;
import cn.lmq.dmsbyservlet.dao.DocumentDAO;

/**
 * 获取所有提交的文档
 */
@WebServlet("/assessor/docsSubmitted")
public class DocsSubmittedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DocumentDAO dao = new DocumentDAO();
		try {
			List<Document> docs = dao.getSubmittedList();
			request.setAttribute("docList", docs);
			//获取列表后跳转到公共目录下文档列表视图
			String url = "/common/docList.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		catch(SQLException e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
