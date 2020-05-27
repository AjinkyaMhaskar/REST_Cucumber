package test.java.seleniumgluecode;

import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;

import org.hamcrest.core.IsNot;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import main.java.utilities.RestAssuredExtension;

public class DELETEPostSteps {

	@SuppressWarnings("unused")
	private static ResponseOptions<Response> response;

	// POST operation with BODY

	@Given("^I perform post operation for \"([^\"]*)\" with body as$")
	public void i_perform_post_operation_for_with_body_as(String url, DataTable table) throws Throwable {
		List<List<String>> data = table.raw();

		// set body
		HashMap<String, String> body = new HashMap<>();
		body.put("id", data.get(1).get(0));
		body.put("title", data.get(1).get(1));
		body.put("author", data.get(1).get(2));

		// Perform post operation
		response = RestAssuredExtension.PostOpsWithBody(url, body);

	}

	// DELETE operation with path parameter

	@Given("^I perform DELETE operation for \"([^\"]*)\"$")
	public void i_perform_DELETE_operation_for(String url, DataTable table) throws Throwable {

		List<List<String>> data = table.raw();

		// path parameter
		HashMap<String, String> pathParam = new HashMap<>();
		pathParam.put("postid", data.get(1).get(0));

		// Perform post operation
		response = RestAssuredExtension.DeleteOpsWithPathParams(url, pathParam);

	}

	@Given("^I am performing GET operation for \"([^\"]*)\"$")
	public void i_perform_GET_operation_for(String url, DataTable table) throws Throwable {
		List<List<String>> data = table.raw();

		// path parameter
		HashMap<String, String> pathParam = new HashMap<>();
		pathParam.put("postid", data.get(1).get(0));

		// Perform post operation
		response = RestAssuredExtension.GetOpsWithPathParams(url, pathParam);
	}

	@SuppressWarnings("deprecation")
	@Then("^I should not see the title has a name \"([^\"]*)\"$")
	public void i_not_should_see_the_title_has_a_name(String title) throws Throwable {
		assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
	}

}
