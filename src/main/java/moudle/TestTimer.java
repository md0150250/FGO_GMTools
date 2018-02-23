package moudle;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import moudle.service.BulletinService;

public class TestTimer {
    static int count = 0;

    public static void showTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
               
            	BulletinService bs= new BulletinService();
            	try {
					bs.deleteBulletinByExpired();
					System.out.println("刪除成功");
				} catch (SQLException e) {
					System.out.println("刪除失敗");
					e.printStackTrace();
				}
            	
            }
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的6點执行，
        calendar.set(year, month, day, 06, 00, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        //System.out.println(date);

        //int period = 10 * 1000;
        //每天的date时刻执行task，每隔2秒重复执行
        //timer.schedule(task, date, period);
        //每天的date时刻执行task, 仅执行一次
        timer.schedule(task, date);
    }


    
    

}
