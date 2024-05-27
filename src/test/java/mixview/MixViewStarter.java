package mixview;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/mixViewFe",
        glue = "mixview",
        plugin = {"pretty","html:target/report.html"})
public class MixViewStarter {
}
