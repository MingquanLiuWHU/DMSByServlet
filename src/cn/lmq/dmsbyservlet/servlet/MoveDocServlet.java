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
 * 将一个文档移入或者移出回收站，当存在deleted为true的参数时视为移入回收站
 */
@WebServlet("/user/moveDoc")
public class MoveDocServlet extends HttpServlet {
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
//		System.out.println("删除恢复方法");
		String id = request.getParameter("id");
		String deleted = request.getParameter("deleted");
		Boolean isDeleted = Boolean.valueOf(deleted);
		System.out.println(isDeleted);
		if (id != null) {
			DocumentDAO dao = new DocumentDAO();
			// 过滤器保证user不空
			User user = (User) request.getSession().getAttribute("user");
			try {
				Document doc = new Document();
				doc.setId(Integer.parseInt(id));
				//
				doc.setDeleted(isDeleted);
				// 如果移入回收站，重置提交和发布的状态
				if (isDeleted) {
					doc.setSubmitted(false);
					doc.setPublished(false);
				}
				dao.update(doc);
				List<Document> docs = dao.getAllByAuthor(user.getId(), !isDeleted);
				request.setAttribute("docList", docs);
				String url = !isDeleted ? "/user/recyleBin.jsp" : "/common/docList.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
