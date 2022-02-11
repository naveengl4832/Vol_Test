package com.onmobile.vol.cataloghub.api.banner;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
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

public class SearchByBannerName extends RestBaseClass implements Constants {

	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> pathParam;
	public Map<String, String> queryParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH,
				SEARCH_BANNER_BY_NAME_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestdata() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH,
				SEARCH_BANNER_BY_NAME_NEGATIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void searchBannerByname(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", CommonValues.bannerValues.get("store_id"));

		queryParam = new HashMap<String, String>();
		// queryParam.put("searchItem", testData.get("searchItem"));

		queryParam.put("searchItem", CommonValues.bannerValues.get("name"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(SEARCH_BANNER_BY_NAME).then().statusCode(Integer.parseInt(testData.get("statusCode")))
				.and()
				.body("banners.banner_id",
						hasItem(Integer.parseInt(CommonValues.bannerValues.get("banner_id"))),
						"banners.name",
						hasItem(CommonValues.bannerValues.get("name")))
				.extract().response();

		response.then().assertThat().body(matchesJsonSchemaInClasspath(PropertyReader
				.getProperty(CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE, SEARCH_BANNER_BY_NAME_JSON_SCHEMA)));

		loggerReport.pass("Response" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.asPrettyString());

	}

	@Test(dataProvider = "getNegativeTestdata")
	public void searchBannerBynameNegative(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("searchItem", testData.get("searchItem"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("store_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(SEARCH_BANNER_BY_NAME).then().statusCode(Integer.parseInt(testData.get("statusCode")))
				.extract().response();

		loggerReport.info("Response \n" + response.asString());
		LOGGER.info("Response : " + response.asString());
		log.debug(response.prettyPrint());
	}
}
