package task_project;

import org.junit.jupiter.api.BeforeAll;
import task_project.utils.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        System.out.println("Set up method: assigning value to baseURI variable");
        baseURI = ConfigurationReader.getProperty("task.endpoint");
    }
}
