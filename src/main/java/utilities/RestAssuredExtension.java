package main.java.utilities;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import main.java.commonFunctions.CommonStringFunctions;
import main.java.dataProviders.ConfigFileReader;

public class RestAssuredExtension {

	public static RequestSpecification Request;

	public RestAssuredExtension() {
		// arrange option
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(ConfigFileReader.getBaseURL_Magento());
		// builder.setBaseUri(ConfigFileReader.getBaseURL_localhost());
		// builder.setBaseUri(ConfigFileReader.getBaseURL_GR());
		builder.setContentType(ContentType.JSON);
		// builder.setContentType(ContentType.XML);
		RequestSpecification requestSpec = builder.build();
		Request = RestAssured.given().spec(requestSpec);
	}

	// Function to perform Get Operation using URL only

	public static ResponseOptions<Response> GetOps(String url) {
		// Act
		return Request.get(url);
	}

	// Function to perform Get Operation using URL with Bearer token and paramter

	public static ResponseOptions<Response> GetOpsWithPathParamsWithToken(String url, String token,
			HashMap<String, String> pathParam) {
		// Act
		Request.headers("Authorization", "Bearer " + CommonStringFunctions.removeSpecialChar(token));
		Request.pathParams(pathParam);
		return Request.get(url);
	}

	// GET Method with Path Parameter
	// Example : http://localhost:3000/posts/1
	public static ResponseOptions<Response> GetOpsWithPathParams(String url, HashMap<String, String> pathParam) {
		Request.pathParams(pathParam);
		return Request.get(url);
	}

	// Function to perform Post Operation using Path Parameter and body
	// Example : http://localhost:3000/posts/2/profile
	// Body : { "name": "ajinkya"}

	public static ResponseOptions<Response> PostOpsWithBodyAndPathParamater(String url, Map<String, String> pathParams,
			Map<String, String> body) {
		Request.pathParams(pathParams);
		Request.body(body);
		return Request.post(url);
	}

	// POST Method with body
	// Example : http://localhost:3000/posts
	// | id | title | author |
	// | 8 | Post_for_delete | mhaskar|

	public static ResponseOptions<Response> PostOpsWithBody(String url, HashMap<String, String> body) {
		Request.body(body);
		return Request.post(url);
	}

	// Delete Method with Path Parameter
	// Example : http://localhost:3000/posts/1
	public static ResponseOptions<Response> DeleteOpsWithPathParams(String url, HashMap<String, String> pathParam) {
		Request.pathParams(pathParam);
		return Request.delete(url);
	}

	public static ResponseOptions<Response> PutOpsWithBodyAndPathParamater(String url,
			HashMap<String, String> pathParam, HashMap<String, String> body) {
		Request.pathParams(pathParam);
		Request.body(body);
		return Request.put(url);
	}

	public static ResponseOptions<Response> PostOpsWithBody(String url, String body) {
		Request.body(body);
		return Request.post(url);
	}

	public static ResponseOptions<Response> PostOpsWithBodyAndToken(String url, String token, String body) {
		// Act
		Request.headers("Authorization", "Bearer " + CommonStringFunctions.removeSpecialChar(token));
		Request.body(body);
		return Request.post(url);
	}

	public static ResponseOptions<Response> PostOpsWithToken(String url, String token) {
		// Act
		Request.headers("Authorization", "Bearer " + CommonStringFunctions.removeSpecialChar(token));
		return Request.post(url);
	}
}
