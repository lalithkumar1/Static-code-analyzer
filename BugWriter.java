package com.philips.casestudy;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BugWriter {
	static XSSFWorkbook workbook = new XSSFWorkbook();
	public static void writeFindBugsData(ArrayList<FindBugsData> buglist, String Classname, String name)
			throws Exception {
		try {
			
			XSSFSheet sheet = workbook.createSheet("Findbugs");
			Map<Integer, Object[]> data = new TreeMap<>();
			data.put(1, new Object[] { "Class Name", "Error Category", "Error Type" });
			int i = 2;
			for (FindBugsData f : buglist) {
				data.put(i++, new Object[] { f.getClassName(), f.getCategory(), f.getType() });
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
					new File("C:\\Users\\320065410\\OneDrive - Philips\\Desktop\\Reports\\finalreport\\" + Classname+ ".xlsx"));
			workbook.write(out);
			out.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writePmdData(ArrayList<PmdData> buglist, String Classname, String name) throws Exception {
		try {

			XSSFSheet sheet = workbook.createSheet("PMD");
			Map<Integer, Object[]> data = new TreeMap<>();
			data.put(1, new Object[] { "Class Name","Line Number", "RuleSet", "Error" });
			int i = 2;
			for (PmdData f : buglist) {
				data.put(i++, new Object[] { f.getClassName(),f.getLine(), f.getruleSet(), f.getContent() });
			}

			Set<Integer> keyset = data.keySet();
			int rownum = 0;
			for (Integer key : keyset) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					sheet.setColumnWidth(cellnum - 1, 10000);
					cell.setCellValue((String) obj);
				}
			}

			FileOutputStream out = new FileOutputStream(
					new File("C:\\Users\\320065410\\OneDrive - Philips\\Desktop\\Reports\\finalreport\\" + Classname+".xlsx"));
			workbook.write(out);
			out.close();
			workbook.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
