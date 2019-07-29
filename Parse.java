
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class  Parse{
	
	public static ArrayList<ToolOne> getToolOneData(NodeList nList) {
		ArrayList<ToolOne> buglist = new ArrayList<>();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			ToolOne f = new ToolOne();
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			f.setCategory(eElement.getAttribute("category"));
			f.setType(eElement.getAttribute("type"));
			Node classname = (Node) eElement.getElementsByTagName("Class").item(0);
			eElement = (Element) classname;
			f.setClassName(eElement.getAttribute("classname"));
			Node line = (Node) eElement.getElementsByTagName("SourceLine").item(0);
			eElement = (Element) line;
			f.setLine(eElement.getAttribute("start"));
			buglist.add(f);
		}
		return buglist;
	}

	public static ArrayList<ToolTwo> getToolTwoData(NodeList nList) {
		ArrayList<ToolTwo> buglist = new ArrayList<>();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				NodeList violations = eElement.getElementsByTagName("violation");
				for (int count = 0; count < violations.getLength(); count++) {
					ToolTwo f = new ToolTwo();
					Node violationNode = violations.item(count);
					eElement = (Element) violationNode;
					f.setClassName(eElement.getAttribute("class"));
					f.setCategory(eElement.getAttribute("ruleset"));
					f.setrule(eElement.getAttribute("rule"));
					f.setLine(eElement.getAttribute("beginline"));
					f.setContent(eElement.getTextContent());
					buglist.add(f);
				}
			}
		}
		return buglist;
	}

	public static void parseXml(String projectName, String analyserName) {
		try {
			File fXmlFile = new File(Commands.destinationPath + projectName + analyserName + ".xml");
			if (fXmlFile.exists()) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);

				doc.getDocumentElement().normalize();
				if (analyserName.compareToIgnoreCase("findbugs") == 0) {
					WriteData<ToolOne> bg = new WriteData<>();
					NodeList nList = doc.getElementsByTagName("BugInstance");
					ArrayList<ToolOne> buglist = getToolOneData(nList);
					bg.writeDataToExcel(buglist, projectName, analyserName);
				}

				else if (analyserName.compareToIgnoreCase("pmd") == 0) {
					WriteData<ToolTwo> bg = new WriteData<>();
					NodeList nList = doc.getElementsByTagName("file");

					ArrayList<ToolTwo> buglist = getToolTwoData(nList);

					bg.writeDataToExcel(buglist, projectName, analyserName);
				}
			} else {
				System.out.println(analyserName + " file is not created");
				System.out.println("The possible reasons are :\n");
				System.out.println("The user didn't install the " + analyserName + " in C: Drive.\n");
				System.out.println("The user may have installed the different version of " + analyserName
						+ " other than we provided in README file.\n");
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}
}