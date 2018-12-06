package cn.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelServlet extends HttpServlet{
	
	//下载表格模板
		public void getExcelModel(HttpServletRequest request,HttpServletResponse response)throws Exception{
			String filePath=request.getSession().getServletContext().getRealPath("/upload");//表格所在路径
			String fileName = "办卡结果填写模板.xlsx";
			File file = new File(filePath+"/"+fileName);
//			response.setCharacterEncoding("UTF-8");
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				//针对IE浏览器
				fileName = URLEncoder.encode(fileName, "UTF-8");  
			} else {  
				//针对其他浏览器（firefox、chrome、safari、opera）
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");  
			}  
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);//new String(fileName.getBytes("utf-8"),"iso-8859-1")
			response.addHeader("Content-Length", "" + file.length());
		    
			//读取文件  
		    InputStream in = new FileInputStream(file);  
		    OutputStream out = response.getOutputStream();  
		    response.setContentType("application/octet-stream");
		    //写文件  
	        int b;  
	        while((b=in.read())!= -1){  
	            out.write(b);  
	        }  
	        in.close(); 
	        out.flush();
	        out.close();  
		}
}
