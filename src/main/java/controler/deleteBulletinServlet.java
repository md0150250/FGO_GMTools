package controler;

import java.io.File;
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

import moudle.TestScp;
import moudle.service.BulletinService;

@WebServlet("/deleteBulletinServlet")
public class deleteBulletinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Map<String, Integer> message = new HashMap<>();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		BulletinService bs = new BulletinService();
		int result = 0;
		String fileName="";

		// 判斷是否有確定要執行操作
		String choose = request.getParameter("choose");
		if (choose.equals("false")) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		String sid = request.getParameter("id");
		
		
		try {
			fileName=bs.selectBulletinPicturePath(sid);
			result = bs.deleteBulletin(sid);
			if (result == 1) {
				
				TestScp.rmFile("/home/johnny/img/"+fileName);
				File file = new File("/home/johnny/tomcat/webapps/img/"+fileName);
				file.delete();
				//刪除成功
				message.put("Message", 1);
				
			} else {
				//刪除失敗
				message.put("Message", 0);
			}
		} catch (SQLException e) {
			//刪除失敗
			message.put("Message", 0);
			e.printStackTrace();
		}
		
		out.println(gson.toJson(message));
		out.close();
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
