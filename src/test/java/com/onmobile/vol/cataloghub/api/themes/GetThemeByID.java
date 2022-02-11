package com.onmobile.vol.cataloghub.api.themes;

import static org.hamcrest.Matchers.*;

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
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetThemeByID extends RestBaseClass implements Constants {

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
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, GET_THEME_BY_ID_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, GET_THEME_BY_ID_NAGATIVE_SHEET);

		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void getThemeByThemeID(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", CommonValues.themeValues.get("store_id"));
		pathParam.put("theme_id", CommonValues.themeValues.get("theme_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(GET_CATALOG_HUB_THEAMS).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("store_id", equalTo(Integer.parseInt(CommonValues.themeValues.get("store_id"))), "theme_id",
						equalTo(Integer.parseInt(CommonValues.themeValues.get("theme_id"))))
				.extract().response();

		response.then().assertThat().body(matchesJsonSchemaInClasspath(
				PropertyReader.getProperty(CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE, CATALOG_HUB_THEMES)));

		loggerReport.pass("Response" + response.prettyPrint());
		loggerReport.pass("Veify the Theme_ID : " + response.jsonPath().getString("theme_id"));
		LOGGER.info("Response : " + response.prettyPrint());

	}

	@Test(dataProvider = "getNegativeTestdata")
	public void getThemeByThemeid_NegativeScenarios(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("StoreID"));
		pathParam.put("theme_id", testData.get("themeID"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(GET_CATALOG_HUB_THEAMS).then().statusCode(Integer.parseInt(testData.get("statusCode"))).extract()
				.response();
		loggerReport.info("Response \n" + response.asString());
		LOGGER.info("Response : " + response.asString());

	}
}
