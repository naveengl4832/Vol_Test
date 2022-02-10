package com.onmobile.vol.cataloghub.api.chat;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.CommonValues;
import com.onmobile.api.helper.PropertyReader;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;

import io.restassured.response.Response;

public class CreateChat extends RestBaseClass implements Constants {

	/**
	 * 
	 * API is developed to create Charts
	 * 
	 */
	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> PathParam;
	public Map<String, String> requestBody;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_CHART_XL_PATH, CREATE_CHART_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestdata() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_CHART_XL_PATH, CREATE_CHART_NEGATIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void createChatByChatName(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;
		PathParam = new HashMap<String, String>();
		PathParam.put("store_id", STORE_ID);

		requestBody = new HashMap<String, String>();

		requestBody.put("chart_name", testData.get("chart_name") + "_" + RandomStringUtils.randomAlphanumeric(10));
		requestBody.put("type", testData.get("type"));
		requestBody.put("language", testData.get("language"));

		Response response = requestGenarator.getRequestWithPathReqobject(BASE_URL, PathParam, requestBody).when()
				.post(CREATE_CHART_BY_CHART_NAME).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("store_id", equalTo(Integer.parseInt(STORE_ID)), "chart_name",
						containsString(testData.get("chart_name")), "type", equalTo(testData.get("type")), "language",
						equalTo(testData.get("language")), "locale_chart_names[0].chart_name",
						containsString(testData.get("chart_name")))
				.and().extract().response();

		response.then().assertThat().body(matchesJsonSchemaInClasspath(
				PropertyReader.getProperty(CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE, CREATE_CHART_JSON_SCHEMA)));

		CommonValues.chartValues.put("chart_id", response.jsonPath().getString("chart_id"));
		CommonValues.chartValues.put("chart_name", response.jsonPath().getString("chart_name"));
		CommonValues.chartValues.put("language", response.jsonPath().getString("language"));
		CommonValues.chartValues.put("type", response.jsonPath().getString("type"));
		CommonValues.chartValues.put("store_id", response.jsonPath().getString("store_id"));
		
		loggerReport.info("Chart is created ---> " + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.asPrettyString());

	}

	@Test(dataProvider = "getNegativeTestdata")
	public void NegativeScenarioCreateChat(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;
		PathParam = new HashMap<String, String>();
		PathParam.put("store_id", testData.get("store_id"));

		requestBody = new HashMap<String, String>();

		requestBody.put("chart_name", testData.get("chart_name"));
		requestBody.put("type", testData.get("type"));
		requestBody.put("language", testData.get("language"));

		Response response = requestGenarator.getRequestWithPathReqobject(BASE_URL, PathParam, requestBody).when()
				.post(CREATE_CHART_BY_CHART_NAME).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.and().extract().response();

		loggerReport.info("Negative Scenarios of  ---> " + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug("Negative Scenarios of  ---> " + response.asPrettyString());

	}
}
