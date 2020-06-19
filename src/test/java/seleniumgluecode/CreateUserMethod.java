package test.java.seleniumgluecode;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import main.java.commonFunctions.CommonUIFunctions;
import main.java.dataProviders.ConfigFileReader;
import main.java.utilities.RestAssuredExtension;

public class CreateUserMethod {

	private static ResponseOptions<Response> response;
	String email_id = ConfigFileReader.getCustomer_email();
	String password = ConfigFileReader.getCustomer_password();

	@Given("^I perform authentication operation for \"([^\"]*)\" with body from properties fileone")
	public void i_perform_authentication_operation_for_with_body_from_properties_file(String url) throws Throwable {
		// Set body (Username & Password from property file)
		HashMap<String, String> body = new HashMap<>();
		body.put("username", ConfigFileReader.getUserName());
		body.put("password", ConfigFileReader.getPassword());

		// Perform post operation
		response = RestAssuredExtension.PostOpsWithBody(url, body);
	}

	@SuppressWarnings("unchecked")
	@Given("^I perform POST operation with URL \"([^\"]*)\" with body$")
	public void i_perform_POST_operation_with_URL_with_header_content_type_and_body(String url) throws Throwable {

		// String body of user
		String body = "{\"customer\":{\"email\":\"" + email_id
				+ "\",\"firstname\":\"Jane\",\"lastname\":\"Doe\",\"addresses\":[{\"defaultShipping\":true,\"defaultBilling\":true,\"firstname\":\"Jane\",\"lastname\":\"Doe\",\"region\":{\"regionCode\":\"NY\",\"region\":\"New York\",\"regionId\":43},\"postcode\":\"10755\",\"street\":[\"123 Oak Ave\"],\"city\":\"Purchase\",\"telephone\":\"512-555-1111\",\"countryId\":\"US\"}]},\"password\":\""
				+ password + "\"}";
		response = RestAssuredExtension.PostOpsWithBodyAndToken(url, response.getBody().asString(), body);
	}

	@SuppressWarnings("deprecation")
	@Then("^I should see the status as (\\d+)$")
	public void i_should_see_the_status_as(int statusCode) throws Throwable {
		response.getBody().jsonPath().prettyPrint();
		assertThat(response.getStatusCode(), is(statusCode));
	}

	@Given("^I open browser and Navigate to Magento website front end$")
	public void i_open_browser_and_Navigate_to_Magnrto_website_front_end() throws Throwable {
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver.exe");
		TestInitialize.driver = new FirefoxDriver();
		TestInitialize.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TestInitialize.driver.get(ConfigFileReader.getfrontend_URL_Magento());
	}

	@Given("^I navigate to login page and enter username, password and click on sign-in button$")
	public void i_navigate_to_login_page_and_enter_username_password_and_click_on_sign_in_button() throws Throwable {

		TestInitialize.driver.findElement(By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]"))
				.click();
		CommonUIFunctions.waitWithid("email");
		TestInitialize.driver.findElement(By.id("email")).sendKeys(email_id);
		TestInitialize.driver.findElement(By.id("pass")).sendKeys(password);
		TestInitialize.driver
				.findElement(By.xpath("//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]")).click();
		CommonUIFunctions.waitWithXpath(
				".//div[@class='panel header']/ul[@class='header links']/li[@class='greet welcome']/span[@class='logged-in']");
	}

	@Then("^I verify if user is logged-in successfully$")
	public void i_verify_if_user_is_logged_in_successfully() throws Throwable {
		String WelcomeMsg = TestInitialize.driver.findElement(By
				.xpath("//div[@class='panel header']//span[@class='logged-in'][contains(text(),'Welcome, Jane Doe!')]"))
				.getText();
		Assert.assertEquals("Welcome, Jane Doe!", WelcomeMsg);
		TestInitialize.driver.quit();
	}

}
