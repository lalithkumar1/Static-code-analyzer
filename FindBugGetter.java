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
	
}
