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

import moudle.bean.GmAccountBean;
import moudle.service.AccountService;
import moudle.service.MailService;


@WebServlet("/sendMailServlet")
public class sendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Map<String, Object> answer = new HashMap<>();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		String suserID = request.getParameter("account");
		String sgold = request.getParameter("gold");
		String smudCode = request.getParameter("mudCode");
		String slvExp = request.getParameter("lvExp");
		String svipExp = request.getParameter("vipExp");
		String slotteryTicket = request.getParameter("lotteryTicket");
		String message = request.getParameter("message");
		// 用all來分辨是不是全服發送
		String all = request.getParameter("all");
		GmAccountBean gm = (GmAccountBean) session.getAttribute("gm");
		String GmEmail = gm.getGmEmail();
		MailService ms = new MailService();
		AccountService as = new AccountService();
		int result = 0;

		// 判斷是否有確定要執行操作
		String choose = request.getParameter("choose");
		if (choose.equals("false")) {
			out.println(gson.toJson(message));
			out.close();
			return;
		}

		suserID = suserID.replace('\n', ' ');
		suserID = suserID.replaceAll("\\s+", "");
		message = message.replace('\n', ' ');
		message = message.replaceAll("\\s+", "");

		if (all.equals("false") && suserID.equals("")) {
			// 非全服發送但未輸入userid
			answer.put("message", 0);
		}
		if (sgold.equals("") || smudCode.equals("") || slvExp.equals("") || svipExp.equals("")
				|| slotteryTicket.equals("") || message.equals("")) {
			// 有欄位未填寫
			answer.put("message", 0);
		}

		if (all.equals("true")) {
			suserID = "All";
		} else {
			try {
				result = as.checkUserId(suserID);
				if (result == 1) {
					// 輸入格式不對
					answer.put("message", 2);
				} else if (result == 2) {
					// 輸入的帳號有錯誤
					answer.put("message", 4);
				}
			} catch (SQLException e) {
				answer.put("message", 3);
				e.printStackTrace();
			}

		}

		// 有錯誤就直接回傳
		if (!answer.isEmpty()) {
			out.println(gson.toJson(answer));
			out.close();
			return;
		}

		try {
			// 存入log_gm
			result = ms.insertMailInGmEmail(suserID, sgold, smudCode, slvExp, svipExp, slotteryTicket, message,
					GmEmail);
			if (result == 1) {
				// 寄信成功
				answer.put("message", 1);
			} else if (result == 0) {
				// 輸入格式不對
				answer.put("message", 2);
			}

			// 存入user_email
			if (all.equals("false")) {
				result = ms.insertMailInUserEmail(suserID, sgold, smudCode, slvExp, svipExp, slotteryTicket, message);
			} else {
				result = ms.insertMailInAllUserEmail(suserID, sgold, smudCode, slvExp, svipExp, slotteryTicket,
						message);
			}

			if (result == 1) {
				// 寄信成功
				answer.put("message", 1);
			} else if (result == 0) {
				// 輸入格式不對
				answer.put("message", 2);
			}

		} catch (SQLException e) {
			answer.put("message", 3);
			e.printStackTrace();
		}
		
		
		out.println(gson.toJson(answer));
		out.close();
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
