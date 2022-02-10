package com.onmobile.vol.cataloghub.chartCollection;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.CommonValues;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;

import io.restassured.response.Response;

public class CreateChartCollection extends RestBaseClass implements Constants {

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
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_CHART_XL_PATH, CREATE_CHART_COLLECTION_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestdata() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_CHART_XL_PATH, CREATE_CHART_COLLECTION_NEGATIVE_SHEET);
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

		requestBody.put("chart_collection_name",
				testData.get("chart_collection_name") + "_" + RandomStringUtils.randomAlphanumeric(10));

		Response response = requestGenarator.getRequestWithPathReqobject(BASE_URL, PathParam, requestBody).when()
				.post(CREATE_CHART_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("store_id", equalTo(Integer.parseInt(STORE_ID)), "chart_collection_name",
						containsString(testData.get("chart_collection_name")))
				.and().extract().response();

		CommonValues.chartCollectionValues.put("chart_collection_id",
				response.jsonPath().getString("chart_collection_id"));
		CommonValues.chartCollectionValues.put("chart_collection_name",
				response.jsonPath().getString("chart_collection_name"));
		CommonValues.chartCollectionValues.put("store_id", response.jsonPath().getString("store_id"));

		loggerReport.info("Chart collection is created ---> " + response.prettyPrint());
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
		requestBody.put("chart_collection_name", testData.get("chart_collection_name"));

		Response response = requestGenarator.getRequestWithPathReqobject(BASE_URL, PathParam, requestBody).when()
				.post(CREATE_CHART_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.and().extract().response();

		loggerReport.info("Negative Scenarios of chart collection API ---> " + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug("Negative Scenarios of  ---> " + response.asPrettyString());

	}
}
