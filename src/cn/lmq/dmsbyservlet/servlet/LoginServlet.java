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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		// System.out.println(account+" "+password);
		if (account == null || "".equals(account.trim())) {
			return;
		}
		if (password == null || "".equals(password.trim())) {
			return;
		}
		UserDAO dao = new UserDAO();
		String url = "/user/user.jsp";
		try {

			User user = dao.get(account, password);

			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				// System.out.println(session.getId());
				if (user.getAssessor()) {
					// System.out.println("assessor.jsp");
					url = "assessor/assessor.jsp";
				}
				request.getRequestDispatcher(url).forward(request, response);
			} else {
				// //重定向
				String message = "帐号或密码错误";
				response.setStatus(401);
				request.setAttribute("message", message);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			// System.out.println(url);

		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
