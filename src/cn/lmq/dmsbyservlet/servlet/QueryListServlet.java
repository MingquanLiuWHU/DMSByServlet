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
 * 查询用户拥有的所有文档列表，根据deleted参数返回是否删除的列表
 */
@WebServlet("/user/queryList")
public class QueryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DocumentDAO dao = new DocumentDAO();
		//在过滤器过滤保证user不为空
		HttpSession session = request.getSession();
//		System.out.println(session.getId());
		User user = (User)session.getAttribute("user");
		
		//获取deleted参数
		String deleted = request.getParameter("deleted");
		Boolean isDeleted = Boolean.valueOf(deleted);
		List<Document> docs = null;
		try {
			//仅当deleted不空且为字符串等于'true'时返回true
			docs = dao.getAllByAuthor(user.getId(), isDeleted);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("docList", docs);
		String url = isDeleted?"/user/recyleBin.jsp":"/common/docList.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
