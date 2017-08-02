package abp.p170718;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import abp.p170718.util.ExcelRead;

/**
 * 处理Excel汇总请求并返回处理结果
 * @author fanpf
 *
 */
public class SumService {
	protected Logger logger =  Logger.getLogger(SumService.class);

	public SumResult dataCount(SumRequest sumRequest){
		 SumResult sumResult = new SumResult();
		 Map<CellAddress, CellSum> map = new LinkedHashMap<CellAddress, CellSum>();
		 
			List<File> file = sumRequest.getExcelFiles();             // 文件路径
			List<CellAddress> addresses = sumRequest.getAddresses();  // 单元格位置信息
			logger.info("======接收传入的Excel文件信息======");
			logger.info("======Excel文件路径 ： ["+StringUtils.join(file," , ")+"]");
			logger.info("======要汇总的sheet和cell位置信息： ["+StringUtils.join(addresses," , ")+"]");
			Workbook wb = null;
			InputStream inp = null;
			int[] rowAndCell = new int[2]; // 单元格所在的行和列值 [row , cell]
			try {
			// 读取文件
				for (int i = 0; i < addresses.size(); i++) {
					CellSum cellsum = new CellSum();
					CellAddress cellads = addresses.get(i); // 获取要汇总的sheet和单元格的信息
					String sheetName = cellads.getSheetName();
					int rowIndex = cellads.getRowIndex();
					int colIndex = cellads.getColumnIndex();
					
					rowAndCell[0] = rowIndex-1;
					rowAndCell[1] = colIndex-1;
					 if(sheetName!=null && !"".equals(sheetName)){
									for (int j = 0; j < file.size(); j++) {
										inp = new FileInputStream(file.get(j));
										wb = WorkbookFactory.create(inp);
										Sheet sheet = wb.getSheet(sheetName);  // 根据sheet名称获取sheet
									 
									  Row row = sheet.getRow(rowAndCell[0]);
									  Cell cell = row.getCell(rowAndCell[1]);
									  String cellStr = ExcelRead.getCellValue(cell);
									  BigDecimal cellVal = BigDecimal.valueOf(Double.valueOf(cellStr));  
									  cellsum.add(cellVal);          // 将单元格数据求和
									}
									map.put(cellads, cellsum);
									sumResult.setMap(map);
							}else{
								logger.error("sheetName为空，获取不到要汇总的sheet和单元格的信息");
								sumResult.setErrorMessage("sheetName为空");
								break;
							}
					 }
				 logger.info("======汇总输出返回结果： ["+StringUtils.join(sumResult," , ")+"]");
			} catch (Exception e) {
				logger.error("系统异常： "+e.getMessage());
				sumResult.setErrorMessage("系统异常 ： "+e.getMessage());
			}
		return sumResult;
	}
}
