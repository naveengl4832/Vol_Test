package com.onmobile.vol.cataloghub.api.themes;

import static org.hamcrest.Matchers.hasKey;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.CommonValues;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;
import com.onmobile.vol.cataloghub.api.themes.request.CreateThemeRequest;

import io.restassured.response.Response;

public class CreateTheme extends RestBaseClass implements Constants {

	/**
	 * 
	 * API is developed to create theme
	 * 
	 */
	public RequestGenerator requestGenarator = new RequestGenerator();
	public CreateThemeRequest createThemeRequest;
	public Map<String, String> PathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, CREATE_THEME_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestdata() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, CREATE_THEME_NEGATIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void createThemByThemeName(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		createThemeRequest = new CreateThemeRequest();
		try {
			BeanUtils.populate(createThemeRequest, testData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		createThemeRequest.setThemeName(testData.get("theme_Name") + " _ " + RandomStringUtils.randomAlphanumeric(10));

		PathParam = new HashMap<String, String>();
		PathParam.put("store_id", STORE_ID);

		Response response = requestGenarator.getRequest(BASE_URL, PathParam, createThemeRequest.toString()).when()
				.post(CREATE_THEME_BY_THEME_NAME).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("$", hasKey("theme_id"), "$", hasKey("store_id"), "$", hasKey("theme_name")).and().extract()
				.response();

		CommonValues.themeValues.put("theme_id", response.jsonPath().getString("theme_id"));
		CommonValues.themeValues.put("theme_name",response.jsonPath().getString("theme_name"));
		CommonValues.themeValues.put("store_id",response.jsonPath().getString("store_id"));
		
		loggerReport.info("Theme ID is created for the given themeName - \n " + createThemeRequest.getThemeName() + "  "
				+ response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.asPrettyString());

	}

}
