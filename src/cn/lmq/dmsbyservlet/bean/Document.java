package cn.lmq.dmsbyservlet.bean;

import java.util.Date;

public class Document {
	private Integer id;
	private String title;
	private String type;
	private Integer author;
	private String authorName;
	private String content;
	private Date modifytime;
	private Boolean submitted;
	private Boolean published;
	private Boolean deleted;

	
	public Document() {}
	public Document(int id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	public Boolean getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Boolean submitted) {
		this.submitted = submitted;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}
	
	public Integer getAuthor() {
		return author;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
}
