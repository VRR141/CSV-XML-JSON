import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ProjectParser {

    public String listToJson(List list){
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(list, listType);
    }

    public void writeJson(String json, String path) {
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

}
