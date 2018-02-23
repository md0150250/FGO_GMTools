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

import moudle.bean.MailBean;
import moudle.service.MailService;


@WebServlet("/findMailServlet")
public class findMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Map<String, Object> answer = new HashMap<>();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		ArrayList<MailBean> result =new ArrayList<MailBean>();
		ArrayList<Map<String, String>> res=new ArrayList<Map<String, String>>();
		MailService ms=new MailService();
		try {
			result= ms.selectAllMail();
			answer.put("message1", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<result.size();i++){
			//日期
			Map<String, String> m=new HashMap<>();
			SimpleDateFormat style = new SimpleDateFormat("yyyy/MM/dd"); 
			String date = style.format(result.get(i).getBeginAt()); 
			m.put("date", date);
			
			//對象
			String[] lsuserID=result.get(i).getUserID().split(";");
			String suserID="";
			if(lsuserID.length>1) {
				suserID=lsuserID[0]+"...";
			}else{
				suserID=lsuserID[0];
			}
			m.put("userID", suserID);
			
			//內容
			String s="現金:"+result.get(i).getGold()+"  泥碼:"+result.get(i).getMudCode()+"  LVEXP:"+result.get(i).getExp()
					+"  VIPEXP:"+result.get(i).getVipExp()+"  彩票:"+result.get(i).getLotteryTicket();
			m.put("message", s);
			
			//發布者 
			m.put("gm", result.get(i).getGmEmail());
			res.add(m);
		}
		answer.put("message2", res);
		out.println(gson.toJson(answer));
		out.close();
		return;
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
