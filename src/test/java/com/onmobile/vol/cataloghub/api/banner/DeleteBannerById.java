package com.onmobile.vol.cataloghub.api.banner;

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

public class DeleteBannerById extends RestBaseClass implements Constants {

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
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH, DELETE_BANNER_ID_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void deleteBannerByID(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;
		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", CommonValues.bannerValues.get("store_id"));
		pathParam.put("banner_id", CommonValues.bannerValues.get("banner_id"));

		Response response = requestGenarator.getRequest_PathParam_URL(BASE_URL, pathParam).when()
				.delete(DELETE_BANNER_BY_ID).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.extract().response();

		LOGGER.info(" Banner ID is deleted --> " + CommonValues.bannerValues.get("banner_id") + "  ---> \n" + response.getStatusCode());
		loggerReport.pass("statucode : " + response.getStatusCode() + "Banner ID is deleted");
		log.debug("Banner ID is deleted ----> "+ response.asPrettyString());

	}

}