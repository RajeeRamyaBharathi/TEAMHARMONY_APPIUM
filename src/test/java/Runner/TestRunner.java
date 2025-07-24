package Runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/Features" }, glue = { "StepDefinitions" },

		monochrome = false,

		tags = "",

		plugin = { "pretty", "html:target/cucumber.html", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",

				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				 

		},
		publish= true)

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false) // Enable parallel execution
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
