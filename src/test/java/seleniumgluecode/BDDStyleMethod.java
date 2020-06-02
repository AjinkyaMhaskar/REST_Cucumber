package test.java.seleniumgluecode;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class BDDStyleMethod {

	public static ResponseOptions<Response> Request;

	// Function to perform Get Operation using Path Parameter
	// Example : http://localhost:3000/posts/1
	public static void PerformPathParameter() {
		given().contentType(ContentType.JSON).with().pathParam("post", 1).when()
				.get("http://localhost:3000/posts/{post}").then().body("author", is("mhaskar"));
	}

	// Function to perform Get Operation using Query Parameter
	// Example : http://localhost:3000/posts/?id=1
	public static void PerformQueryParameter() {
		given().contentType(ContentType.JSON).with().queryParam("id", 1).when().get("http://localhost:3000/posts/")
				.then().body("author", contains("mhaskar"));
	}

	// Create a new POST method
	public static void PerformPostWithBodyParameter() {
		HashMap<String, String> postContent = new HashMap<String, String>();
		postContent.put("id", "9");
		postContent.put("title", "PostMethod");
		postContent.put("author", "mhaskar_PostMethod");

		given().contentType(ContentType.JSON).with().body(postContent).when().post("http://localhost:3000/posts").then()
				.body("author", is("mhaskar_PostMethod"));
	}

	@SuppressWarnings("deprecation")
	public static ResponseOptions<Response> GetOpsWithQueryParams(String url) {

		// query parameter
		HashMap<String, String> queryParam = new HashMap<>();

		queryParam.put("format", "xml");
		queryParam.put("key", "4H9OpAay8m9znBqaZ72yw");
		queryParam.put("q", "chetan bhagat");

		Request = given().contentType(ContentType.XML).queryParameters(queryParam).get(url);
		return Request;

	}
}
