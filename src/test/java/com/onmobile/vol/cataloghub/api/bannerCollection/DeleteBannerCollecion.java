package com.onmobile.vol.cataloghub.api.bannerCollection;

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

public class DeleteBannerCollecion extends RestBaseClass implements Constants {

	/**
	 * API is developed to Delete the Themes By Theme-ID
	 * 
	 */

	RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> pathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH, DELETE_BANNER_COLLECTION_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void getAttributes(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;
		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", STORE_ID);
		pathParam.put("banner_collection_id", CommonValues.bannerCollectionValues.get("banner_collection_id"));

		Response response = requestGenarator.getRequest_PathParam_URL(BASE_URL, pathParam).when()
				.delete(DELETE_BANNER_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.extract().response();

		LOGGER.info(" Banner Collection  ID is deleted --> "
				+ CommonValues.bannerCollectionValues.get("banner_collection_id") + "  ---> \n"
				+ response.getStatusCode());
		loggerReport.pass("statucode : " + response.getStatusCode() + "Theme_id is deleted");
		log.debug("Banner collection ID is deleted ----> " + response.asPrettyString());

	}
}
