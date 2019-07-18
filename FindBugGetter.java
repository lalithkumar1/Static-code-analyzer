package com.philips.casestudy;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FindBugGetter {
	static ArrayList<FindBugsData> buglist = new ArrayList<FindBugsData>();
	static ArrayList<PMDData> pmdList=new ArrayList<PMDData>();

	public static ArrayList<FindBugsData> getBugs(String projectName) {
		try {
			FindBugsData f;
			File fXmlFile = new File(
					"C:\\Users\\320066613\\findbugs-3.0.1\\bin\\" + projectName + ".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("BugInstance");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				f = new FindBugsData();
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					f.setCategory(eElement.getAttribute("category"));
					f.setType(eElement.getAttribute("type"));
					Node classname=(Node)eElement.getElementsByTagName("Class").item(0);
					eElement=(Element) classname;
					f.setClassName(eElement.getAttribute("classname"));
					buglist.add(f);
					
				}
			}
			
		}

		catch (Exception e) {
			e.printStackTrace();
			
		}
		return buglist;
	}
	
	
	public static ArrayList<PMDData> getPMD(String projectName) {
		try {
			PMDData p;
			File fXmlFile = new File(
					"C:\\Users\\320066613\\CodeAnalysis\\XMLfiles\\" + projectName + ".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("file");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				p = new PMDData();
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					NodeList violations=eElement.getElementsByTagName("violation");
					for(int count=0;count<violations.getLength();count++)
					{	Node violationNode=violations.item(count);
						eElement=(Element) violationNode;
						p.setClassName(eElement.getAttribute("class"));
						p.setIssue(eElement.getAttribute("rule"));
						p.setData(eElement.getTextContent());
					pmdList.add(p);
					}
				}
			}
			
		}

		catch (Exception e) {
			e.printStackTrace();
			
		}
		return pmdList;
	}
}
