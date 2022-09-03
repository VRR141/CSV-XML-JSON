import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectJSONParser extends ProjectParser {

    public String readString(String path) {
        StringBuffer sb = new StringBuffer();
        try {
            JSONParser p = new JSONParser();
            BufferedReader br = new BufferedReader(new FileReader(path));
            Object o = p.parse(br);
            JSONArray jsonArray = (JSONArray) o;
            sb.append(jsonArray);
            return sb.toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        sb.append("something troubles");
        return sb.toString();
    }

    public List<Employee> jsonToList(String json){
        List<Employee> list = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);
            for (Object o : jsonArray) {
                JSONObject temp = (JSONObject) o;
                Employee employee = gson.fromJson(String.valueOf(temp), Employee.class);
                list.add(employee);
            }
            System.out.println("\nJSON -> OBJECT\n");
            for (Employee e: list){
                System.out.println(e);
            }
            return list;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("something troubles");
        return list;
    }



    //read JSONObject
    public void readJson(String directory){
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

    //write each List<Employee> element to *.json

    public void writeEachJson(List list, String directory)  {
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
                File js = new File(directory.concat("/eachJson") + (i + 1) + ".json");
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


    //read all json from directory

    public void readJsonFromDirectory(String directory){
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
