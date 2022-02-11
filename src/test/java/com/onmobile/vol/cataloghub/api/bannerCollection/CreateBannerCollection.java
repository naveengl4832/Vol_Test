package com.onmobile.vol.cataloghub.api.bannerCollection;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.CommonValues;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;

import io.restassured.response.Response;

public class CreateBannerCollection extends RestBaseClass implements Constants {

	/**
	 * 
	 * API is developed to create Charts
	 * 
	 */
	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> PathParam;
	public Map<String, String> requestBody;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH, CREATE_BANNER_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void createChatByChatName(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;
		PathParam = new HashMap<String, String>();
		PathParam.put("store_id", STORE_ID);

		requestBody = new HashMap<String, String>();

		requestBody.put("banner_collection_name",
				testData.get("banner_collection_name") + "_" + RandomStringUtils.randomAlphanumeric(10));
		requestBody.put("banner_collection_type", testData.get("banner_collection_type"));

		Response response = requestGenarator.getRequestWithPathReqobject(BASE_URL, PathParam, requestBody).when()
				.post(CREATE_BANNER_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("storeId", equalTo(Integer.parseInt(STORE_ID)), "banner_collection_name",
						containsString(testData.get("banner_collection_name")), "banner_collection_type",
						equalTo(testData.get("banner_collection_type")))
				.and().extract().response();

		
		CommonValues.bannerCollectionValues.put("banner_collection_id", response.jsonPath().getString("banner_collection_id"));
		CommonValues.bannerCollectionValues.put("banner_collection_name", response.jsonPath().getString("banner_collection_name"));
		CommonValues.bannerCollectionValues.put("banner_collection_type", response.jsonPath().getString("banner_collection_type"));
		CommonValues.bannerCollectionValues.put("store_id", response.jsonPath().getString("storeId"));

		loggerReport.info("Banner Collection ID is created ---> " + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.asPrettyString());

	}
}