import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        String path = "new_data.json";

        List<Employee> list = parseCSV(columnMapping, fileName);

        String json = listToJson(list);

        writeJson(json, path);

    }

    public static List<Employee> parseCSV(String[] columnMapping, String fileName)  {

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {

            ColumnPositionMappingStrategy<Employee> something = new ColumnPositionMappingStrategy<>();
            something.setType(Employee.class);
            something.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader).
                    withMappingStrategy(something)
                    .build();
            List<Employee> list = csv.parse();
            return list;
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("something troubles");
        return new ArrayList<>();
    }

    public static String listToJson(List list){
        Type listType = new TypeToken<List<Employee>>() {}.getType();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(list, listType);
    }

    public static void writeJson(String json, String path) {
        File file = new File(path);
        try {
            if (file.createNewFile()) {
            } else {
                System.out.println("Cant initialize file " + file);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(json);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //write each List<Employee> element to *.json

    public static void writeEachJson(List list, String directory)  {
        File file = new File(directory);
        if (file.mkdir()) {
        } else {
            System.out.println("Can't initialize directory " + directory);
        }

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        int i = 0;
        try {
            for (Object e : list) {
                var gs = gson.toJson(e);
                File js = new File(directory.concat("/eachJson") + Integer.toString(i + 1) + ".json");
                System.out.println(js);
                js.createNewFile();
                FileWriter fw = new FileWriter(js);
                fw.write(gs);
                fw.flush();
                fw.close();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read JSONObject
    public static void readJson(String directory){
        try {
            JSONParser p = new JSONParser();
            FileReader fw = new FileReader(directory);
            Object o = p.parse(fw);
            JSONObject jsonObject = (JSONObject) o;
            System.out.println(jsonObject);
            fw.close();
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }

    //read all json from directory

    public static void readJsonFromDirectory(String directory){
        File file = new File(directory);
        List<String> l = new ArrayList<>();
        if (file.isDirectory()){
            for (File item: file.listFiles()){
                if (!item.isDirectory()) {
                    l.add(directory.concat("/").concat(item.getName()));
                }
            }
        }
        for (String s: l){
            readJson(s);
        }
    }
}
