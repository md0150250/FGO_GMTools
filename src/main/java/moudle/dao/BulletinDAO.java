package moudle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import moudle.bean.BulletinBean;
import moudle.bean.ReserveBean;

public class BulletinDAO {
	DataSource ds = null;

	public BulletinDAO() throws SQLException {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_BULLETIN = "insert into bulletin(begin_at, end_at, rank, picture_path) "
			+ " VALUES(?, ?, ?, ?)";

	public int insertBulletin(java.sql.Timestamp beginAt, java.sql.Timestamp endAt, int rank, String picturePath)
			throws SQLException {
		int result = 0;

		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_BULLETIN);) {
			stmt.setTimestamp(1, beginAt);
			stmt.setTimestamp(2, endAt);
			stmt.setInt(3, rank);
			stmt.setString(4, picturePath);
			result = stmt.executeUpdate();
		}
		return result;
	}

	private static final String UPDATE_BULLETIN = "update bulletin set begin_at=?, end_at=?, rank=?, picture_path=? where id=?";

	public int updateBulletin(int id, java.sql.Timestamp beginAt, java.sql.Timestamp endAt, int rank,
			String picturePath) throws SQLException {
		int result = 0;

		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_BULLETIN);) {
			stmt.setTimestamp(1, beginAt);
			stmt.setTimestamp(2, endAt);
			stmt.setInt(3, rank);
			stmt.setString(4, picturePath);
			stmt.setInt(5, id);
			result = stmt.executeUpdate();
		}
		return result;
	}

	private static final String SELECT_ALL_BULLETINS = "select * from  bulletin ";

	public ArrayList<BulletinBean> selectAllBulletins() throws SQLException {
		ArrayList<BulletinBean> result = new ArrayList<BulletinBean>();

		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_BULLETINS);) {
			try (ResultSet rset = stmt.executeQuery();) {
				while (rset.next()) {
					BulletinBean bb = new BulletinBean();
					bb.setId(rset.getInt("id"));
					bb.setBeginAt(rset.getTimestamp("begin_at"));
					bb.setEndAt(rset.getTimestamp("end_at"));
					bb.setRank(rset.getInt("rank"));
					bb.setPicturePath(rset.getString("picture_path"));
					result.add(bb);
				}
			}
		}
		return result;
	}

	private static final String DELETE_BULLETIN = "delete from  bulletin where id=?";
	public int deleteBulletin(int id) throws SQLException {
		int result = 0;

		try (Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(DELETE_BULLETIN);) {

			stmt.setInt(1, id);
			result = stmt.executeUpdate();
		}
		return result;
	}
	
	private static final String SELECT_BULLETIN_PICTURE_PATH = "select picture_path from  bulletin where id= ?";
	public String selectBulletinPicturePath(int id) throws SQLException {
		String result = "";

		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BULLETIN_PICTURE_PATH);) 
		{
			stmt.setInt(1, id);
			try(
				ResultSet rset = stmt.executeQuery();
			){
				if (rset.next()) {
					result = rset.getString("picture_path");
					
				}
			}
		}
		return result;
	}
	
	
	private static final String DELETE_BULLETIN_BY_Expired = "delete from  bulletin where end_at<=?";
	public int deleteBulletinByExpired(java.sql.Timestamp  endAt) throws SQLException {
		int result = 0;

		try (Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(DELETE_BULLETIN_BY_Expired);) {

			stmt.setTimestamp(1, endAt);
			result = stmt.executeUpdate();
		}
		return result;
	}
	
	private static final String SELECT_BULLETIN_PICTURE_PATH_BY_Expired = "select picture_path from  bulletin where end_at<=?";
	public ArrayList<String> selectBulletinPicturePathByExpired(java.sql.Timestamp  endAt) throws SQLException {
		ArrayList<String> result = new ArrayList<String>();

		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_BULLETIN_PICTURE_PATH_BY_Expired);) 
		{
			stmt.setTimestamp(1,endAt);
			try(
				ResultSet rset = stmt.executeQuery();
			){
				while(rset.next()) {
					String s=rset.getString("picture_path");
					System.out.println(s);
					result.add(s);
				}
			}
		}
		System.out.println(result);
		return result;
	}
	
	

}
