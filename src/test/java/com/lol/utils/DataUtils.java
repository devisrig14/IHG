package com.lol.utils;

import java.io.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lol.tests.TestExecutor;

public class DataUtils extends TestExecutor {
	int rowCount;
//	String sheetName = new String();
	
	public void setSheetName(String sheetName)
	{
//		this.sheetName = sheetName;
		sheet = book.getSheet(sheetName);
	}
	
	
	public DataUtils() throws IOException
	{
		File = new FileInputStream(userDir + "\\src\\test\\java\\test\\java\\input\\Data.xlsx");
		
		book = new XSSFWorkbook(File);
//		sheet = book.getSheet(this.sheetName);
	}	
	
	
	public void getRowData(int rowVal) throws IOException {
		try
		{
			rowCount = sheet.getLastRowNum() + 1;
			int colCount = sheet.getRow(0).getLastCellNum();
			Row firstRow = sheet.getRow(0);
			Row currRow = sheet.getRow(rowVal);
			for (int j = 0; j < colCount; j++) 
			{
				System.out.println(formatter.formatCellValue(firstRow.getCell(j)) + " = "+ formatter.formatCellValue(currRow.getCell(j)));
				map.put(formatter.formatCellValue(firstRow.getCell(j)), formatter.formatCellValue(currRow.getCell(j)));
			}
			book.close();
		}
		catch (NullPointerException e)
		{
			System.out.println("Row number entered exceeds the actual row count of excel data sheet");
		}		
	}
	public int noOfRows()
	{
		return sheet.getLastRowNum();
	}
	public String getMapValue(String mapData)
	{
		return map.get(mapData);
	}
}
