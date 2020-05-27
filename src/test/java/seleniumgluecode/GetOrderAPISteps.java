package test.java.seleniumgluecode;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import main.java.dataProviders.ConfigFileReader;
import main.java.utilities.RestAssuredExtension;

public class GetOrderAPISteps {

	private static ResponseOptions<Response> response;

	@Given("^I perform authentication operation for \"([^\"]*)\" with body from properties file$")
	public void i_perform_authentication_operation_for_with_body_from_properties_file(String url) throws Throwable {

		// Set body (Username & Password from property file)
		HashMap<String, String> body = new HashMap<>();
		body.put("username", ConfigFileReader.getUserName());
		body.put("password", ConfigFileReader.getPassword());

		// Perform post operation
		response = RestAssuredExtension.PostOpsWithBody(url, body);
	}

	@Given("^I perform GET operation for \"([^\"]*)\" with token and body as$")
	public void i_perform_GET_operation_for_with_token_and_body_as(String url, DataTable table) throws Throwable {
		List<List<String>> data = table.raw();

		// path parameter
		HashMap<String, String> pathParam = new HashMap<>();
		pathParam.put("postid", data.get(1).get(0));

		// Perform put operation
		response = RestAssuredExtension.GetOpsWithPathParamsWithToken(url, response.getBody().asString(), pathParam);
	}

	@SuppressWarnings("deprecation")
	@Then("^I should see the status as (\\d+) & order id as \"([^\"]*)\"$")
	public void i_should_see_the_status_as_order_id_as(int statusCode, String arg2) throws Throwable {
		assertThat(response.getStatusCode(), is(statusCode));
//		assertThat(response.getBody().jsonPath().get("author"), is(author));
	}
}
