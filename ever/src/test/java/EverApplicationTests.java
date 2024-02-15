import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = EverApplicationTests.class)
public class EverApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("\"hello\" = " + "hello");
    }

    @Test
    void hello() {
        System.out.println("hello");

    }
}
