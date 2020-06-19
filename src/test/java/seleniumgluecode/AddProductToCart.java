package test.java.seleniumgluecode;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import main.java.commonFunctions.CommonUIFunctions;
import main.java.dataProviders.ConfigFileReader;
import main.java.utilities.RestAssuredExtension;

public class AddProductToCart {

	private static ResponseOptions<Response> response;
	String email_id = ConfigFileReader.getCustomer_email();
	String password = ConfigFileReader.getCustomer_password();
	private static String customer_token = null;

	@Given("^I perfomr POST operation with URL \"([^\"]*)\" with customer details for authentication$")
	public void i_perfomr_POST_operation_with_URL_with_customer_details_for_authentication(String url)
			throws Throwable {
		// Set body for user created in CreateUserMethod.java
		HashMap<String, String> body = new HashMap<>();
		body.put("username", ConfigFileReader.getCustomer_email());
		body.put("password", ConfigFileReader.getCustomer_password());

		// Perform post operation
		response = RestAssuredExtension.PostOpsWithBody(url, body);
		customer_token = response.getBody().asString();
	}

	@Given("^I perform POST operation with URL \"([^\"]*)\" with customer token$")
	public void i_perform_POST_operation_with_URL_with_customer_token(String url) throws Throwable {
		response = RestAssuredExtension.PostOpsWithToken(url, response.getBody().asString());
	}

	@SuppressWarnings("deprecation")
	@Then("^I should get quote id and API status code (\\d+)$")
	public void i_should_get_quote_id_and_API_status_code(int statusCode) throws Throwable {
		assertThat(response.getStatusCode(), is(statusCode));
		if (response.getBody().asString().isEmpty())
			System.out.println("Quote id not created");
		System.out.println("Quote id returned successfully");
	}

	@Given("^I perfomr POST operation with URL \"([^\"]*)\" with customer token and body$")
	public void i_perfomr_POST_operation_with_URL_with_customer_token_and_body(String url) throws Throwable {

		String body = "{\"cartItem\":{\"sku\":\"" + ConfigFileReader.getSimple_product_sku()
				+ "\",\"qty\":1,\"quote_id\":\"" + response.getBody().asString() + "\"}}";
		response = RestAssuredExtension.PostOpsWithBodyAndToken(url, customer_token, body);
	}

	@SuppressWarnings("deprecation")
	@Then("^I should get Product name, type and price in response$")
	public void i_should_get_Product_name_type_and_price_in_response() throws Throwable {
		// print response
		response.getBody().jsonPath().prettyPrint();
		assertThat(response.getBody().jsonPath().get("name"), is(ConfigFileReader.getSimple_product_name()));
		assertThat(response.getBody().jsonPath().get("price"), is(34));
		assertThat(response.getBody().jsonPath().get("product_type"), is(ConfigFileReader.getSimple_product_type()));
	}

	@Then("^I navigate to customer cart verify added product$")
	public void i_navigate_to_cuctomer_cart_verify_added_product() throws Throwable {
		TestInitialize.driver.get("https://internal-hpwszmy-gopegmkbduhfw.us-4.magentosite.cloud/checkout/cart/");
		CommonUIFunctions.waitWithXpath("//td[@class='col item']//a[contains(text(),'Joust Duffle Bag')]");
		String product_name = TestInitialize.driver
				.findElement(By.xpath("//td[@class='col item']//a[contains(text(),'Joust Duffle Bag')]")).getText();
		Assert.assertEquals(ConfigFileReader.getSimple_product_name(), product_name);
		TestInitialize.driver.quit();
	}
}
