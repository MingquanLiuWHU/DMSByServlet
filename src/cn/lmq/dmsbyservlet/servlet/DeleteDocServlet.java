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
 * 永久删除一个文档
 */
@WebServlet("/user/deleteDoc")
public class DeleteDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			DocumentDAO dao = new DocumentDAO();
			// 过滤器保证user不空
			HttpSession session = request.getSession();
//			System.out.println(session.getId());
			User user = (User) session.getAttribute("user");
			try {
				dao.delete(Integer.parseInt(id));
				List<Document> docs = dao.getAllByAuthor(user.getId(), true);
				request.setAttribute("docList", docs);
				request.getRequestDispatcher("/user/recyleBin.jsp").forward(request, response);
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
