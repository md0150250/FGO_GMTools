package moudle.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import moudle.bean.MailBean;
import moudle.bean.ReserveBean;
import moudle.dao.ReserveDAO;

public class ReserveService {
	public ArrayList<ReserveBean> selectReserveListByUserID(int userID) throws SQLException{
		ReserveDAO dao = new ReserveDAO();
		ArrayList<ReserveBean> result = null;
		
		result = dao.selectReserveListByUserID(userID);
		
		return result;
	}
	
	public int selectTotalReserveMondyByUserID(int userID) throws SQLException{
		ReserveDAO dao = new ReserveDAO();
		int result = -1;
		
		result = dao.selectTotalReserveMondyByUserID(userID);
		
		return result;
	}
	
	public ArrayList<MailBean> selectReserveMailListByUserID(int userID) throws SQLException{
		ReserveDAO dao = new ReserveDAO();
		ArrayList<MailBean> result = null;
		result = dao.selectReserveMailListByUserID(userID);
		
		return result;
	}
}
