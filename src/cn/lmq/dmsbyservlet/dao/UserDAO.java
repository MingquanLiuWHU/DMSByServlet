package cn.lmq.dmsbyservlet.dao;

import java.sql.*;

import cn.lmq.dmsbyservlet.bean.User;
import cn.lmq.dmsbyservlet.util.*;

public class UserDAO {

	/**
	 * 根据id获取User
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public User get(int id) throws SQLException {
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT account,password,username,assessor FROM user WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		User user = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user = new User();
			user.setId(id);
			user.setAccount(rs.getString("account"));
			user.setPassword(rs.getString("password"));
			user.setUsername(rs.getString("username"));
			user.setAssessor(rs.getBoolean("assessor"));
		}
		rs.close();
		ps.close();
		conn.close();
		return user;
	}

	/**
	 * 根据帐号密码获取User信息
	 * 
	 * @param account
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public User get(String account, String password) throws SQLException {
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT id,account,password,username,assessor FROM user" + " WHERE account=? AND password=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, account);
		ps.setString(2, password);
		User user = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setAccount(rs.getString("account"));
			user.setPassword(rs.getString("password"));
			user.setUsername(rs.getString("username"));
			user.setAssessor(rs.getBoolean("assessor"));
		}
		rs.close();
		ps.close();
		conn.close();
		return user;
	}

	/**
	 * 根据id修改非空的字段
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public boolean update(int id, String account, String password, String username, Boolean assessor)
			throws SQLException {
		User user = get(id);
		if (user == null) {
			return false;
		}

		if (account != null && !"".equals(account.trim())) {
			user.setAccount(account);
		}

		if (password != null && !"".equals(password.trim())) {
			user.setPassword(password);
		}

		if (username != null && !"".equals(username.trim())) {
			user.setUsername(username);
		}

		if (assessor != null) {
			user.setAssessor(assessor);
		}

		Connection conn = DBConnection.getConnection();
		String sql = "UPDATE user SET account=?,password=?,username=?,assessor=? WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getAccount());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getUsername());
		ps.setBoolean(4, user.getAssessor());
		ps.setInt(5, id);
		int row = ps.executeUpdate();
		ps.close();
		conn.close();
		return row > 0;
	}

	public boolean update(User user) throws SQLException {
		return update(user.getId(), user.getAccount(), user.getPassword(), user.getUsername(), user.getAssessor());
	}

	/**
	 * 根据id删除user，不存在返回false
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean delete(int id) throws SQLException {
		User user = get(id);
		if (user == null) {
			return false;
		}
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM user WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		int row = ps.executeUpdate();
		ps.close();
		conn.close();
		return row > 0;
	}

	public boolean insert(User user) throws SQLException {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT user(account,password,username,assessor) VALUES(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getAccount());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getUsername());
		ps.setBoolean(4, user.getAssessor());
		int row = ps.executeUpdate();
		ps.close();
		conn.close();
		return row > 0;
	}

}
