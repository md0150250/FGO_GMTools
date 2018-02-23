package controler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import moudle.TestScp;
import moudle.service.BulletinService;


@WebServlet("/changeBulletinServlet")
public class changeBulletinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)){//判断是不是文件上传表单，如果不是设置页面500错误
			
			File stempFile = new File("c:/stemp");//新建一个文件作为上传文件的临时路径
			if(!stempFile.exists()){//如果该路径不存在，就创建它
				stempFile.mkdir();
			}
			ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory(10 * 1000 * 1024, stempFile ));
			stempFile.delete();
			try {
				List<FileItem> items = sfu.parseRequest(request);//返回表单所有元素的集合
				BulletinService bs = new BulletinService();
				String sbeginAt = null;
				String sendAt= null;//设置变量接收非文件元素值
				String srank = null;
				String sid = null;
				for(FileItem item:items){
					if(item.isFormField()){
						if(item.getFieldName().indexOf("id")>=0){
							sid = item.getString();
						}else if(item.getFieldName().indexOf("beginAt")>=0){
							sbeginAt = item.getString();//通过对普通元素name值的判断找到该元素的值
						}else if(item.getFieldName().indexOf("endAt")>=0){
							sendAt = item.getString();
						}else if(item.getFieldName().indexOf("rank")>=0){
							srank = item.getString();
						}
					}else{
						//如果是文件元素
						String name = item.getName();//获取文件名
						if (name.length()<=0){
							request.setAttribute("Message", "沒選擇圖片");
							request.getRequestDispatcher("index.jsp").forward(request, response);
							return;
						} 
						String extName = name.substring(name.indexOf("."));//获取文件的后缀名
						if(extName.equals(".png") || extName.equals(".jpg") || extName.equals(".gif")){
							//规定上传文件的类型
							long maxSize = 1024*1024*10;//设置上传的文件最大为10M
							if(item.getSize()<maxSize){
								File path = new File("/home/johnny/tomcat/webapps/img");
								//设置上传的文件路径为根目录中的img文件夹，如果，没有就创建
								if(!path.exists()){
									path.mkdir();
								}
								String fileName = name;
								File file = new File(path,fileName);//设置上传的文件
								
								
								item.write(file);//将图片文件上传
								TestScp.rmFile("/home/johnny/img/"+bs.selectBulletinPicturePath(sid));
								File oldFile = new File("/home/johnny/tomcat/webapps/img/"+bs.selectBulletinPicturePath(sid));
								oldFile.delete();
								TestScp.putFile("/home/johnny/tomcat/webapps/img/"+fileName, "/home/johnny/img");
								
								
								
								//存入database
								int result=bs.updateBulletin(sid,sbeginAt, sendAt, srank, "/home/johnny/tomcat/webapps/img/"+fileName);
								if(result==1){
									request.setAttribute("Message", "更改成功");	
								}else if(result==0) {
									request.setAttribute("Message", "日期或優先序格式不對");
								}else if(result==2) {
									request.setAttribute("Message", "sql出錯");
								}else if(result==3) {
									request.setAttribute("Message", "截止日期比起始日期早");
								}
							
							}else{
								//檔案太大
								request.setAttribute("Message", "檔案太大");
							}
						}else{
							//檔案格式不對
							request.setAttribute("Message", "檔案格式不對");
						}
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			request.setAttribute("Message", "文件上传表单设置错误！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
