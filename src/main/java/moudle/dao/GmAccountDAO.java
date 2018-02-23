package moudle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import moudle.bean.AccountBean;
import moudle.bean.GmAccountBean;

public class GmAccountDAO {
DataSource ds = null;
	
	public GmAccountDAO()throws SQLException {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String SELECT_GMACCOUNT_BY_GM_EMAIL = "Select * from gm_account "
            + "  where gm_email=? ";
	public GmAccountBean selectGmAccountByGmEmail (String GmEmail) throws SQLException {
		GmAccountBean result = null;
		
		try(
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_GMACCOUNT_BY_GM_EMAIL);
		){
				stmt.setString(1, GmEmail);
				try(
					ResultSet rset = stmt.executeQuery();
				){
					if (rset.next()) {
						result = new GmAccountBean();
						result.setId(rset.getInt("id"));
						result.setGmEmail(rset.getString("gm_email"));
						result.setPassword(rset.getString("password"));
					}
				}
		}
		return result;
	}
	
}
