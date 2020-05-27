package test.java.seleniumgluecode;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import main.java.utilities.RestAssuredExtension;

public class GetPostSteps {

	private static ResponseOptions<Response> response;

	@Given("^I perform GET operation for \"([^\"]*)\"$")
	public void i_perform_GET_operation_for(String url) throws Throwable {
		// given().contentType(ContentType.JSON);

		response = RestAssuredExtension.GetOps(url);
	}

	@And("^I perform GET for the post number \"([^\"]*)\"$")
	public void i_perform_GET_for_the_post_number(String postnumber) throws Throwable {
		// when().get(String.format("http://localhost:3000/posts/%s", postnumber)).
		// then().body("author", is("mhaskar"));
	}

	@SuppressWarnings("deprecation")
	@Then("^I should get the author name as \"([^\"]*)\"$")
	public void i_should_get_the_author_name_as(String name) throws Throwable {
		// response.getBody().jsonPath().prettyPrint();
		assertThat(response.getBody().jsonPath().get("author"), Matchers.hasItems(name));
	}

	@Then("^I should verify get parameter$")
	public void i_should_verify_get_parameter() throws Throwable {
		BDDStyleMethod.PerformPathParameter();
	}

	@Then("^I should verify get query parameter$")
	public void i_should_verify_get_query_parameter() throws Throwable {
		BDDStyleMethod.PerformQueryParameter();
	}
}
