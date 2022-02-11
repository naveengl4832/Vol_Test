package com.onmobile.vol.cataloghub.api.collectionThemes;

import static org.hamcrest.Matchers.hasKey;

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

public class CreateThemeCollection extends RestBaseClass implements Constants {

	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> PathParam;
	public Map<String, String> requestObject;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, CREATE_THEME_COLLECTION_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void createThemByThemeName(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		PathParam = new HashMap<String, String>();
		PathParam.put("store_id", STORE_ID);

		requestObject = new HashMap<String, String>();
		requestObject.put("theme_collection_name",
				testData.get("theme_collection_name") + "_" + RandomStringUtils.randomAlphanumeric(10));

		Response response = requestGenarator.getRequestWithPathReqobject(BASE_URL, PathParam, requestObject).when()
				.post(CREATE_THEME_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("$", hasKey("theme_collection_id"), "$", hasKey("store_id"), "$", hasKey("theme_collection_name"))
				.and().extract().response();

		CommonValues.themeCollectionValues.put("theme_collection_id",
				response.jsonPath().getString("theme_collection_id"));
		CommonValues.themeCollectionValues.put("theme_collection_name",
				response.jsonPath().getString("theme_collection_name"));
		CommonValues.themeCollectionValues.put("store_id", response.jsonPath().getString("store_id"));

		loggerReport.info("Theme Collection ID is created for the given theme Collection Name ----     "
				+ requestObject.get("theme_collection_name") + "\n" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());

	}

}
