import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectJSONParserTest extends ProjectTest {

    ProjectJSONParser sut;

    @BeforeEach
    @Override
    public void init(){
        sut = new ProjectJSONParser();
        System.out.println("test start");
    }

    @AfterEach
    @Override
    public void finished(){
        sut = null;
        System.out.println("test completed");
    }

    @Test
    public void readStringTest(){
        //given
        String pathXML = "CSV new_data.json";
        String expected = "[{\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"id\":1,\"age\":25}," +
                "{\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"id\":2,\"age\":23}]";

        //when
        String actual = sut.readString(pathXML);

        //assert

        assertThat(actual, equalTo(expected));
        assertEquals(expected.length(), actual.length());
        assertEquals(expected, actual);
    }

    @Test
    public void jsonToListTest(){
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
        String json = "[{\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"id\":1,\"age\":25}," +
                "{\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"id\":2,\"age\":23}]";

        //when

        List<Employee> actual = sut.jsonToList(json);

        //assert
        assertThat(actual, hasSize(2));
        assertEquals(expected, actual);
    }

}
