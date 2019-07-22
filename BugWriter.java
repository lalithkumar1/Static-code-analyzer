package com.philips.casestudy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BugWriter<T> {
	static String path="C:\\Users\\320066613\\CodeAnalysis\\Reports\\";
	static XSSFWorkbook workbook = new XSSFWorkbook();
	public  void writeData(ArrayList<T> buglist, String Classname, String AnalysisType) throws Exception {
		try {
				if(((ArrayList<?>)buglist).get(0) instanceof FindBugsData)
			        {
			        	XSSFSheet sheet = workbook.createSheet("Findbugs");
						Map<Integer, Object[]> data = new TreeMap<Integer,Object[]>();
						data.put(1, new Object[] { "Class Name", "Error Category", "Error Type" });
						int i = 2;
						
						for (T f : buglist) {
							data.put(i++, new Object[] { ((FindBugsData) f).getClassName(), ((FindBugsData) f).getCategory(), ((FindBugsData) f).getType() });
						}
						
						Set<Integer> keyset = data.keySet();
						int rownum = 0;
						for (Integer key : keyset) {
							Row row = sheet.createRow(rownum++);
							Object[] objArr = data.get(key);
							int cellnum = 0;
							for (Object obj : objArr) {
								Cell cell = row.createCell(cellnum++);
								sheet.setColumnWidth(cellnum - 1, 15000);
								cell.setCellValue((String) obj);
							}
						}

						FileOutputStream out = new FileOutputStream(
								new File(path + Classname+ "-findbugs.xlsx"));
						workbook.write(out);
						out.close();}
			      else if(((ArrayList<?>)buglist).get(0) instanceof PMDData)
						{
			        	XSSFSheet sheet = workbook.createSheet("PMD");
						Map<Integer, Object[]> data = new TreeMap<Integer,Object[]>();
						data.put(1, new Object[] { "Class Name", "Error Category", "Error Type" });
						int i = 2;
						for (T f : buglist) {
							data.put(i++, new Object[] { ((PMDData) f).getClassName(), ((PMDData) f).getData(), ((PMDData) f).getIssue() });
						}

						Set<Integer> keyset = data.keySet();
						int rownum = 0;
						for (Integer key : keyset) {
							Row row = sheet.createRow(rownum++);
							Object[] objArr = data.get(key);
							int cellnum = 0;
							for (Object obj : objArr) {
								Cell cell = row.createCell(cellnum++);
								sheet.setColumnWidth(cellnum - 1, 15000);
								cell.setCellValue((String) obj);
							}
						}

						FileOutputStream out = new FileOutputStream(
								new File(path + Classname+ "-pmd.xlsx"));
						workbook.write(out);
						out.close();
			    	  
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
