package cn.lmq.dmsbyservlet.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.lmq.dmsbyservlet.bean.User;
import cn.lmq.dmsbyservlet.dao.UserDAO;

/**
 * Servlet implementation class ModifyServlet
 */
@WebServlet("/common/modifyInfo")
public class ModifyInfoServlet extends HttpServlet {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username==null||"".equals(username.trim())) {
			return;
		}
		if(password==null||"".equals(password.trim())) {
			return;
		}
		UserDAO dao = new UserDAO();
		try {
			HttpSession session = request.getSession();
			System.out.println(session.getId());
			User user = (User) session.getAttribute("user");
			user.setUsername(username);
			user.setPassword(password);
			if(dao.update(user)) {
				session.setAttribute("user", user);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/common/info.jsp").forward(request, response);
	}

}
