package abp.p170718;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import abp.p170718.util.ExcelRead;

public class ExcelReadTest extends Assert {

	@Test
	public void testGetCellValue() throws Exception {
		  URL resource = ExcelReadTest.class.getResource("excelread.xlsx");
     File file = new File(resource.toURI());
     InputStream inp = new FileInputStream(file);
     Workbook wb = WorkbookFactory.create(inp);
     Sheet sheet = wb.getSheet("sheet1");  // 根据sheet名称获取sheet
		  Row row = sheet.getRow(4);
		  Cell cell = row.getCell(1);
		  assertTrue(cell.getCellTypeEnum() == CellType.FORMULA);
		  String cellStr = ExcelRead.getCellValue(cell);
		  assertEquals("3", cellStr);
	}

}
