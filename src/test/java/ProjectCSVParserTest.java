import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectCSVParserTest extends ProjectTest{

    ProjectCSVParser sut;


    @BeforeEach
    @Override
    public void init(){
        sut = new ProjectCSVParser();
        System.out.println("test start");
    }

    @AfterEach
    @Override
    public void finished(){
        sut = null;
        System.out.println("test completed");
    }


    @Test
    public void parseCSVTest(){
        //given
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee(
                1,
                "John",
                "Smith",
                "USA",
                25));
        expected.add(new Employee(
                2,
                "Inav",
                "Petrov",
                "RU",
                23));
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileNameCSV = "CSV data.csv";

        //when

        List<Employee> actual = sut.parseCSV(columnMapping, fileNameCSV);

        //assert
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
