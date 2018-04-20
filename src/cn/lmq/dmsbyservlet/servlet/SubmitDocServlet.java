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
import cn.lmq.dmsbyservlet.bean.User;
import cn.lmq.dmsbyservlet.dao.DocumentDAO;

/**
 * 用户提交一个文档
 */
@WebServlet("/user/submitDoc")
public class SubmitDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = (User) request.getSession().getAttribute("user");
		if (id != null) {
			try {
				DocumentDAO dao = new DocumentDAO();
				Document doc = new Document();
				//设置要更新的字段
				doc.setId(Integer.valueOf(id));
				doc.setSubmitted(true);
				dao.update(doc);
				//查询文档列表返回文档页面
				List<Document> docs = dao.getAllByAuthor(user.getId(), false);
				request.setAttribute("docList", docs);
				request.getRequestDispatcher("/common/docList.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
