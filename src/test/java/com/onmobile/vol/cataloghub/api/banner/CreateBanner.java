package com.onmobile.vol.cataloghub.api.banner;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

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

public class CreateBanner extends RestBaseClass implements Constants {

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
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH, CREATE_BANNER_POSTIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestdata() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH, CREATE_BANNER_NEGATIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void createChatByChatName(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;
		PathParam = new HashMap<String, String>();
		PathParam.put("store_id", testData.get("store_id"));

		requestBody = new HashMap<String, String>();

		requestBody.put("name", testData.get("name") + "_" + RandomStringUtils.randomAlphanumeric(10));
		requestBody.put("language", testData.get("language"));

		Response response = requestGenarator.getRequestWithPathReqobject(BASE_URL, PathParam, requestBody).when()
				.post(CREATE_BANNER).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("store_id", equalTo(Integer.parseInt(testData.get("store_id"))), "name",
						containsString(testData.get("name")), "language", equalTo(testData.get("language")),
						"image_url", is(notNullValue()), "gif_url", is(notNullValue()), "video_url", is(notNullValue()),
						"banner_names", is(not(empty())))
				.and().extract().response();

		CommonValues.bannerValues.put("banner_id", response.jsonPath().getString("banner_id"));
		CommonValues.bannerValues.put("name", response.jsonPath().getString("name"));
		CommonValues.bannerValues.put("language", response.jsonPath().getString("language"));
		CommonValues.bannerValues.put("image_url", response.jsonPath().getString("image_url"));
		CommonValues.bannerValues.put("store_id", response.jsonPath().getString("store_id"));
		
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

		requestBody.put("name", testData.get("name"));
		requestBody.put("language", testData.get("language"));

		Response response = requestGenarator.getRequestWithPathReqobject(BASE_URL, PathParam, requestBody).when()
				.post(CREATE_BANNER).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and().and()
				.extract().response();

		loggerReport.info("Negative Scenarios of  ---> " + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug("Negative Scenarios of  ---> " + response.asPrettyString());

	}
}
