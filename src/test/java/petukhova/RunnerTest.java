package petukhova;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//аннотация как запустить тест
@RunWith(Cucumber.class)

@CucumberOptions(
        features = "C:\\Users\\а\\IdeaProjects\\WikiSelenium\\src\\test\\resources\\features",
        glue = "petukhova/steps",
        plugin = "pretty"
)

public class RunnerTest {
}

