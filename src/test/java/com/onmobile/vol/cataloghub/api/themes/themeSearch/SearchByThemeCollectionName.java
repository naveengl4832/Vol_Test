package com.onmobile.vol.cataloghub.api.themes.themeSearch;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;

import io.restassured.response.Response;

public class SearchByThemeCollectionName extends RestBaseClass implements Constants {
	/**
	 * 
	 * API Is to get the details of themes by passing theme collection Name
	 * 
	 */

	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> queryParam;
	public Map<String, String> pathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH,
				SEARCH_THEME_COLLECTION_BY_THEMENAME_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH,
				SEARCH_THEME_BY_COLLECECTION_THEMENAME_NEGATIVE_SHEET);

		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void searchThemeCollection(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("searchItem", testData.get("searchItem"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("store_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(SEARCH_THEME_COLLECTION_BY_COLLECTION_THEME_NAME).then()
				.statusCode(Integer.parseInt(testData.get("statusCode"))).and().body("total_item_count", greaterThan(0))
				.extract().response();

		List<Object> list = response.jsonPath().getList("theme_collections");

		for (int i = 0; i < list.size(); i++) {
			response.then().body("theme_collections[" + i + "].theme_collection_name",
					containsString(testData.get("searchItem")));
		}

		loggerReport.pass("Response" + response.prettyPrint());
		loggerReport.pass("Total count of the matched objects : --> " + response.jsonPath().get("total_item_count"));
		LOGGER.info("Response : " + response.prettyPrint());

	}

	@Test(dataProvider = "getNegativeTestdata")
	public void searchThemeCollectionNegativeScenarioes(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("searchItem", testData.get("searchItem"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("store_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(SEARCH_THEME_COLLECTION_BY_COLLECTION_THEME_NAME).then()
				.statusCode(Integer.parseInt(testData.get("statusCode"))).and().extract().response();
		loggerReport.pass("Response" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
	}

}
