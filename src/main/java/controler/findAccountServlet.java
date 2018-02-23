package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import moudle.TestTimer;
import moudle.bean.AccountBean;
import moudle.bean.MailBean;
import moudle.bean.ReserveBean;
import moudle.service.AccountService;
import moudle.service.ReserveService;


@WebServlet("/findAccountServlet")
public class findAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Map<String, Object> message = new HashMap<>();
		//Map<String, ArrayList<ReserveBean>> amessage = new HashMap<>();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		int userID=0;
		int totalReserve=-1;
		AccountBean ab= null;
		AccountService as=new AccountService();	
		ArrayList<ReserveBean> rblist = null;
		ArrayList<MailBean> mblist = null;
		ArrayList<String> datelist =new ArrayList<String>();
		ArrayList<String> contentlist =new ArrayList<String>();
		ReserveService rs=new ReserveService();
		//檢查是否輸入帳號id
		String sid = request.getParameter("account");
		if (sid==""){
			return;
		}
		
		
		//找出帳號,有就把userid放入map物件
		try {
			userID=as.findUserID(sid);
			ab=as.findAccount(userID);
			if(ab!=null) {
				message.put("userid", ab.getUserID());
				message.put("lv", ab.getLv());
				message.put("exp", ab.getExp());
				message.put("vip", ab.getVip());
				message.put("vipExp", ab.getVipExp());
				message.put("mudCode", ab.getMudCode());
				message.put("gold", ab.getGold());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//找出儲值歷史紀錄和儲值總金額
		try {
			rblist=rs.selectReserveListByUserID(userID);
			if(rblist !=null){
				message.put("rblist", rblist);
			}
			totalReserve=rs.selectTotalReserveMondyByUserID(userID);
			if(totalReserve!=-1){
				message.put("totalReserve", totalReserve);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//找出玩家歷史信件
		try {
			mblist=rs.selectReserveMailListByUserID(userID);
			if(rblist !=null){
								
				for(int i=0;i<mblist.size();i++){
					String content ="現金:"+mblist.get(i).getGold()+"  泥碼:"+mblist.get(i).getMudCode()+
							        "  LVEXP:"+mblist.get(i).getVipExp()+"  彩票:"+mblist.get(i).getLotteryTicket();
					contentlist.add(content);
					
					SimpleDateFormat style = new SimpleDateFormat("yyyy/MM/dd"); 
					String sdate = style.format(mblist.get(i).getBeginAt()); 
					datelist.add(sdate);
				
				}
				message.put("contentlist", contentlist);
				message.put("datelist", datelist);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println(gson.toJson(message));
		out.close();
		return;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
