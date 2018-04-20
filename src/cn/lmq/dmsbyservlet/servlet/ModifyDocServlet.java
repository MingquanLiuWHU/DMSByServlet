package cn.lmq.dmsbyservlet.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.lmq.dmsbyservlet.bean.Document;
import cn.lmq.dmsbyservlet.bean.User;
import cn.lmq.dmsbyservlet.dao.DocumentDAO;

/**
 * Servlet implementation class ModifyDocServlet
 */
@WebServlet("/ModifyDocServlet")
public class ModifyDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
//		System.out.println(session.getId());
		User user = (User) session.getAttribute("user");

		Document doc = new Document();
		String id = request.getParameter("id");
		doc.setId(Integer.valueOf(id));
		
		doc.setTitle(request.getParameter("title"));
		doc.setType(request.getParameter("type"));
		doc.setContent(request.getParameter("content"));
		DocumentDAO dao = new DocumentDAO();
		try {
			dao.update(doc);
			List<Document> docs = dao.getAllByAuthor(user.getId(), false);
			request.setAttribute("docList", docs);
			String url = "/common/docList.jsp";
			request.getRequestDispatcher(url).forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
