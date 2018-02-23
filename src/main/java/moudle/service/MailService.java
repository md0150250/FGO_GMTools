package moudle.service;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import moudle.bean.MailBean;
import moudle.dao.MailDAO;

public class MailService {
	                           
	public int insertMailInGmEmail(String suserID, String sgold, String smudCode, String slvExp, String svipExp, String slotteryTicket, String message, String GmEmail) throws SQLException {
		MailDAO dao = new MailDAO();
		int gold=0;
		int mudCode=0;
		int lvExp=0;
		int vipExp=0;
		int lotteryTicket=0;
		
		try {
			gold = Integer.parseInt(sgold);
			mudCode = Integer.parseInt(smudCode);
			lvExp = Integer.parseInt(slvExp);
			vipExp = Integer.parseInt(svipExp);
			lotteryTicket = Integer.parseInt(slotteryTicket);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			//輸入格式不對
			return 0;
		};
		
		Timestamp beginAt = new Timestamp(System.currentTimeMillis());  
		Timestamp endAt=new Timestamp(beginAt.getTime()+(1000*3600*24*14));
		
		int hour= Integer.parseInt(String.format("%tH",beginAt));
		int minute= Integer.parseInt(String.format("%tM",beginAt));
		int second= Integer.parseInt(String.format("%tS",beginAt));
		
		 endAt=new Timestamp(endAt.getTime()-(1000*(3600*(hour-6)+60*minute+second)));
		if(hour<6) {
			endAt=new Timestamp(endAt.getTime()-(1000*3600*24));
		}
		int result = dao.insertMailInGmEmail(suserID, gold, mudCode, lvExp, vipExp, lotteryTicket, message, beginAt, endAt,GmEmail);
		if(result!=1) {
			//sql錯誤
			return 2;
		}
		return 1;
	}
	
	public int insertMailInUserEmail(String suserID, String sgold, String smudCode, String slvExp, String svipExp, String slotteryTicket, String message) throws SQLException{
		MailDAO dao = new MailDAO();
		int gold=0;
		int mudCode=0;
		int lvExp=0;
		int vipExp=0;
		int lotteryTicket=0;
		int sid=0;
		
		try {
			gold = Integer.parseInt(sgold);
			mudCode = Integer.parseInt(smudCode);
			lvExp = Integer.parseInt(slvExp);
			vipExp = Integer.parseInt(svipExp);
			lotteryTicket = Integer.parseInt(slotteryTicket);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			//輸入格式不對
			return 0;
		}
		//先去空白再切開
		String[] lsuserID=suserID.replaceAll("\\s+", "").split(";");
		Timestamp beginAt = new Timestamp(System.currentTimeMillis());  
		Timestamp endAt=new Timestamp(beginAt.getTime()+(1000*3600*24*15));
		
		int hour= Integer.parseInt(String.format("%tH",beginAt));
		int minute= Integer.parseInt(String.format("%tM",beginAt));
		int second= Integer.parseInt(String.format("%tS",beginAt));
		
		 endAt=new Timestamp(endAt.getTime()-(1000*(3600*(hour-6)+60*minute+second)));
		if(hour<6) {
			endAt=new Timestamp(endAt.getTime()-(1000*3600*24));
		}
			
		for(int i=0;i<lsuserID.length;i++) {
			if(lsuserID[i].equals("")) {
				continue;
			}
			try {
				sid = Integer.parseInt(lsuserID[i]);
				
				} catch (NumberFormatException e) {
				// e.printStackTrace();
				//輸入格式不對
				return 0;
			}
			int result = dao.insertMailInUserEmail(sid, gold, mudCode, lvExp, vipExp, lotteryTicket, message, beginAt, endAt);
			if(result!=1) {
				//sql錯誤
				return 2;
			}
		}
		return 1;
	}
	
	public int insertMailInAllUserEmail(String suserID, String sgold, String smudCode, String slvExp, String svipExp, String slotteryTicket, String message) throws SQLException{
		MailDAO dao = new MailDAO();
		int gold=0;
		int mudCode=0;
		int lvExp=0;
		int vipExp=0;
		int lotteryTicket=0;
		int sid=0;
		
		try {
			gold = Integer.parseInt(sgold);
			mudCode = Integer.parseInt(smudCode);
			lvExp = Integer.parseInt(slvExp);
			vipExp = Integer.parseInt(svipExp);
			lotteryTicket = Integer.parseInt(slotteryTicket);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
			//輸入格式不對
			return 0;
		}
		
		Timestamp beginAt = new Timestamp(System.currentTimeMillis());  
		Timestamp endAt=new Timestamp(beginAt.getTime()+(1000*3600*24*15));
		
		int hour= Integer.parseInt(String.format("%tH",beginAt));
		int minute= Integer.parseInt(String.format("%tM",beginAt));
		int second= Integer.parseInt(String.format("%tS",beginAt));
		
		 endAt=new Timestamp(endAt.getTime()-(1000*(3600*(hour-6)+60*minute+second)));
		if(hour<6) {
			endAt=new Timestamp(endAt.getTime()-(1000*3600*24));
		}
		
		ArrayList<Integer> all=dao.selectAllUserID();
		
		for(int i=0;i<all.size();i++) {
			int result = dao.insertMailInUserEmail(all.get(i), gold, mudCode, lvExp, vipExp, lotteryTicket, message, beginAt, endAt);
			if(result!=1) {
				//sql錯誤
				return 2;
			}
		
		}
		return 1;
	}
	
	
	public ArrayList<MailBean> selectAllMail() throws SQLException{
		MailDAO dao = new MailDAO();
		ArrayList<MailBean> result =new ArrayList<MailBean>();
		result=dao.selectAllMail();
		
		return result;
	}
	
	
	
	
	
	
}
