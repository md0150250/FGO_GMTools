package moudle.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import moudle.bean.AccountBean;
import moudle.dao.AccountDAO;

public class AccountService {
	// 輸入userid或fbid或gmailid找帳號
	public int findUserID(String sid) throws SQLException {
		AccountDAO dao = new AccountDAO();
		int result = 0;
		int id = 0;
		try {
			id = Integer.parseInt(sid);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
		}

		result = dao.selectUserIDByUserID(id);
		if (result == 0) {
			result = dao.selectUserIDByFbID(sid);
			if (result == 0) {
				result = dao.selectUserIDByGmailID(sid);
			}
		}
		return result;
	}

	public AccountBean findAccount(int userID) throws SQLException {
		AccountDAO dao = new AccountDAO();
		AccountBean bean = null;
		bean = dao.selectAccountByUserID(userID);
		return bean;
	}

	public int ChangeAccountData(Map<String, Integer> message, String suserID, String smudCode, String sgold,
			String slv, String sexp, String svip, String svipExp) throws SQLException {
		AccountDAO dao = new AccountDAO();
		int result = 0;
		int userID = 0;
		if (suserID.equals("0")) {
			// 一開始沒指定帳號
			return 2;
		} else {
			userID = Integer.parseInt(suserID);
		}
		// 有收到泥碼的話,修改泥碼
		if (smudCode != null) {
			try {
				int mudCode = Integer.parseInt(smudCode);
				result = dao.changeMudCode(userID, mudCode);
				message.put("mudCode", mudCode);
			} catch (NumberFormatException e) {
				// e.printStackTrace();
			}
		}
		// 有收到現金碼的話,修改現金碼
		if (sgold != null) {
			try {
				int gold = Integer.parseInt(sgold);
				result = dao.changeGold(userID, gold);
				message.put("gold", gold);
			} catch (NumberFormatException e) {
				// e.printStackTrace();
			}
		}
		// 有收到lv的話,修改lv
		if (slv != null) {
			try {
				int lv = Integer.parseInt(slv);
				result = dao.changeLv(userID, lv);
				message.put("gold", lv);
			} catch (NumberFormatException e) {
				// e.printStackTrace();
			}
		}
		// 有收到exp的話,修改exp
		if (sexp != null) {
			try {
				int exp = Integer.parseInt(sexp);
				result = dao.changeExp(userID, exp);
				message.put("gold", exp);
			} catch (NumberFormatException e) {
				// e.printStackTrace();
			}
		}
		// 有收到vip的話,修改vip
		if (svip != null) {
			try {
				int vip = Integer.parseInt(svip);
				result = dao.changeVip(userID, vip);
				message.put("gold", vip);
			} catch (NumberFormatException e) {
				// e.printStackTrace();
			}
		}
		// 有收到vipExp的話,修改vipExp
		if (svipExp != null) {
			try {
				int vipExp = Integer.parseInt(svipExp);
				result = dao.changeVipExp(userID, vipExp);
				message.put("gold", vipExp);
			} catch (NumberFormatException e) {
				// e.printStackTrace();
			}
		}

		return result;
	}
	
	public int checkUserId(String suserID) throws SQLException{
		AccountDAO dao = new AccountDAO();
		ArrayList<Integer> result = new ArrayList<Integer>();
		//ArrayList<Integer> userId = new ArrayList<Integer>();
		result = dao.selectAllUserId();
		String[] lsuserID=suserID.replaceAll("\\s+", "").split(";");
		for(String i: lsuserID) {
			if(i.equals("")) {
				continue;
			}
			try {
				int id = Integer.parseInt(i);
                int userid =dao.selectUserIDByUserID(id);
                if(userid==0) {
                	//輸入帳號錯誤
                	return 2;
                }

				} catch (NumberFormatException e) {
				// e.printStackTrace();
				//輸入格式不對
				return 1;
			}
		}
		
		
		
		return 0;
	}	
	

}
