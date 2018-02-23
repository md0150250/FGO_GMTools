package moudle.bean;

import java.io.Serializable;

public class MailBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String userID;
	private int lotteryTicket;
	private int exp;
	private int vipExp;
	private int mudCode;
	private int gold;
	private String message;
	private java.sql.Timestamp beginAt;
	private String gmEmail;
	
	public MailBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getLotteryTicket() {
		return lotteryTicket;
	}

	public void setLotteryTicket(int lotteryTicket) {
		this.lotteryTicket = lotteryTicket;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getVipExp() {
		return vipExp;
	}

	public void setVipExp(int vipExp) {
		this.vipExp = vipExp;
	}

	public int getMudCode() {
		return mudCode;
	}

	public void setMudCode(int mudCode) {
		this.mudCode = mudCode;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public java.sql.Timestamp getBeginAt() {
		return beginAt;
	}

	public void setBeginAt(java.sql.Timestamp beginAt) {
		this.beginAt = beginAt;
	}

	public String getGmEmail() {
		return gmEmail;
	}

	public void setGmEmail(String gmEmail) {
		this.gmEmail = gmEmail;
	}

	@Override
	public String toString() {
		return "MailBean [id=" + id + ", userID=" + userID + ", lotteryTicket=" + lotteryTicket + ", exp=" + exp
				+ ", vipExp=" + vipExp + ", mudCode=" + mudCode + ", gold=" + gold + ", message=" + message
				+ ", beginAt=" + beginAt + ", gmEmail=" + gmEmail + "]";
	}
	
	
	
}
