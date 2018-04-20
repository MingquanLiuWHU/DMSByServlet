package cn.lmq.dmsbyservlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.lmq.dmsbyservlet.bean.User;
import cn.lmq.dmsbyservlet.util.AuthorityUtil;

/**
 * 检测是否登录
 */

public class LoginFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		// System.out.println(session.getId());
//		System.out.println(httpRequest.getRequestURI());
		User user = (User) session.getAttribute("user");
		// 没有登录或者没有权限
		if (user == null || !AuthorityUtil.hasAuthority(user.getAssessor(), httpRequest.getRequestURI())) {

			HttpServletResponse httpResponse = (HttpServletResponse) response;
			String message = "你没有登录或者你没有权限";
			httpResponse.setStatus(401);
			httpRequest.setAttribute("message", message);
			httpRequest.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		// 应该补上user对相应url权限的检查
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
