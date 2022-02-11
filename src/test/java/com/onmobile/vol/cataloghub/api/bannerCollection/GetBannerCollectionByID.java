package com.onmobile.vol.cataloghub.api.bannerCollection;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

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

public class GetBannerCollectionByID extends RestBaseClass implements Constants {

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
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH,
				GET_BANNER_COLLECTION_BY_ID_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH,
				GET_BANNER_COLLECTION_BY_ID_NEGATIVE_SHEET);

		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void getBannerCollectionsById(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", CommonValues.bannerCollectionValues.get("store_id"));
		pathParam.put("banner_collection_id", CommonValues.bannerCollectionValues.get("banner_collection_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when().get(GET_COLLECTION_BY_ID)
				.then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("store_id", equalTo(Integer.parseInt(STORE_ID)), "banner_collection_name",
						equalTo(CommonValues.bannerCollectionValues.get("banner_collection_name")), "banner_collection_id",
						equalTo(Integer.parseInt(CommonValues.bannerCollectionValues.get("banner_collection_id"))))
				.extract().response();

		response.then().assertThat().body(matchesJsonSchemaInClasspath(
				PropertyReader.getProperty(CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE, GET_COLLECTION_BANNER_BY_ID_JSON_SCHEMA)));

		loggerReport.pass("Response" + response.prettyPrint());
		loggerReport.pass("Veified the Banner Collection ID : " + response.jsonPath().getString("banner_collection_id"));
		LOGGER.info("Response : " + response.prettyPrint());

	}

	@Test(dataProvider = "getNegativeTestdata")
	public void getBannerCollectionsByID_NegativeScenarios(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("store_id"));
		pathParam.put("banner_collection_id", testData.get("banner_collection_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when().get(GET_COLLECTION_BY_ID)
				.then().statusCode(Integer.parseInt(testData.get("statusCode"))).extract().response();

		loggerReport.info("Response \n" + response.asString());
		LOGGER.info("Response : " + response.asString());
		log.debug(response.prettyPrint());
	}

}