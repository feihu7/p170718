package com.aisino.tzgs.domain;

/**
 * 接收输入信息
 * @author fanpf
 *
 */
public class ExcelInfo {
		private String[] address;  // excel文件路径 
		private String[] sheetMes; // 要汇总的单元格信息 [sheet1,row1]
		
		public String[] getAddress() {
			return address;
		}
		public void setAddress(String[] address) {
			this.address = address;
		}
		public String[] getSheetMes() {
			return sheetMes;
		}
		public void setSheetMes(String[] sheetMes) {
			this.sheetMes = sheetMes;
		}
		
		public ExcelInfo(String[] address,String[] sheetMes) {
			 this.address = address;
			 this.sheetMes = sheetMes;
		}
}
