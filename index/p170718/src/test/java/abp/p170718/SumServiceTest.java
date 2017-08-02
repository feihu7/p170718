package abp.p170718;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import abp.p170718.CellAddress;
import abp.p170718.SumRequest;
import abp.p170718.SumResult;
import abp.p170718.SumService;

public class SumServiceTest extends Assert{
private SumRequest sumRequest;
	
	@Before
	public void setUp() throws Exception {
		URL resource1 = SumServiceTest.class.getResource("1.xlsx");
		URL resource2 = SumServiceTest.class.getResource("2.xlsx");
		URL resource3 = SumServiceTest.class.getResource("3.xlsx");
		URL resource4 = SumServiceTest.class.getResource("4.xlsx");
		
		File file1 = new File(resource1.toURI());
		File file2 = new File(resource2.toURI());
		File file3 = new File(resource3.toURI());
		File file4 = new File(resource4.toURI());
		List<File> excelFiles = new ArrayList<File>();
		excelFiles.add(file1);
		excelFiles.add(file2);
		excelFiles.add(file3);
		excelFiles.add(file4);
		
		CellAddress cds1 = new CellAddress("sheet1", 18, 2);   // sheet1.B18"
		CellAddress cds2 = new CellAddress("sheet1", 18, 6);   // sheet1.F18
		List<CellAddress> addresses = new ArrayList<CellAddress>();
		addresses.add(cds1);
		addresses.add(cds2);
		
		sumRequest = new SumRequest();
		sumRequest.setExcelFiles(excelFiles);
		sumRequest.setAddresses(addresses);
	}

	@Test
	public void testDataCount() {
		CellAddress cds1 = new CellAddress("sheet1", 18, 2);   // sheet1.B18"
		SumService ss = new SumService();
		SumResult sr = ss.dataCount(sumRequest);
//		String jsonRest = "{\"sheet1.B18\":{\"sum\":\"87.743\",\"avg\":\"21.936\",\"max\":\"24.507\",\"min\":\"18.5\"},\"sheet1.F18\":{\"sum\":\"11.0\",\"avg\":\"2.75\",\"max\":\"4.0\",\"min\":\"2.0\"}}";
		BigDecimal sum = sr.getMap().get(cds1).getSum();
		assertEquals("87.743", sum.toString());
		//fail("Not yet implemented");
	}
}
