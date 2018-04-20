package cn.lmq.dmsbyservlet.bean;

public class User {
	private Integer id;
	private String account;
	private String password;
	private String username;
	private Boolean assessor;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public void setAssessor(Boolean assessor) {
		this.assessor = assessor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getAssessor() {
		return assessor;
	}
	
	
}
