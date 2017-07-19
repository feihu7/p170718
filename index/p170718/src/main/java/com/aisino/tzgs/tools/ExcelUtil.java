package com.aisino.tzgs.tools;


public class ExcelUtil {
	
	/**
	 * 英文列号转数字
	 * @param colStr
	 * @param length
	 * @return
	 */
	 public static int excelColStrToNum(String colStr, int length) {
        int num = 0;
        int result = 0;
        for(int i = 0; i < length; i++) {
            char ch = colStr.charAt(length - i - 1);
            num = (int)(ch - 'A' + 1) ;
            num *= Math.pow(26, i);
            result += num;
        }
        return result;
    }
	 
	 public static int excelColStrToNum2(String colStr) {
	        int num = 0;
	        int result = 0;
	        for(int i = 0; i < colStr.length(); i++) {
	            char ch = colStr.charAt(colStr.length() - i - 1);
	            num = (int)(ch - 'A' + 1) ;
	            num *= Math.pow(26, i);
	            result += num;
	        }
	        return result;
	    }
	 
	 
	 /**
		 * 获取要汇总的单元格所在的行和列
		 * @param str=“AB20”
		 * @return
		 */
		public static int[] getRowAndCell(String str){
			String zm = str.split("\\d")[0]; // 字母
			String sz = str.substring(zm.length(), str.length()); // 行
			int row = Integer.valueOf(sz);
			int cell = ExcelUtil.excelColStrToNum2(zm);
			int[] rowCell = {row-1,cell-1};  // 读取excel时index默认从0开始  所以行和列的序号要减一
			return rowCell;
		}
	 
	 public static void main(String[] args) {
		 
		 System.out.println(excelColStrToNum("AAA",3));
		 System.out.println(excelColStrToNum2("AAA"));
		 System.out.println(excelColStrToNum2("HK"));
		 
		 String str = "AB20";
		 String zm = str.split("\\d")[0];
		 System.out.println(zm);
		 System.out.println(str.substring(zm.length(), str.length()));
		 
	}
}
