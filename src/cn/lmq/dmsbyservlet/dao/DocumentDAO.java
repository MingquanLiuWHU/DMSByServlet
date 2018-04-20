package cn.lmq.dmsbyservlet.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import cn.lmq.dmsbyservlet.bean.Document;
import cn.lmq.dmsbyservlet.util.DBConnection;;

public class DocumentDAO {

	/**
	 * 根据Id获取document所有信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Document get(int id) throws SQLException {
		Connection conn = DBConnection.getConnection();
		Document doc = null;
		String sql = "SELECT title,type,author,content,modifytime,published,submitted,deleted,username"
				+ " FROM document INNER JOIN user ON author=user.id WHERE document.id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			doc = new Document();
			doc.setId(id);
			doc.setTitle(rs.getString("title"));
			doc.setAuthor(rs.getInt("author"));
			doc.setContent(rs.getString("content"));
			doc.setType(rs.getString("type"));
			doc.setModifytime(rs.getTimestamp("modifytime"));
			doc.setSubmitted(rs.getBoolean("submitted"));
			doc.setPublished(rs.getBoolean("published"));
			doc.setDeleted(rs.getBoolean("deleted"));
			doc.setAuthorName(rs.getString("username"));
		}
		rs.close();
		ps.close();
		conn.close();
		return doc;
	}

	/**
	 * 加载列表信息
	 * 
	 * @param author
	 * @param deleted
	 * @return
	 * @throws SQLException
	 */
	public List<Document> getAllByAuthor(int author, boolean deleted) throws SQLException {
		Connection conn = DBConnection.getConnection();
		List<Document> docs = new ArrayList<>();
		Document doc = null;
		String sql = "SELECT document.id,title,type,modifytime,submitted,published,username"
				+ " FROM document INNER JOIN user ON author=user.id WHERE author=? " + "and deleted=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, author);
		ps.setBoolean(2, deleted);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			doc = new Document();
			docs.add(doc);
			doc.setId(rs.getInt("id"));
			doc.setTitle(rs.getString("title"));
			doc.setAuthor(author);
			// 加载列表的时候详情不加载
			// doc.setContent(rs.getString("content"));
			doc.setType(rs.getString("type"));
			doc.setModifytime(rs.getTimestamp("modifytime"));
			doc.setSubmitted(rs.getBoolean("submitted"));
			doc.setPublished(rs.getBoolean("published"));
			doc.setDeleted(deleted);
			doc.setAuthorName(rs.getString("username"));
		}
		rs.close();
		ps.close();
		conn.close();
		return docs;
	}

	/**
	 * 加载所有未标记删除且已通过审核发布的文稿
	 * 
	 * @param reviewed
	 * @return
	 * @throws SQLException
	 */
	public List<Document> getList(boolean published) throws SQLException {
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT document.id,title,type,author,modifytime,username FROM document "
				+ "INNER JOIN user ON author=user.id WHERE published=? ORDER BY modifytime DESC";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setBoolean(1, published);
		ResultSet rs = ps.executeQuery();
		List<Document> docs = new ArrayList<>();
		while (rs.next()) {
			Document doc = new Document();
			docs.add(doc);
			doc.setId(rs.getInt("id"));
			doc.setAuthor(rs.getInt("author"));
			doc.setModifytime(rs.getTimestamp("modifytime"));
			doc.setTitle(rs.getString("title"));
			doc.setType(rs.getString("type"));
			doc.setAuthorName(rs.getString("username"));
		}
		rs.close();
		ps.close();
		conn.close();
		return docs;
	}

	/**
	 * 获取所有已经提交但没有审核发布的文档
	 * @return
	 * @throws SQLException
	 */
	public List<Document> getSubmittedList() throws SQLException {
		Connection conn = DBConnection.getConnection();
		String sql ="SELECT document.id,title,type,author,published,submitted,username FROM document "+
		"INNER JOIN user ON author=user.id WHERE submitted=? AND published=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setBoolean(1, true);
		ps.setBoolean(2, false);
		ResultSet rs = ps.executeQuery();
		List<Document> docs = new ArrayList<>();
		while(rs.next()) {
			Document doc = new Document();
			docs.add(doc);
			doc.setId(rs.getInt("id"));
			doc.setTitle(rs.getString("title"));
			doc.setType(rs.getString("type"));
			doc.setAuthor(rs.getInt("author"));
			doc.setAuthorName(rs.getString("username"));
			doc.setPublished(rs.getBoolean("published"));
			doc.setSubmitted(rs.getBoolean("submitted"));
		}
		rs.close();
		ps.close();
		conn.close();
		return docs;
	}

	/**
	 * 根据doc的id修改非空的字段
	 * 
	 * @param doc
	 * @return
	 * @throws SQLException
	 */
	public Boolean update(Document doc) throws SQLException {
		if (doc.getId() == null) {
			return false;
		}
		return update(doc.getId(), doc.getTitle(), doc.getType(), doc.getContent(), doc.getSubmitted(),
				doc.getPublished(), doc.getDeleted());
	}

	private boolean update(int id, String title, String type, String content, Boolean submitted, Boolean published,
			Boolean deleted) throws SQLException {
		Document doc = get(id);
		if (doc == null) {
			return false;
		}
		if (title != null && !"".equals(title.trim())) {
			doc.setTitle(title);
		}
		if (type != null && !"".equals(type.trim())) {
			doc.setType(type);
		}
		if (content != null && !"".equals(content.trim())) {
			doc.setContent(content);
		}
		if (submitted != null) {
			doc.setSubmitted(submitted);
		}
		if (published != null) {
			doc.setPublished(published);
		}
		if (deleted != null) {
			doc.setDeleted(deleted);
		}
		Connection conn = DBConnection.getConnection();
		String sql = "UPDATE document SET title=?,type=?,content=?,submitted=?,published=?,deleted=? WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, doc.getTitle());
		ps.setString(2, doc.getType());
		ps.setString(3, doc.getContent());
		ps.setBoolean(4, doc.getSubmitted());
		ps.setBoolean(5, doc.getPublished());
		ps.setBoolean(6, doc.getDeleted());
		ps.setInt(7, doc.getId());
		int row = ps.executeUpdate();
		ps.close();
		conn.close();
		return row > 0;
	}

	public Boolean delete(int id) throws SQLException {
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM document WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		int row = ps.executeUpdate();
		ps.close();
		conn.close();
		return row > 0;
	}

	public Boolean delete(Document doc) throws SQLException {
		return delete(doc.getId());
	}

	/**
	 * 插入新文稿
	 * 
	 * @param doc
	 * @return
	 * @throws SQLException
	 */
	public Boolean insert(Document doc) throws SQLException {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO document (title,type,author,content) VALUES(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, doc.getTitle());
		ps.setString(2, doc.getType());
		ps.setInt(3, doc.getAuthor());
		ps.setString(4, doc.getContent());
		int row = ps.executeUpdate();
		doc.setModifytime(new Date());
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			doc.setId(rs.getInt(1));
		}
		rs.close();
		ps.close();
		conn.close();
		return row > 0;
	}

}
