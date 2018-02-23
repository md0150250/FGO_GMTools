package moudle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import moudle.bean.AccountBean;
import moudle.bean.BulletinBean;





public class AccountDAO {
DataSource ds = null;
	
	public AccountDAO()throws SQLException {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String SELECT_USERID_BY_USERID = "Select user_id from account "
            + "  where user_id=? ";
	public int selectUserIDByUserID (int userID) throws SQLException  {
		int result = 0;
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_USERID_BY_USERID);
		){
				stmt.setInt(1, userID);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					if (rset.next()) {
						result = rset.getInt("user_id");
						//result.setUserID(rset.getInt("user_id"));
						//result.setFbID(rset.getString("fb_id"));
						//result.setGmailID(rset.getString("gmail_id"));
					}
				}
		}
		return result;
	}
	
	private static final String SELECT_USERID_BY_FBID = "Select user_id from account "
            + "  where fb_id=? ";
	public int selectUserIDByFbID (String fbID) throws SQLException  {
		int result = 0;
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_USERID_BY_FBID);
		){
				stmt.setString(1, fbID);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					if (rset.next()) {
						result = rset.getInt("user_id");
					}
				}
		}
		return result;
	}
	
	private static final String SELECT_USERID_BY_GMAILID = "Select user_id from account "
            + "  where gmail_id=? ";
	public int selectUserIDByGmailID (String gmailID) throws SQLException  {
		int result = 0;
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_USERID_BY_GMAILID);
		){
				stmt.setString(1, gmailID);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					if (rset.next()) {
						result = rset.getInt("user_id");
					}
				}
		}
		return result;
	}
	
	private static final String SELECT_ACCOUNT_BY_USERID = "Select * from user_base_info "
            + "  where user_id=? ";
	public AccountBean selectAccountByUserID (int userID) throws SQLException  {
		AccountBean result = null;
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_ACCOUNT_BY_USERID);
		){
				stmt.setInt(1, userID);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					if (rset.next()) {
						result = new AccountBean();
						result.setUserID(rset.getInt("user_id"));
						result.setLv(rset.getInt("lv"));
						result.setExp(rset.getInt("exp"));
						result.setVip(rset.getInt("vip"));
						result.setVipExp(rset.getInt("vip_exp"));
						result.setMudCode(rset.getInt("mud_code"));
						result.setGold(rset.getInt("gold"));
					}
				}
		}
		return result;
	}
	
	private static final String UPDATE_MUDCODE = "Update user_base_info set mud_code=? where user_id=?";
	public int changeMudCode(int userID, int mudCode) throws SQLException {
		int result = 0;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_MUDCODE);
		){
				stmt.setInt(1, mudCode);
				stmt.setInt(2,userID);
				result = stmt.executeUpdate();

		 } 
		return result;
    }
	
	private static final String UPDATE_GOLD = "Update user_base_info set gold=? where user_id=?";
	public int changeGold(int userID, int gold) throws SQLException {
		int result = 0;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_GOLD);
		){
				stmt.setInt(1, gold);
				stmt.setInt(2,userID);
				result = stmt.executeUpdate();
		 } 
		return result;
    }
	
	private static final String UPDATE_LV = "Update user_base_info set lv=? where user_id=?";
	public int changeLv(int userID, int lv) throws SQLException {
		int result = 0;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_LV);
		){
				stmt.setInt(1, lv);
				stmt.setInt(2,userID);
				result = stmt.executeUpdate();
		 } 
		return result;
    }
	
	private static final String UPDATE_EXP = "Update user_base_info set exp=? where user_id=?";
	public int changeExp(int userID, int exp) throws SQLException {
		int result = 0;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_EXP);
		){
				stmt.setInt(1, exp);
				stmt.setInt(2,userID);
				result = stmt.executeUpdate();
		 } 
		return result;
    }
	
	private static final String UPDATE_VIP = "Update user_base_info set vip=? where user_id=?";
	public int changeVip(int userID, int vip) throws SQLException {
		int result = 0;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_VIP);
		){
				stmt.setInt(1, vip);
				stmt.setInt(2,userID);
				result = stmt.executeUpdate();
		 } 
		return result;
    }
	
	private static final String UPDATE_VIPEXP = "Update user_base_info set vip_exp=? where user_id=?";
	public int changeVipExp(int userID, int vipExp) throws SQLException {
		int result = 0;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE_VIPEXP);
		){
				stmt.setInt(1, vipExp);
				stmt.setInt(2,userID);
				result = stmt.executeUpdate();
		 } 
		return result;
    }
	
	
	private static final String SELECT_ALL_USERID = "select user_id from account";
	public  ArrayList<Integer> selectAllUserId() throws SQLException {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_USERID);
		){
			try(
					ResultSet rset = stmt.executeQuery();
				){
					while (rset.next()) {
						result.add(rset.getInt("user_id"));
					}
				}
				
		 } 
		return result;
    }
	
}

