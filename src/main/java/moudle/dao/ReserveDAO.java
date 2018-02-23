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

import moudle.bean.MailBean;
import moudle.bean.ReserveBean;

public class ReserveDAO {
DataSource ds = null;
	
	public ReserveDAO()throws SQLException {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String SELECT_RESERVE_BY_USERID = "Select * from  log_money"
            + "  where user_id=? and add_gold>0  order by created_at DESC";
	public ArrayList<ReserveBean> selectReserveListByUserID (int userID) throws SQLException  {
		ArrayList<ReserveBean> result = new ArrayList<ReserveBean>();
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_RESERVE_BY_USERID);
		){
				stmt.setInt(1, userID);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					while (rset.next()) {
						ReserveBean rb=new ReserveBean();
						rb.setUserID(rset.getInt("user_id"));
						rb.setCellphone("Android");
						rb.setGold(rset.getInt("add_gold"));
						SimpleDateFormat style = new SimpleDateFormat("yyyy/MM/dd"); 
						String date = style.format(rset.getDate("created_at")); 
						rb.setDay(date);
						result.add(rb);
						
						
					}
				}
		}
		return result;
	}
	
	private static final String SELECT_TOTAL_RESERVE_MONEY_BY_USERID = "Select sum(add_gold) from  log_money"
            + "  where user_id=? and add_gold>0  ";
	public int selectTotalReserveMondyByUserID (int userID) throws SQLException  {
		int result = -1;
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_TOTAL_RESERVE_MONEY_BY_USERID);
		){
				stmt.setInt(1, userID);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					if (rset.next()) {
						result=rset.getInt("sum(add_gold)");
					}
				}
		}
		return result;
	}
	
	private static final String SELECT_RESERVE_MAIL_BY_USERID = "Select * from  user_email"
            + "  where user_id=? order by begin_at DESC";
	public ArrayList<MailBean> selectReserveMailListByUserID (int userID) throws SQLException  {
		ArrayList<MailBean> result = new ArrayList<MailBean>();
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_RESERVE_MAIL_BY_USERID);
		){
				stmt.setInt(1, userID);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					while (rset.next()) {
						MailBean rb=new MailBean();
						//rb.setCellphone("Android");
						rb.setGold(rset.getInt("gold"));
						rb.setMudCode(rset.getInt("mud_code"));
						rb.setExp(rset.getInt("exp"));
						rb.setVipExp(rset.getInt("vip_exp"));
						rb.setLotteryTicket(rset.getInt("lottery_ticket"));
						rb.setBeginAt(rset.getTimestamp("begin_at"));
						result.add(rb);
						
						
					}
				}
		}
		return result;
	}
	
}
