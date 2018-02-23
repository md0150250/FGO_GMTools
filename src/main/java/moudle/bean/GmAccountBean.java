package moudle.bean;

import java.io.Serializable;

public class GmAccountBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String gmEmail;
	private String password;
	
	
	public GmAccountBean() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getGmEmail() {
		return gmEmail;
	}


	public void setGmEmail(String gmEmail) {
		this.gmEmail = gmEmail;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "GmAccountBean [id=" + id + ", gmEmail=" + gmEmail + ", password=" + password + "]";
	}
	
	
	
}
