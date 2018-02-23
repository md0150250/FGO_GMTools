package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import moudle.service.GmAccountService;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Map<String, String> message = new HashMap<>();
		request.setAttribute("message", message);
		GmAccountService gas=new GmAccountService();
		int result=0;
		
		String gmEmail = request.getParameter("gmEmail");
		String password = request.getParameter("password");
		
		try {
			result=gas.selectGmAccountByGmEmail(gmEmail, password, session);
			if(result==0){
				message.put("aerror", "查無此帳號");
			}else if(result==2){
				message.put("perror", "密碼錯誤");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (!message.isEmpty()) {
			message.put("account", gmEmail);
			message.put("password", password);
			RequestDispatcher rd = request
					.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			return;
		}else{
			
			RequestDispatcher rd = request
					.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			return;
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
