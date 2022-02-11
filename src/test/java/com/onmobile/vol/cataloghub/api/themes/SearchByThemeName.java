package com.onmobile.vol.cataloghub.api.themes;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;

import java.util.HashMap;
import java.util.List;
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

public class SearchByThemeName extends RestBaseClass implements Constants {

	/**
	 * 
	 * API Is to get the details of themes by passing theme Name
	 * 
	 */

	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> queryParam;
	public Map<String, String> pathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, SEARCH_THEME_BY_THEMENAME_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, SEARCH_THEME_BY_THEMENAME_NEGATIVE_SHEET);

		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void validateSearchThemeByThemeName(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("searchItem", CommonValues.themeValues.get("theme_name"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", CommonValues.themeValues.get("store_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(SEARCH_BY_THEME_NAME).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("total_item_count", greaterThan(0)).extract().response();

		List<Object> list = response.jsonPath().getList("themes");

		if (!testData.get("Test case").equalsIgnoreCase("Blank Search")) {

			for (int i = 0; i < list.size(); i++) {

				response.then().body("themes[" + i + "].theme_name",
						containsString(CommonValues.themeValues.get("theme_name")));

			}
		}

		response.then().assertThat().body(matchesJsonSchemaInClasspath(
				PropertyReader.getProperty(CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE, SEARCH_THEME_BY_THEME_NAME_SCHEMA)));

		loggerReport.pass("Response" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());

	}

	@Test(dataProvider = "getNegativeTestdata")
	public void searchThemeByThemeNameNegativeScenarioes(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("searchItem", testData.get("search_Item"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("StoreID"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(SEARCH_BY_THEME_NAME).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.extract().response();
		loggerReport.pass("Response" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
	}

}
