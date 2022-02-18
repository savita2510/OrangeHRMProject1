package com.framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {
	
	//This class will be used to maintain all methods related to excel (reading test data from excel)

	public static String [][] readData(String workbook, String sheet){
		
		String [][] data = null;
		
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+workbook); //to read file
			XSSFWorkbook wb = new XSSFWorkbook(fis);// to let java understand that file is excel
			XSSFSheet sh = wb.getSheet(sheet);  // to read the data from sheet
			XSSFRow rw = sh.getRow(0); //to specify which row
			int totalrows = sh.getPhysicalNumberOfRows();
			int totalcols = rw.getPhysicalNumberOfCells();
			
			data = new String[totalrows-1][totalcols];
			
			for(int r=1; r<totalrows;r++) {
				for(int c=0; c<totalcols;c++) {
					Cell cell = sh.getRow(r).getCell(c);
					data[r-1][c] = cell.getStringCellValue();
				}
			}	
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return data;
		
	}
}
