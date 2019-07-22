package com.philips.casestudy;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class StaticToolBugs<T> implements Bugs<T>{

	static String path=Commands.destinationPath;
	static XSSFWorkbook workbook = new XSSFWorkbook();
	
	ArrayList<T> buglist = new ArrayList<>();
	@Override
	public void getBugs(String projectName, String analyserName) {
		try {

			File fXmlFile = new File(
					"C:\\Users\\320065410\\OneDrive - Philips\\Desktop\\Reports\\" + projectName + analyserName+".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			if (analyserName.compareToIgnoreCase("findbugs") == 0) {

				NodeList nList = doc.getElementsByTagName("BugInstance");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
					FindBugsData f = new FindBugsData();
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					f.setCategory(eElement.getAttribute("category"));
					f.setType(eElement.getAttribute("type"));
					Node classname = (Node) eElement.getElementsByTagName("Class").item(0);
					eElement = (Element) classname;
					f.setClassName(eElement.getAttribute("classname"));
					buglist.add((T) f);
					
				}
			}

			else if (analyserName.compareToIgnoreCase("pmd") == 0) {
				NodeList nList = doc.getElementsByTagName("file");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					PmdData f = new PmdData();
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						NodeList violations = eElement.getElementsByTagName("violation");
						for (int count = 0; count < violations.getLength(); count++) {
							Node violationNode = violations.item(count);
							eElement = (Element) violationNode;
							f.setClassName(eElement.getAttribute("class"));
							f.setruleSet(eElement.getAttribute("ruleset"));
							f.setrule(eElement.getAttribute("rule"));
							f.setLine(eElement.getAttribute("beginline"));
							f.setContent(eElement.getTextContent());
							buglist.add((T) f);
							
						}
					}
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		
	}

	@Override
	public void writeData(String Classname, String AnalysisType) {
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
							new File(path + Classname+ ".xlsx"));
					workbook.write(out);
					out.close();}
		      else if(((ArrayList<?>)buglist).get(0) instanceof PmdData)
					{
		        	XSSFSheet sheet = workbook.createSheet("PMD");
					Map<Integer, Object[]> data = new TreeMap<Integer,Object[]>();
					data.put(1, new Object[] { "Class Name", "Error Category", "Error Type","Error" });
					int i = 2;
					for (T f : buglist) {
						data.put(i++, new Object[] { ((PmdData) f).getClassName(), ((PmdData) f).getLine(), ((PmdData) f).getruleSet(),((PmdData) f).getContent() });
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
							new File(path + Classname+ ".xlsx"));
					workbook.write(out);
					out.close();
		    	  
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}

}
