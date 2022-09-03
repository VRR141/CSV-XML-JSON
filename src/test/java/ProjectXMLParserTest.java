import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectXMLParserTest extends ProjectTest{


    ProjectXMLParser sut;


    @BeforeEach
    @Override
    public void init(){
        sut = new ProjectXMLParser();
        System.out.println("test start");
    }

    @AfterEach
    @Override
    public void finished(){
        sut = null;
        System.out.println("test completed");
    }

    @Test
    public void parseXMLTest(){
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

        String fileNameXML = "XML data.xml";


        //when

        List<Employee> actual = sut.parseXML(fileNameXML);


        //assert

        assertEquals(expected, actual);
    }
}
