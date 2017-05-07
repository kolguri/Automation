package com.yahoo.uiAutomation.excelReader;

import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yahoo.uiAutomation.testBase.TestBase;

public class Excel_Reader {
	public static final Logger log = Logger.getLogger(Excel_Reader.class.getName());
    public FileInputStream fis;
    public String path;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    
	public Excel_Reader(String path){
		this.path=path;
		try {
			
		    fis = new FileInputStream(path);
			workbook= new XSSFWorkbook(fis);
			log.info("=========In excel reader constructor=======" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public String[][]getDataFromSheet(String sheetName,String excelName)
	{
		String datasets[][]=null;
		try{
		sheet=workbook.getSheet(sheetName);
		int totalRows=sheet.getLastRowNum() +1 ;
		int totalColumns=sheet.getRow(0).getLastCellNum();
		datasets=new String[totalRows-1][totalColumns];
		for(int i=1;i<totalRows;i++){
			row=sheet.getRow(i);
			for(int j=0;j<totalColumns;j++){
				cell=row.getCell(j);
				if(cell.getCellType()==cell.CELL_TYPE_STRING)
					datasets[i-1][j]=cell.getStringCellValue();
				else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
					datasets[i-1][j]=String.valueOf(cell.getNumericCellValue());
				else
					datasets[i-1][j]=String.valueOf(cell.getBooleanCellValue());
			}
		}
					
		return datasets;	
		}
		catch(Exception e){
		System.out.println("Error in reading the excel file"+e.getMessage());
		e.printStackTrace();
		}
		return datasets;
	}
	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, int rownum, String columnName)
	{
		try{
			int col_Num=0;
			int index= workbook.getSheetIndex(sheetName);
			sheet =workbook.getSheetAt(index);
			XSSFRow row=sheet.getRow(0); 
			for(int i=0;i<row.getLastCellNum();i++)
			{
				if(row.getCell(i).getStringCellValue().equals(columnName)){
					col_Num=i;
					break;
				}
			}
			row=sheet.getRow(rownum-1);
			XSSFCell cell=row.getCell(col_Num);
			if(row.getCell(col_Num).getCellType()==Cell.CELL_TYPE_STRING){
				return cell.getStringCellValue();
			}
			else if(row.getCell(col_Num).getCellType()==Cell.CELL_TYPE_BLANK){
				return "";
			}
		}catch(Exception e){
			System.out.println("Error in reading the excel file"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}
