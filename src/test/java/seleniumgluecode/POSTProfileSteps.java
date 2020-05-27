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
import main.java.utilities.RestAssuredExtension;

public class POSTProfileSteps {

	private static ResponseOptions<Response> response;

	@Given("^I perform POST operation for \"([^\"]*)\"$")
	public void i_perform_POST_operation_for(String arg1) throws Throwable {
		BDDStyleMethod.PerformPostWithBodyParameter();
	}

	@Given("^I perform post operation for \"([^\"]*)\" with body$")
	public void i_perform_post_operation_for_with_body(String url, DataTable table) throws Throwable {
		List<List<String>> data = table.raw();

		// set body
		HashMap<String, String> body = new HashMap<>();
		body.put("name", data.get(1).get(0));

		// path parameter
		HashMap<String, String> pathParam = new HashMap<>();
		pathParam.put("profileNo", data.get(1).get(1));

		// Perform post operation
		response = RestAssuredExtension.PostOpsWithBodyAndPathParamater(url, pathParam, body);
	}

	@SuppressWarnings("deprecation")
	@Then("^I should see the body has a name \"([^\"]*)\"$")
	public void i_should_see_the_body_has_a_name(String name) throws Throwable {
		response.getBody().jsonPath().prettyPrint();
		assertThat(response.getBody().jsonPath().get("name"), is(name));
	}
}
