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
 * 审核文档
 */
@WebServlet("/assessor/assessDoc")
public class AssessDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		String id = request.getParameter("id");// 文档的id
		String published = request.getParameter("published");
		Boolean isPublished = Boolean.valueOf(published);// 是否通过审核
		if (id != null) {
			try {
				DocumentDAO dao = new DocumentDAO();
				Document doc = new Document();
				doc.setId(Integer.valueOf(id));
//				System.out.println(isPublished);
				doc.setPublished(isPublished);
				if (!isPublished) {
					// 未通过审核，退回未提交的状态
					doc.setSubmitted(false);
				}
				dao.update(doc);
				List<Document> docs = dao.getSubmittedList();
				request.setAttribute("docList", docs);
				//获取列表后跳转到公共目录下文档列表视图
				String url = "/common/docList.jsp";
				request.getRequestDispatcher(url).forward(request, response);
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
		doGet(request, response);
	}

}
