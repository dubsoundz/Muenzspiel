package standard.eclipse.muenzspiel;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import org.w3c.dom.NodeList;

public class ReadXMLFile {
	
	JDialog cMSg;
	
	
	
	public ReadXMLFile() {
		// TODO Auto-generated constructor stub
		cMSg = new JDialog();
	}
	
	
	
	 public void readXML() {

		    try {

		    	String workingDirectory = System.getProperty("user.Dir");
		    //	String filepath = workingDirectory + "values/strings.xml";
		    //	System.out.println(filepath);
			File fXmlFile = new File("values/strings.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("ComputerMessages");
					
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Introduction : " + eElement.getElementsByTagName("Introduction").item(0).getTextContent());
					JOptionPane.showMessageDialog(null, eElement.getElementsByTagName("Introduction").item(0).getTextContent());
					

				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  }



	
}
