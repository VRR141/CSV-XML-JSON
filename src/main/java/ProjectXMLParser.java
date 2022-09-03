import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectXMLParser extends ProjectParser {

    private List<Long> idEmployeeValues = new ArrayList<>();
    private List<String> firstNameEmployeeValues = new ArrayList<>();
    private List<String> lastNameEmployeeValues = new ArrayList<>();
    private List<String> countryEmployeeValues = new ArrayList<>();
    private List<Integer> ageEmployeeValues = new ArrayList<>();


    public List<Employee> parseXML(String path){
        innerParseXML(path);
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i <idEmployeeValues.size(); i++){
            Employee temp = new Employee(
                    idEmployeeValues.get(i),
                    firstNameEmployeeValues.get(i),
                    lastNameEmployeeValues.get(i),
                    countryEmployeeValues.get(i),
                    ageEmployeeValues.get(i)
            );
            list.add(temp);
        }
        return list;
    }

    private void innerParseXML(String path) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            Node root = doc.getDocumentElement();
            System.out.println("\nXML -> JSON");
            System.out.println("Корневой элемент: " + root.getNodeName() + "\n");
            readXML(root);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private void readXML(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                //System.out.println("Текущий узел: " + node_.getNodeName());
                Element element = (Element) node_;
                String switcher = element.getNodeName();
                var value = element.getTextContent();
                switch (switcher){
                    case "id":
                        Long tempLong = Long.parseLong(value.replaceAll("\"", ""));
                        idEmployeeValues.add(tempLong);
                        break;
                    case "firstName":
                        firstNameEmployeeValues.add(value.replaceAll("\"", ""));
                        break;
                    case "lastName":
                        lastNameEmployeeValues.add(value.replaceAll("\"", ""));
                        break;
                    case "country":
                        countryEmployeeValues.add(value.replaceAll("\"", ""));
                        break;
                    case "age":
                        var tempInt = Integer.parseInt(value.replaceAll("\"", ""));
                        ageEmployeeValues.add(tempInt);
                        break;
                    default:
                }
                readXML(node_);
            }
        }
    }

    private void printLists(){
        System.out.println("idEmployeeValues");
        for (Long l : idEmployeeValues) {
            System.out.println(l);
        }
        System.out.println("firstNameEmployeeValues");
        for (String s : firstNameEmployeeValues) {
            System.out.println(s);
        }        System.out.println("lastNameEmployeeValues");
        for (String l : lastNameEmployeeValues) {
            System.out.println(l);
        }        System.out.println("countryEmployeeValues");
        for (String l : countryEmployeeValues) {
            System.out.println(l);
        }        System.out.println("ageEmployeeValues");
        for (Integer l : ageEmployeeValues) {
            System.out.println(l);
        }

    }
}
