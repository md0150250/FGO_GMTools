package moudle.bean;

import java.io.Serializable;

public class ReserveBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int userID;
	private String cellphone;
	private String day;
	private int gold;
	
		
	
	public ReserveBean() {
		super();
	}



	public int getUserID() {
		return userID;
	}




	public void setUserID(int userID) {
		this.userID = userID;
	}




	public String getCellphone() {
		return cellphone;
	}




	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}




	public String getDay() {
		return day;
	}




	public void setDay(String day) {
		this.day = day;
	}




	public int getGold() {
		return gold;
	}




	public void setGold(int gold) {
		this.gold = gold;
	}




	@Override
	public String toString() {
		return "ReserveBean [userID=" + userID + ", cellphone=" + cellphone + ", day=" + day + ", gold=" + gold + "]";
	}
	
	
}