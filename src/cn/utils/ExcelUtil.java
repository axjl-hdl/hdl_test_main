package cn.utils;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static void main(String[] args) {
		File file = new File("C:/Users/hudl/Desktop/工作计划.xlsx");
		boolean a=file.isFile();
		if (a) {
			try {
				ExcelUtil.readExcel(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void readExcel(File file) throws Exception{
		
		Workbook book = WorkbookFactory.create(file);//读取表格文件，创建工作台（表）
		Sheet sheet = book.getSheetAt(1);//
		//获取总行数
		int rowNum = sheet.getLastRowNum();
		Row firstRow = sheet.getRow(0);
		//总列数
		int colNum = firstRow.getPhysicalNumberOfCells()+1;
		for (int i = 1; i <= rowNum; i++) {
			Row row = sheet.getRow(i);
			String[] cellArr = new String[colNum];
			for (int j = 0; j < colNum; j++) {
				Cell cell = row.getCell(j);
				System.out.print(getCellValue(cell)+"  ");
//				cellArr[j] = getCellValue(cell);
			}
			System.out.println();
		}
		
	}
	
	public static String getCellValue(Cell cell){  
        String cellValue = "";  
        if(cell == null){  
            return cellValue;  
        }  
        //把数字当成String来读，避免出现1读成1.0的情况  
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
            cell.setCellType(Cell.CELL_TYPE_STRING);  
        }  
        //判断数据的类型  
        switch (cell.getCellType()){  
            case Cell.CELL_TYPE_NUMERIC: //数字  
                cellValue = String.valueOf(cell.getNumericCellValue());  
                break;  
            case Cell.CELL_TYPE_STRING: //字符串  
                cellValue = String.valueOf(cell.getStringCellValue());  
                break;  
            case Cell.CELL_TYPE_BOOLEAN: //Boolean  
                cellValue = String.valueOf(cell.getBooleanCellValue());  
                break;  
            case Cell.CELL_TYPE_FORMULA: //公式  
                cellValue = String.valueOf(cell.getCellFormula());  
                break;  
            case Cell.CELL_TYPE_BLANK: //空值   
                cellValue = "";  
                break;  
            case Cell.CELL_TYPE_ERROR: //故障  
                cellValue = "非法字符";  
                break;  
            default:  
                cellValue = "未知类型";  
                break;  
        }  
        return cellValue;  
    } 
	
	//导出
//	public void exportExcel() throws Exception{
//		String[] header = new String[]{"序号","商户ID","商家名称","商户简称","商户账户名","商家类型","接口地址","联系人","联系方式","限定IP","签名key","状态","创建时间","修改时间","备注"};
//		List<DbMerchantInfo> list = merchantService.queryMerchantList(merchantInfo);
//		
//		//声明工作簿
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		//生成表格
//		HSSFSheet sheet = workbook.createSheet("商家信息报表");
//		//生成表格标题
//		HSSFRow row = sheet.createRow(0);
//		for (int i = 0; i < header.length; i++) {
//			HSSFCell cell = row.createCell(i);
//			HSSFRichTextString text = new HSSFRichTextString(header[i]);
//			cell.setCellValue(text);
//		}
//		//遍历集合数据，产生数据行
//		int rownum=1;
//		for (DbMerchantInfo mer : list) {
//			row = sheet.createRow(rownum);
//			for (int i = 0; i < header.length; i++) {
//				HSSFCell cell = row.createCell(i);
//				cell.setCellValue(getValue(rownum,i,mer));
//			}
//			rownum++;
//		}
////			String userAgent = request.getHeader("User-Agent");
//		String fileName = "商家信息报表.xls";
//		fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
//		
////			response.setHeader("Content-Type", "application/msexcel");
//		response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
//		OutputStream out = response.getOutputStream();  
//		response.setContentType("application/octet-stream");
//		workbook.write(response.getOutputStream());
//		out.flush();
//		out.close();
//	}
}
