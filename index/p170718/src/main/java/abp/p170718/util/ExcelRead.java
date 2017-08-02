package abp.p170718.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
		private static final String TYPE_XLS = "xls";
		private static final String TYPE_XLSX = "xlsx";
		
		public static Workbook getWorkbook(InputStream in,String filename)throws IOException {
			Workbook wb = null;
			if(filename.endsWith(TYPE_XLS)){
					wb = new HSSFWorkbook(in);//Excel 2003
			}else if(filename.endsWith(TYPE_XLSX)){
					wb = new XSSFWorkbook(in);//Excel 2007
			}    
					return wb;
			}
		
		public static String getCellValue(Cell cell){
			String cellVal = "";
			
			switch (cell.getCellTypeEnum()) {  
	        case BLANK:  // 空值
	            cellVal = "";  
	            break;  
	        case BOOLEAN:  // 布尔
	            cellVal = String.valueOf(cell.getBooleanCellValue());  
	            break;  
	        case ERROR:  // 故障
	            cellVal = null;  
	            break;  
	        case FORMULA:  // 公式
	            Workbook wb = cell.getSheet().getWorkbook();  
	            CreationHelper crateHelper = wb.getCreationHelper();  
	            FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
	            cellVal = getCellValue(evaluator.evaluateInCell(cell));  
	            break;  
	        case NUMERIC:  // 数字
	            if (DateUtil.isCellDateFormatted(cell)) {  // 如果是时间格式
	            	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	                Date theDate = cell.getDateCellValue();  
	                cellVal = sdf.format(theDate);  
	            } else {   
	                cellVal = NumberToTextConverter.toText(cell.getNumericCellValue());  
	            }  
	            break;  
	        case STRING:  //字符串
	            cellVal = cell.getRichStringCellValue().getString();  
	            break;  
	        default:  
	            cellVal = null;  
	        }  
			return cellVal;
		}
}
