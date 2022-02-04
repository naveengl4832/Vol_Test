package com.onmobile.vol.cataloghub.api.collectionThemes;

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

public class DeleteThemeCollectionByID extends RestBaseClass implements Constants {

	/**
	 * API is developed to Delete the collection Theme By Collection Theme ID
	 * 
	 */

	RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> pathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, DELETE_THEME_COLLECTION_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void getAttributes(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;
		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("store_id"));
		pathParam.put("theme_collection_id", CommonValues.theme_Collection_Id);

		Response response = requestGenarator.getRequest_PathParam_URL(BASE_URL, pathParam).when()
				.delete(DELETE_THEME_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.extract().response();

		LOGGER.info(" Theme_Collection_ID is deleted --> \n " + CommonValues.theme_Collection_Id + "  ---> "
				+ response.getStatusCode());
		loggerReport.pass("statuscode : " + response.getStatusCode() + "Theme_id is deleted");
	}

}
