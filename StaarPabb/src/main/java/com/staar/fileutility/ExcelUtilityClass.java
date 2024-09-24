package com.staar.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author Ashutosh
 * contains methods for setting and getting data in/from excel
 */
public class ExcelUtilityClass {
	/**
	 * used to get data from excel file
	 * @author Ashutosh
	 * @param sheet
	 * @param row
	 * @param column 
	 * @return
	 * @throws Throwable
	 */
	public String getExcelData(String sheet,int row, int column) throws Throwable {
		FileInputStream fis=new FileInputStream("./testdata/testcaseData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheet).getRow(row).getCell(column).getStringCellValue();
		wb.close();
		return data;
	}
	/**
	 * used to set data into excel sheet
	 * @author Ashutosh
	 * @param sheet
	 * @param row
	 * @param column
	 * @param data
	 * @throws Throwable
	 */
	public void setdDataInExcel(String sheet,int row, int column,String data) throws Throwable {
		FileInputStream fis=new FileInputStream("./testdata/testcaseData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheet).getRow(row).getCell(column).setCellValue(data);
		FileOutputStream fos=new FileOutputStream("/.testData/");
		wb.write(fos);
		wb.close();
	}
	/**
	 * used to get the number of rows in the given sheet
	 * @author Ashutosh
	 * @param sheet
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheet) throws Throwable {
		FileInputStream fis=new FileInputStream("./ śśtestdata/testcaseData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowNum= wb.getSheet(sheet).getLastRowNum();
		wb.close();
		return rowNum;
	}
}
