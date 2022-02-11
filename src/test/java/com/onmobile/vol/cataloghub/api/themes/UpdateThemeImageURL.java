package com.onmobile.vol.cataloghub.api.themes;

import static org.hamcrest.Matchers.hasKey;

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
import com.onmobile.vol.cataloghub.api.themes.request.ThemeAttribute;
import com.onmobile.vol.cataloghub.api.themes.request.UpdateThemeImageURLRequest;

import io.restassured.response.Response;

public class UpdateThemeImageURL extends RestBaseClass implements Constants {

	/**
	 * 
	 * API is developed to update theme by theme_id
	 * 
	 */

	public RequestGenerator requestGenarator = new RequestGenerator();
	public UpdateThemeImageURLRequest updateThemeRequest;
	public ThemeAttribute themeAttribute;
	public Map<String, String> PathParam;
	public Map<String, String> queryParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, UPDATE_THEME_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void updateThemeImageURL(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		updateThemeRequest = new UpdateThemeImageURLRequest();
		themeAttribute = new ThemeAttribute();
		try {
			BeanUtils.populate(updateThemeRequest, testData);
			BeanUtils.populate(themeAttribute, testData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		PathParam = new HashMap<String, String>();
		PathParam.put("store_id", CommonValues.themeValues.get("store_id"));
		PathParam.put("theme_id", CommonValues.themeValues.get("theme_id"));

		queryParam = new HashMap<String, String>();
		queryParam.put("image_url", testData.get("image_url") + CommonValues.themeValues.get("theme_id") + ".jpeg");

		updateThemeRequest.setImageUrl(testData.get("image_url_1"));
		updateThemeRequest.setStoreId(Integer.parseInt(CommonValues.themeValues.get("store_id")));
		updateThemeRequest.setThemeId(Integer.parseInt(CommonValues.themeValues.get("theme_id")));
		updateThemeRequest.setThemeName(CommonValues.themeValues.get("theme_name"));

		themeAttribute.setAttributeId(Integer.parseInt(testData.get("attribute_id")));
		themeAttribute.setAttributeName(testData.get("attribute_name"));
		themeAttribute.setAttributeValue(testData.get("attribute_value"));

		List<ThemeAttribute> listThemeAttribute = new ArrayList<ThemeAttribute>();
		listThemeAttribute.add(themeAttribute);

		updateThemeRequest.setThemeAttributes(listThemeAttribute);

		Response response = requestGenarator
				.getRequestWithPathQueryReqobject(queryParam, BASE_URL, PathParam, updateThemeRequest.toString()).when()
				.put(UPDATE_THEME_BY_THEME_ID).then().body("$", hasKey("image_url")).extract().response();

		CommonValues.themeValues.put("image_url", response.jsonPath().getString("image_url"));

		Assert.assertTrue(response.jsonPath().get("image_url").toString()
				.contains(CommonValues.themeValues.get("theme_id") + ".jpeg"));
		loggerReport.info("Image URL is updated  - \n " + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());

	}

}
