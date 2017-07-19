package com.aisino.tzgs.domain;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.aisino.tzgs.tools.ExcelRead;
import com.aisino.tzgs.tools.ExcelUtil;

/**
 * 处理接收信息并返回JSON串
 * @author fanpf
 *
 */
public class DataCount {
	
	protected Logger logger =  Logger.getLogger(DataCount.class);

	public String dataCount(ExcelInfo excelInfo){
		 DecimalFormat df = new DecimalFormat("#.000"); // 保留3位小数
		 String jsonRet = "";   																// 要返回的json字符串
			String[] paths = excelInfo.getAddress();  		 // 文件路径
			String[] xlsxMes = excelInfo.getSheetMes(); 	 // sheet和单元格信息 [sheet1.H8,sheet1.H15,sheet2.M2,sheet2.P10]
			logger.info("======接收传入的Excel文件信息======");
			logger.info("======Excel文件路径 ： ["+StringUtils.join(paths," , ")+"]");
			logger.info("======要汇总的sheet和cell位置信息： ["+StringUtils.join(xlsxMes," , ")+"]");
			Workbook wb = null;
			InputStream inp = null;
//			int sheetCount = 0;
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			
			String sheetStr = "";
			int[] rowAndCell = null; // 单元格所在的行和列值 [row , cell]
			try {
			// 读取文件
				for (int i = 0; i < xlsxMes.length; i++) {
					 sheetStr = xlsxMes[i]; // 获取要汇总的sheet和单元格的信息
					 sb.append("\""+sheetStr+"\":{");
					 String s[] = sheetStr.split("\\.");
					 if(s!=null && s.length==2){
								 String sheetName = s[0]; // sheet信息
								 String colStr = s[1]; // 单元格位置信息     AB20
								 rowAndCell = ExcelUtil.getRowAndCell(colStr);  // 获取要汇总的单元格所在的行和列
								 double sum=0;  // 汇总值
								 double avg=0;  // 平均值
								 double min=0;  // 最小值
								 double max=0;  // 最大值
								 double[] dataArray = new double[paths.length];
									for (int j = 0; j < paths.length; j++) {
										inp = new FileInputStream(paths[j]);
										wb = WorkbookFactory.create(inp);
//										sheetCount = wb.getNumberOfSheets();  //Sheet的数量  
										//Sheet sheet = wb.getSheetAt(0);
										Sheet sheet = wb.getSheet(sheetName);  // 根据sheet名称获取sheet
									 
									  Row row = sheet.getRow(rowAndCell[0]);
									  Cell cell = row.getCell(rowAndCell[1]);
									  String cellStr = ExcelRead.getCellValue(cell);
									  double cellVal = Double.valueOf(cellStr);
									  dataArray[j]=cellVal;
									  sum += cellVal;  // 将单元格数据求和
									}
									Arrays.sort(dataArray);  // 对数组升序排序
									sum = Double.parseDouble(df.format(sum));
									avg = Double.parseDouble(df.format(sum/paths.length));
									max = Double.parseDouble(df.format(dataArray[dataArray.length-1]));
									min = Double.parseDouble(df.format(dataArray[0]));
									
									sb.append("\"sum\":\""+sum+"\",");
									sb.append("\"avg\":\""+avg+"\",");
									sb.append("\"max\":\""+max+"\",");
									sb.append("\"min\":\""+min+"\"},");
									System.out.println(sheetStr+"的汇总值  ： "+sum);
							}else{
								logger.error("获取不到要汇总的sheet和单元格的信息或者填写格式不正确，正确格式【sheet1.B20】");
							}
					 		 jsonRet = sb.toString().substring(0, sb.length()-1)+"}"; // 拼接输出JSON串
							 logger.info("======汇总成功，输出的JSON： "+jsonRet);
					 }
					 
			} catch (Exception e) {
				logger.error("系统异常： "+e.getMessage());
				e.printStackTrace();
			}
		return jsonRet;
	}
	
	public static void main(String[] args) throws Exception {
			String[] paths = {"/home/fanpf/Users/1.xlsx","/home/fanpf/Users/2.xlsx","/home/fanpf/Users/3.xlsx","/home/fanpf/Users/4.xlsx"};
			String[] xlsxMes = {"sheet1.B18","sheet1.F18"};
			ExcelInfo info = new ExcelInfo(paths,xlsxMes);
			DataCount dc = new DataCount();
			dc.dataCount(info);
		
//				File f = new File("/home/fanpf/Users/1.xlsx");
//				System.out.println("f.exists()  =="+f.exists());
//		   InputStream is = new FileInputStream("/home/fanpf/Users/1.xlsx");
//		   Workbook wb = WorkbookFactory.create(is);
//		   Sheet sheet = wb.getSheetAt(0);
//		   System.out.println("行数 ： "+sheet.getPhysicalNumberOfRows());
//		   Row row = sheet.getRow(2);
//			 Cell cell = row.getCell(1);
//			 System.out.println(cell.getCellType());
//			 System.out.println(cell.getNumericCellValue());
			
//		String json = "{\"sheet1.B18\":{\"sum\":\"1\",\"avg\":\"1\"}}";
//		System.out.println(json);
			
	}
}
