import java.util.List;

public class Main {

    public static void main(String[] args) {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String fileNameCSV = "CSV data.csv";
        String pathCSV = "CSV new_data.json";

        String fileNameXML = "XML data.xml";
        String pathXML = "XML new data.json";


        //CSV -> JSON

        ProjectCSVParser projectCSVParser = new ProjectCSVParser();
        List<Employee> listCSV = projectCSVParser.parseCSV(columnMapping, fileNameCSV);
        String jsonFromCSV = projectCSVParser.listToJson(listCSV);
        projectCSVParser.writeJson(jsonFromCSV, pathCSV);


        //XML -> JSON

        ProjectXMLParser projectXMLParser = new ProjectXMLParser();
        List<Employee> listXML = projectXMLParser.parseXML(fileNameXML);
        String jsonFromXML = projectXMLParser.listToJson(listXML);
        projectXMLParser.writeJson(jsonFromXML, pathXML);

        //JSON -> Employee

        ProjectJSONParser projectJSONParser = new ProjectJSONParser();
        String temp = projectJSONParser.readString(pathXML);
        List<Employee> listJSON = projectJSONParser.jsonToList(temp);
    }

}