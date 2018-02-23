package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import moudle.service.AccountService;

/**
 * Servlet implementation class changeAccountDataServlet
 */
@WebServlet("/changeAccountDataServlet")
public class changeAccountDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Map<String, Integer> message = new HashMap<>();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		AccountService as=new AccountService();	
		int result=0;
		
		String suserID = request.getParameter("userID");
		String smudCode = request.getParameter("mudcode");
		String sgold = request.getParameter("gold");
		String slv = request.getParameter("LV");
		String sexp = request.getParameter("EXP");
		String svip = request.getParameter("vipLV");
		String svipExp = request.getParameter("vipEXP");
		
		//判斷是否有確定要執行操作
		String choose = request.getParameter("choose");
		if (choose.equals("false")) {
			out.println(gson.toJson(message));
			out.close();
			return;
		}
		
		
		try {
			result=as.ChangeAccountData(message, suserID, smudCode, sgold, slv, sexp, svip, svipExp);
		   	if (result==2) {
		    	//沒輸入帳號
		    	message.put("success",2);
		    }else if(result==0){
		    	//修改失敗
		    	message.put("success",0);
		    }else if(result==1){
		    	//修改成功
		    	message.put("success",1);
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
