package com.ProjectOne.ProjectOne.Utilities;

import java.io.*;
import java.util.*;

import org.apache.poi.xssf.usermodel.*;

public class ExcelFactory {
	//Excel file objects
	private static XSSFWorkbook workBook = null;
	private static XSSFSheet workSheet = null;
	private static String SysPath = System.getProperty("user.dir") + "//DataSource//";;
	private static Map<String, String> TestDataMap = new HashMap<String, String>();
	
	public void getWorkbook(String filePath){
		filePath = SysPath + filePath;
		File excelfile = new File(filePath);
		if(excelfile.exists()){
			try {
				workBook = new XSSFWorkbook(new FileInputStream(excelfile));
			} catch (Exception e) {
				//Need to log the error message
				System.out.println("Excel Data file is not available at the mentioned location.");
				e.printStackTrace();
			}
		}	
	}
	
	public void getSheet(String sheetName) throws Exception{
		if(workBook ==  null){
			throw new Exception("Workbook is not available at the provided location");
		}else{
			if (workBook.getNumberOfSheets() != 0) {
		        for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
		           if (workBook.getSheetName(i).equals(sheetName)) {
		                workSheet = workBook.getSheet(sheetName);
		                break;
		           }
		        }
		    }
		}
	}
	
	public String getCellValue(int row, int col) throws Exception{
		String cellValue = null;
		if(workSheet == null){
			throw new Exception("Sheet is not available in the provided WorkBook");
		}else{
			try{
				XSSFRow rowNumber = workSheet.getRow(row);
				cellValue = rowNumber.getCell(col).toString();
			}catch(Exception e){
				System.out.println("Requested Cell number may have some issue. Please check provided row and column count");
				e.printStackTrace();
			}			
		}
		return cellValue;
	}
	
	public void getTestDataLoaded(XSSFSheet sheetName,String TestIdentifier){
		int DataRow = 0;
		int rowCount = workSheet.getLastRowNum();
		int colCount = workSheet.getRow(rowCount).getLastCellNum();
		try{
			for(int row = 1; row <= rowCount; row++){
				if(getCellValue(row, 1).equals(TestIdentifier)){
					DataRow = row;
					break;
				}  
			}
			for(int col = 1; col <= colCount; col++){
				TestDataMap.put(getCellValue(1,col), getCellValue(DataRow,col));
			}
		}catch(Exception e){
			System.out.println("Facing some error while loading test data for the test script.");
			e.printStackTrace();
		}
		
	}
	
	public String getValueOf(String keyValue) throws Exception{
		String value = "";
		if(TestDataMap.containsKey(keyValue)){
			value = TestDataMap.get(keyValue);
		}else{
			throw new Exception("Column is not present for the selected sheet.");
		}
		return value;
	}
}
