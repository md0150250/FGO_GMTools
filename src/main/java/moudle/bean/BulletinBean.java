package moudle.bean;

import java.io.Serializable;

public class BulletinBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private java.sql.Timestamp beginAt;
	private java.sql.Timestamp endAt;
	private int rank;
	private String picturePath;
	
	
	public BulletinBean() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public java.sql.Timestamp getBeginAt() {
		return beginAt;
	}


	public void setBeginAt(java.sql.Timestamp beginAt) {
		this.beginAt = beginAt;
	}


	public java.sql.Timestamp getEndAt() {
		return endAt;
	}


	public void setEndAt(java.sql.Timestamp endAt) {
		this.endAt = endAt;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public String getPicturePath() {
		return picturePath;
	}


	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}


	@Override
	public String toString() {
		return "BulletinBean [id=" + id + ", beginAt=" + beginAt + ", endAt=" + endAt + ", rank=" + rank
				+ ", picturePath=" + picturePath + "]";
	}
	
	
	
	
	
}
