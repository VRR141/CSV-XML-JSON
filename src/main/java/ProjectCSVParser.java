import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectCSVParser extends ProjectParser {

    public List<Employee> parseCSV(String[] columnMapping, String fileName)  {

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
}
