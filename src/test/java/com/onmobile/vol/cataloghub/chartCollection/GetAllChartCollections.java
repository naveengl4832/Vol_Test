package com.onmobile.vol.cataloghub.chartCollection;

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

public class GetAllChartCollections extends RestBaseClass implements Constants {

	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> pathParam;
	public Map<String, String> queryParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_CHART_XL_PATH,
				GET__ALL_CHART_COLLECTION__POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestdata() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_CHART_XL_PATH,
				GET__ALL_CHART_COLLECTION__NEGATIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void getAllBanner(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", CommonValues.chartCollectionValues.get("store_id"));

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(GET_ALL_CHART_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("chart_collection_id",
						hasItem(Integer.parseInt(CommonValues.chartCollectionValues.get("chart_collection_id"))),
						"chart_collection_name",
						hasItem(CommonValues.chartCollectionValues.get("chart_collection_name")), "store_id",
						hasItem(Integer.parseInt(CommonValues.chartCollectionValues.get("store_id"))))
				.extract().response();

		response.then().assertThat().body(matchesJsonSchemaInClasspath(PropertyReader
				.getProperty(CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE, GET_ALL_CHART_COLLECTION_JSON_SCHEMA)));

		loggerReport.pass("Response" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.asPrettyString());

	}

	@Test(dataProvider = "getNegativeTestdata")
	public void NegativeGetAllBanner(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("store_id"));

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when()
				.get(GET_ALL_CHART_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.extract().response();

		loggerReport.pass("Response" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.prettyPrint());

	}

}
