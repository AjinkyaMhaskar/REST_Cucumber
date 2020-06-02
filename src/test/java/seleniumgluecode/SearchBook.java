package test.java.seleniumgluecode;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class SearchBook {

	private static ResponseOptions<Response> response;

	@Given("^I perform GET operation for \"([^\"]*)\" with query parameters$")
	public void i_perform_GET_operation_for_with_query_parameters(String url) throws Throwable {

//		List<List<String>> data = table.raw();
//
//		// query parameter
//		HashMap<String, String> queryParam = new HashMap<>();
//
//		queryParam.put("format", data.get(1).get(1));
//		queryParam.put("key", data.get(1).get(2));
//		queryParam.put("q", data.get(1).get(3));
		int page = 1;

		response = BDDStyleMethod.GetOpsWithQueryParams(url);

		System.out.println(response.getBody().asString());

		XmlPath xml = new XmlPath(response.getBody().asString());
		System.out.println("test-------------");

		int i = 0, j = 1;
		for (i = 0; i < 20; i++) {
			System.out.println("Search Result:" + j + " "
					+ xml.getString("GoodreadsResponse.search.results.work[" + i + "].best_book.title"));
			j++;
		}

		System.out.println("test-------------");
	}

	@SuppressWarnings("deprecation")
	@Then("^I should see the status as (\\d+) and use XML class path for result verification$")
	public void i_should_see_the_status_as_and_use_XML_class_path_for_result_verification(int statusCode)
			throws Throwable {
		assertThat(response.getStatusCode(), is(statusCode));
	}

}
