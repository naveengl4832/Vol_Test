package com.onmobile.vol.cataloghub.api.chartCollection;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.CommonValues;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;

import io.restassured.response.Response;

public class DeleteChartCollectionByID extends RestBaseClass implements Constants {

	/**
	 * API is developed to Delete Chart collection id
	 * 
	 */

	RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> pathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_CHART_XL_PATH, DELETE_CHART_COLLECTION_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void deleteChartCollectionById(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;
		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", CommonValues.chartCollectionValues.get("store_id"));
		pathParam.put("chart_collection_id", CommonValues.chartCollectionValues.get("chart_collection_id"));

		Response response = requestGenarator.getRequest_PathParam_URL(BASE_URL, pathParam).when()
				.delete(DELETE_CHART_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.extract().response();

		LOGGER.info(
				" Chart Collection ID is deleted --> " + CommonValues.chartCollectionValues.get("chart_collection_id")
						+ "  ---> \n" + response.getStatusCode());
		loggerReport.pass("statucode : " + response.getStatusCode() + "chart collection is deleted");
		log.debug("Chart collection ID is deleted ----> " + response.asPrettyString());

	}

}