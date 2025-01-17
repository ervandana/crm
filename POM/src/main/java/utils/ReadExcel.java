package utils;


	
	import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



	public class ReadExcel {
		
		public static Object[][] readXcel(String dataSheetName) throws IOException {
		//select the excel file
		XSSFWorkbook wb = new XSSFWorkbook("./data/"+dataSheetName+".xlsx");
		//goto sheet
		XSSFSheet sheet = wb.getSheetAt(0);
		//goto rows                                           //XSSFRow row1 = sheet.getRow(1);
		                                            //to get no.of cells in a row
		int rowNum = sheet.getLastRowNum();
		System.out.println(rowNum);
		int cellNum = sheet.getRow(0).getLastCellNum();
		System.out.println(cellNum);
		//create array
		Object[][] data = new Object[rowNum][cellNum];
		
	    for (int j = 1; j <=rowNum; j++) {
	    	XSSFRow row = sheet.getRow(j);
	 
			//goto cell
			try {
				for (int i = 0; i < cellNum; i++) {
					XSSFCell cell = row.getCell(i);
					String value = cell.getStringCellValue();
					System.out.println(value);
					data[j-1][i]=value;	
				}
			} catch (NullPointerException e) {
				System.out.println("");
			} 
		}
	    //close Excel
	    wb.close();
		return data;
	     
		
	}
	}
