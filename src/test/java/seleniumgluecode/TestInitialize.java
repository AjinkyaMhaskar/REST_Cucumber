package test.java.seleniumgluecode;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.Before;
import main.java.dataProviders.ConfigFileReader;
import main.java.utilities.RestAssuredExtension;

public class TestInitialize {

	public static WebDriver driver;

	@Before
	public void TestSetup() {
		ConfigFileReader ConfigFileReader = new ConfigFileReader();
		RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
	}

}
