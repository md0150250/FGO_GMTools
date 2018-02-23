package moudle.bean;

import java.io.Serializable;

public class AccountBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int userID;
	private String fbID;
	private String gmailID;
	private int lv;
	private int exp;
	private int vip;
	private int vipExp;
	private int mudCode;
	private int gold;
	
	
	
	public AccountBean() {
		super();
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFbID() {
		return fbID;
	}
	public void setFbID(String fbID) {
		this.fbID = fbID;
	}
	public String getGmailID() {
		return gmailID;
	}
	public void setGmailID(String gmailID) {
		this.gmailID = gmailID;
	}
	
	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AccountBean [userID=" + userID + ", fbID=" + fbID + ", gmailID=" + gmailID + ", lv=" + lv + ", exp="
				+ exp + ", vip=" + vip + ", vipExp=" + vipExp + ", mudCode=" + mudCode + ", gold=" + gold + "]";
	}
	
	
}
