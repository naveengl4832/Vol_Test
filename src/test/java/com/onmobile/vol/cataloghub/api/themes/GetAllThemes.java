package com.onmobile.vol.cataloghub.api.themes;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
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

public class GetAllThemes extends RestBaseClass implements Constants {

	/**
	 * 
	 * Get Request with Catalog-Hub Api.. This request will give response with all
	 * themes
	 * 
	 */

	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> pathParam;
	public Map<String, String> queryParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, GET_ALL_THEMES_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void getAllThemes(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("StoreID"));

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when().get(GET_ALL_THEMES)
				.then().statusCode(Integer.parseInt(testData.get("statusCode"))).and().body("theme_id",
						hasItem(Integer.parseInt(CommonValues.theme_Id)), "image_url", hasItem(CommonValues.image_url))
				.extract().response();

		System.out.println(response.asPrettyString());

		response.then().assertThat().body(matchesJsonSchemaInClasspath(
				PropertyReader.getProperty(CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE, GET_ALL_THEMES_SCHEMA)));

		loggerReport.pass("Response \n" + response.prettyPrint());
		loggerReport.pass("Veify the Theme_ID :  \n" + response.jsonPath().getString("theme_id"));
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.asPrettyString());


	}

}
