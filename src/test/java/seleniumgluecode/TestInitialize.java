package test.java.seleniumgluecode;

import cucumber.api.java.Before;
import main.java.dataProviders.ConfigFileReader;
import main.java.utilities.RestAssuredExtension;

public class TestInitialize {

	@Before
	public void TestSetup() {
		ConfigFileReader ConfigFileReader = new ConfigFileReader();
		RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
	}

}
