package com.onmobile.vol.cataloghub.api.banner;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.CommonValues;
import com.onmobile.api.helper.PropertyReader;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;

import io.restassured.response.Response;

public class GetBannerById extends RestBaseClass implements Constants {

	/**
	 * 
	 * Get Request with Catalog-Hub Api
	 * 
	 */

	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> queryParam;
	public Map<String, String> pathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH, GET_BANNER_POSTIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH, GET_BANNER_NEGATIVE_SHEET);

		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void getBannerByID(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", CommonValues.bannerValues.get("store_id"));
		pathParam.put("banner_id", CommonValues.bannerValues.get("banner_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when().get(GET_BANNER_BY_ID)
				.then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("store_id", equalTo(Integer.parseInt(CommonValues.bannerValues.get("store_id"))), "name",
						equalTo(CommonValues.bannerValues.get("name")), "banner_id",
						equalTo(Integer.parseInt(CommonValues.bannerValues.get("banner_id"))), "banner_names.name",
						hasItem(CommonValues.bannerValues.get("name")))
				.extract().response();

		response.then().assertThat().body(matchesJsonSchemaInClasspath(
				PropertyReader.getProperty(CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE, GET_BANNER_BY_ID_JSON_SCHEMA)));

		loggerReport.pass("Response" + response.prettyPrint());
		loggerReport.pass("Veified the ChartID : " + response.jsonPath().getString("chart_id"));
		LOGGER.info("Response : " + response.prettyPrint());

	}

	@Test(dataProvider = "getNegativeTestdata")
	public void getBannerById_NegativeScenarios(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("store_id"));
		pathParam.put("banner_id", testData.get("banner_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when().get(GET_BANNER_BY_ID)
				.then().statusCode(Integer.parseInt(testData.get("statusCode"))).extract().response();

		loggerReport.info("Response \n" + response.asString());
		LOGGER.info("Response : " + response.asString());
		log.debug(response.prettyPrint());
	}

}