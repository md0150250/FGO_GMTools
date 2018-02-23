package moudle.service;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import moudle.bean.GmAccountBean;
import moudle.dao.GmAccountDAO;

public class GmAccountService {
	
   public int selectGmAccountByGmEmail (String GmEmail,String password,HttpSession session) throws SQLException{
	   GmAccountBean result=null;
	   GmAccountDAO dao = new GmAccountDAO();
	   result=dao.selectGmAccountByGmEmail(GmEmail);
	   if(result==null) {
		   //查無此帳號
		   return 0;
	   }else if(!password.equals(result.getPassword())) {
		   //密碼錯誤
		   return 2;
	   }
	   session.setAttribute("gm", result);
	   return 1;
   }
}
