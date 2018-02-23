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

import moudle.bean.MailBean;

public class MailDAO {
DataSource ds = null;
	
	public MailDAO()throws SQLException {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_MAIL_IN_GM_EMAIL = "insert into log_gm(user_id, gold, mud_code, exp, vip_exp, lottery_ticket, message, begin_at, end_at, gm_email) "
			+"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public int insertMailInGmEmail(String suserID, int gold, int mudCode, int lvExp, int vipExp, int lotteryTicket, String message, java.sql.Timestamp beginAt, java.sql.Timestamp endAt, String GmEmail) throws SQLException {
		int result = 0;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(INSERT_MAIL_IN_GM_EMAIL);
		){
				stmt.setString(1,suserID);
				stmt.setInt(2,gold);
				stmt.setInt(3,mudCode);
				stmt.setInt(4,lvExp);
				stmt.setInt(5,vipExp);
				stmt.setInt(6,lotteryTicket);
				stmt.setString(7,message);
				stmt.setTimestamp(8,beginAt);
				stmt.setTimestamp(9,endAt);
				stmt.setString(10,GmEmail);
				result = stmt.executeUpdate();
		 } 
		return result;
    }
	
	private static final String INSERT_MAIL_IN_USER_EMAIL = "insert into  user_email(user_id, gold, mud_code, exp, vip_exp, lottery_ticket, message, begin_at, end_at, status, look) "
			+"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, 2, 2)";
	public int insertMailInUserEmail(int userID, int gold, int mudCode, int lvExp, int vipExp, int lotteryTicket, String message, java.sql.Timestamp beginAt, java.sql.Timestamp endAt) throws SQLException {
		int result = 0;
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(INSERT_MAIL_IN_USER_EMAIL);
		){
				stmt.setInt(1,userID);
				stmt.setInt(2,gold);
				stmt.setInt(3,mudCode);
				stmt.setInt(4,lvExp);
				stmt.setInt(5,vipExp);
				stmt.setInt(6,lotteryTicket);
				stmt.setString(7,message);
				stmt.setTimestamp(8,beginAt);
				stmt.setTimestamp(9,endAt);
				result = stmt.executeUpdate();
		 } 
		return result;
    }
	
	
	private static final String SELECT_ALL_USERID = "Select user_id from account ";
           
	public ArrayList<Integer> selectAllUserID () throws SQLException  {
		ArrayList<Integer> result=new ArrayList<Integer>(); ;
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_USERID);
		){
				try(
					ResultSet rset = stmt.executeQuery();
				){
					while (rset.next()) {
						int id = rset.getInt("user_id");
						result.add(id);
					}
				}
		}
		return result;
	}
	
	
	private static final String SELECT_ALL_MAIL_OF_LOG_GM = "Select * from log_gm ";
    
	public ArrayList<MailBean> selectAllMail () throws SQLException  {
		ArrayList<MailBean> result=new ArrayList<MailBean>(); ;
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_MAIL_OF_LOG_GM);
		){
				try(
					ResultSet rset = stmt.executeQuery();
				){
					while (rset.next()) {
						MailBean mb=new MailBean();
						mb.setId(rset.getInt("id"));
						mb.setUserID(rset.getString("user_id"));
						mb.setGold(rset.getInt("gold"));
						mb.setMudCode(rset.getInt("gold"));
						mb.setExp(rset.getInt("exp"));
						mb.setVipExp(rset.getInt("vip_exp"));
						mb.setLotteryTicket(rset.getInt("lottery_ticket"));
						mb.setMessage(rset.getString("message"));
						mb.setBeginAt(rset.getTimestamp("begin_at"));
						mb.setGmEmail(rset.getString("gm_email"));
						result.add(mb);
					}
				}
		}
		return result;
	}
	
	
	
}
