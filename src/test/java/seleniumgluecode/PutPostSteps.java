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

public class PutPostSteps {

	private static ResponseOptions<Response> response;

	@Given("^I perform PUT operation for \"([^\"]*)\"$")
	public void i_perform_PUT_operation_for(String url, DataTable table) throws Throwable {
		List<List<String>> data = table.raw();

		// set body
		HashMap<String, String> body = new HashMap<>();
		body.put("id", data.get(1).get(0));
		body.put("title", data.get(1).get(1));
		body.put("author", data.get(1).get(2));

		// path parameter
		HashMap<String, String> pathParam = new HashMap<>();
		pathParam.put("postid", data.get(1).get(0));

		// Perform put operation
		response = RestAssuredExtension.PutOpsWithBodyAndPathParamater(url, pathParam, body);

	}

	@Given("^I am performing GET operation for \"([^\"]*)\" after post$")
	public void i_am_performing_GET_operation_for_after_post(String url, DataTable table) throws Throwable {
		List<List<String>> data = table.raw();

		// path parameter
		HashMap<String, String> pathParam = new HashMap<>();
		pathParam.put("postid", data.get(1).get(0));

		// Perform post operation
		response = RestAssuredExtension.GetOpsWithPathParams(url, pathParam);
	}

	@SuppressWarnings("deprecation")

	@Then("^I should see the title has a title \"([^\"]*)\" and author as \"([^\"]*)\"$")
	public void i_should_see_the_title_has_a_title_and_author_as(String title, String author) throws Throwable {
		assertThat(response.getBody().jsonPath().get("title"), is(title));
		assertThat(response.getBody().jsonPath().get("author"), is(author));
	}

}
