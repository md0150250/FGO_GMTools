package controler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import moudle.TestTimer;

public class initServlet extends HttpServlet{
	  @Override    
	    public void init() throws ServletException {    
	        super.init();    
	        TestTimer.showTimer();
	    }    
}
