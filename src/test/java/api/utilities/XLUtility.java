package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public XLUtility(String path)
	{
		this.path=path;
	}

	public int getRowCount(String sheetName)
	{
		try {
			fi=new FileInputStream(path);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		try {
			workbook=new XSSFWorkbook(fi);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		sheet=workbook.getSheet(sheetName);
		int rowcount =sheet.getLastRowNum();
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  try {
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  return rowcount;

	}

	public int getCellCount(String sheetName,int rownum) throws Exception
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;


	}


	public void setCellData(String sheetName,int rownm,int colnm,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())        //if file is not exist then create new file
		{
			workbook=new XSSFWorkbook(fi);
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}

		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);

		if(workbook.getSheetIndex(sheetName)==-1)  //if sheet not exist then create new sheet
		{
			workbook.createSheet(sheetName);
			sheet=workbook.getSheet(sheetName);
		}

		if(sheet.getRow(rownm)==null)   //if row is not exist then create new row
		{
			sheet.createRow(rownm);
			row=sheet.getRow(rownm);


			cell=row.createCell(colnm);
			cell.setCellValue(data);
			fo=new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		}



	}



	public String getCelldata(String sheetName,int rownm,int colnm) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownm);
		cell=row.getCell(colnm);

		DataFormatter formatter=new DataFormatter();
		String data;

		try {
			data=formatter.formatCellValue(cell);  //returns formatted cell value of a cell as a string
		}
		catch(Exception e)
		{
			data=" ";
		}
		workbook.close();
		fi.close();
		return data;
	}

}
