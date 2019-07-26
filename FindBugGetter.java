
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class FindBugGetter {

	public static void getBugs(String projectName, String analyserName) {

		try {
			File fXmlFile = new File(Commands.destinationPath+ projectName + analyserName+".xml");
			if(fXmlFile.exists()) {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
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
					Node line=(Node) eElement.getElementsByTagName("SourceLine").item(0);
					eElement=(Element) line;
					((FindBugsData) f).setLine(eElement.getAttribute("start"));
					buglist.add(f);
				}
				bg.writeData(buglist, projectName, analyserName);
			}

			else if (analyserName.compareToIgnoreCase("pmd") == 0) {
				BugWriter<PmdData> bg = new BugWriter<PmdData>();
				ArrayList<PmdData> buglist = new ArrayList<PmdData>();
				NodeList nList = doc.getElementsByTagName("file");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						NodeList violations = eElement.getElementsByTagName("violation");
						//System.out.println(violations.getLength());
						for (int count = 0; count < violations.getLength(); count++) {
							PmdData f = new PmdData();
							Node violationNode = violations.item(count);
							eElement = (Element) violationNode;
							f.setClassName(eElement.getAttribute("class"));
							f.setruleSet(eElement.getAttribute("ruleset"));
							f.setrule(eElement.getAttribute("rule"));
							f.setLine(eElement.getAttribute("beginline"));
							f.setContent(eElement.getTextContent());
							//System.out.println(eElement.getTextContent());
							buglist.add(f);
						}
					}
				}
				bg.writeData(buglist, projectName, analyserName);
			}
			}
			else {
				System.out.println(analyserName+" file is not created");
				System.out.println("The possible reasons are :\n");
				System.out.println("The user didn't install the "+analyserName+" in C: Drive.\n");
				System.out.println("The user may have installed the different version of "+analyserName+" other than we provided in README file.\n");
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}
}