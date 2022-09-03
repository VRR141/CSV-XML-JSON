import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ProjectTest {

    @BeforeEach
    public void init(){
        System.out.println("test start");
    }

    @AfterEach
    public void finished(){
        System.out.println("test completed");
    }

    @BeforeAll
    public static void startAll(){
        System.out.println("Tests start");
    }

    @AfterAll
    public static void finishAll(){
        System.out.println("Tests finished");
    }
}
