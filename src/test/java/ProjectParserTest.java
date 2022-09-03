import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectParserTest extends ProjectTest {

    private ProjectParser sut;

    @BeforeEach
    @Override
    public void init(){
        sut = new ProjectParser();
        System.out.println("test start");
    }

    @AfterEach
    @Override
    public void finished(){
        sut = null;
        System.out.println("test completed");
    }

    @Test
    public void listToJsonTest(){
        //given
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(
                1,
                "John",
                "Smith",
                "USA",
                25));
        list.add(new Employee(
                2,
                "Inav",
                "Petrov",
                "RU",
                23));
        String expected = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}," +
                "{\"id\":2,\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";

        //when
        String actual = sut.listToJson(list);

        //assert

        assertEquals(expected, actual);
    }

    //какая-то хуйня, надо переделать

    @Test
    public void writeJsonTest(){
        //given
        String path = "XML new_data.json";
        Long expected = 145L;
        String json = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}," +
                "{\"id\":2,\"firstName\":\"Inav\",\"lastName\":\"Petrov\",\"country\":\"RU\",\"age\":23}]";

        //when
        Long actual = sut.writeJson(json, path).length();
        //assert

        assertEquals(expected, actual);
    }

}
