package com.onmobile.vol.cataloghub.api.collectionThemes;

import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.CommonValues;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;
import com.onmobile.vol.cataloghub.api.themeSearchRequest.ThemeCollectionTheme;
import com.onmobile.vol.cataloghub.api.themeSearchRequest.UpdateCollectionThemeRequest;

import io.restassured.response.Response;

public class UpdateThemeCollection extends RestBaseClass implements Constants {

	/**
	 * 
	 * API is developed to update theme as per the request Details
	 * 
	 */

	public RequestGenerator requestGenarator = new RequestGenerator();
	public UpdateCollectionThemeRequest updateCollectionThemeRequest;
	public ThemeCollectionTheme themeCollectionTheme;
	public Map<String, String> PathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, UPDATE_THEME_COLLECTION_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void updateTheme(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		updateCollectionThemeRequest = new UpdateCollectionThemeRequest();
		themeCollectionTheme = new ThemeCollectionTheme();
		try {
			BeanUtils.populate(updateCollectionThemeRequest, testData);
			BeanUtils.populate(themeCollectionTheme, testData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		PathParam = new HashMap<String, String>();
		PathParam.put("store_id", CommonValues.themeCollectionValues.get("store_id"));

		updateCollectionThemeRequest.setStartDatetime(CommonValues.getDateAndTime());
		updateCollectionThemeRequest.setEndDatetime(CommonValues.getDateAndTime());
		updateCollectionThemeRequest.setStoreId(Integer.parseInt(CommonValues.themeCollectionValues.get("store_id")));
		updateCollectionThemeRequest
				.setThemeCollectionId(Integer.parseInt(CommonValues.themeCollectionValues.get("theme_collection_id")));
		updateCollectionThemeRequest
				.setThemeCollectionName(CommonValues.themeCollectionValues.get("theme_collection_name"));

		themeCollectionTheme.setThemeId(CommonValues.themeValues.get("theme_id"));
		themeCollectionTheme.setThemeName(CommonValues.themeValues.get("theme_name"));

		List<ThemeCollectionTheme> themeCollectionThemeList = new ArrayList<ThemeCollectionTheme>();
		themeCollectionThemeList.add(themeCollectionTheme);

		updateCollectionThemeRequest.setThemeCollectionThemes(themeCollectionThemeList);

		Response response = requestGenarator.getRequest(BASE_URL, PathParam, updateCollectionThemeRequest.toString())
				.when().put(UPDATE_THEME_COLLECTION).then().statusCode(Integer.parseInt(testData.get("statusCode")))
				.body("theme_collection_name", equalTo(CommonValues.themeCollectionValues.get("theme_collection_name")),
						"theme_collection_id",
						equalTo(Integer.parseInt(CommonValues.themeCollectionValues.get("theme_collection_id"))))
				.extract().response();

		List<Object> list = response.jsonPath().getList("theme_collection_themes");

		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(response.jsonPath().get("theme_collection_themes[" + i + "].theme_id"),
					Integer.parseInt(CommonValues.themeValues.get("theme_id")));
			Assert.assertEquals(response.jsonPath().get("theme_collection_themes[" + i + "].theme_name"),
					CommonValues.themeValues.get("theme_name"));
		}
		loggerReport.info("Theme is updated with the given - \n " + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());

	}
}
