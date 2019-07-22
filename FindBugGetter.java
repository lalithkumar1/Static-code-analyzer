package com.philips.casestudy;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FindBugGetter<T> {

	public void getBugs(String projectName, String analyserName) {

		try {

			File fXmlFile = new File(
					"C:\\Users\\320066613\\CodeAnalysis\\XMLfiles\\" + projectName + "-" + analyserName + ".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			if (analyserName.compareToIgnoreCase("findbugs") == 0) {
				BugWriter<FindBugsData> bg = new BugWriter<FindBugsData>();
				ArrayList<FindBugsData> buglist = new ArrayList<FindBugsData>();

				NodeList nList = doc.getElementsByTagName("BugInstance");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
					FindBugsData f = new FindBugsData();
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					((FindBugsData) f).setCategory(eElement.getAttribute("category"));
					((FindBugsData) f).setType(eElement.getAttribute("type"));
					Node classname = (Node) eElement.getElementsByTagName("Class").item(0);
					eElement = (Element) classname;
					((FindBugsData) f).setClassName(eElement.getAttribute("classname"));
					buglist.add(f);
				}
			bg.writeData(buglist, projectName, analyserName);
			}

			else if (analyserName.compareToIgnoreCase("pmd") == 0) {
				BugWriter<PMDData> bg = new BugWriter<PMDData>();
				ArrayList<PMDData> buglist = new ArrayList<PMDData>();
				NodeList nList = doc.getElementsByTagName("file");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					PMDData f = new PMDData();
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						NodeList violations = eElement.getElementsByTagName("violation");
						for (int count = 0; count < violations.getLength(); count++) {
							Node violationNode = violations.item(count);
							eElement = (Element) violationNode;
							((PMDData) f).setClassName(eElement.getAttribute("class"));
							((PMDData) f).setIssue(eElement.getAttribute("rule"));
							((PMDData) f).setData(eElement.getTextContent());
							buglist.add(f);
						}
					}
				}
				bg.writeData(buglist, projectName, analyserName);
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}
}
