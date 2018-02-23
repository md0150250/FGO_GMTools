package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
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

import moudle.bean.BulletinBean;
import moudle.service.BulletinService;

/**
 * Servlet implementation class findBulletinServlet
 */
@WebServlet("/findBulletinServlet")
public class findBulletinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Map<String, Object> answer = new HashMap<>();
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		ArrayList<BulletinBean> alb= new ArrayList<BulletinBean>();
		ArrayList<Map<String, Object>> l=new ArrayList<Map<String, Object>>();
		BulletinService bs = new BulletinService();
		String beginAt="";
		String endAt="";
		
		try {
			alb=bs.selectAllBulletins();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(BulletinBean bb: alb) {
			Map<String, Object> m=new HashMap<>();
			//圖片ID
			 m.put("id", bb.getId());
			 //公告開始日
			 DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			 beginAt = sdf.format(bb.getBeginAt()); 
			 m.put("beginAt", beginAt);
			 //公告截止日
			 endAt = sdf.format(bb.getEndAt()); 
			  m.put("endAt", endAt);
			 //優先序
			 m.put("rank", bb.getRank());
			 //圖片路徑
			 String[] picturePath= bb.getPicturePath().split("/");
			 m.put("picturePath", picturePath[picturePath.length-1]);
			 l.add(m);
		}
		if(l.size()>=0) {
			answer.put("message", l);
		}
		
		out.println(gson.toJson(answer));
		out.close();
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
