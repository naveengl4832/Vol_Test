package com.onmobile.vol.cataloghub.api.themes;

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
import com.onmobile.vol.cataloghub.api.themes.request.ThemeAttribute;
import com.onmobile.vol.cataloghub.api.themes.request.UpdateThemeImageURLRequest;

import io.restassured.response.Response;

public class UpdateTheme extends RestBaseClass implements Constants {
	/**
	 * 
	 * API is developed to update theme as per the request Details
	 * 
	 */

	public RequestGenerator requestGenarator = new RequestGenerator();
	public UpdateThemeImageURLRequest updateThemeRequest;
	public ThemeAttribute themeAttribute;
	public Map<String, String> PathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, UPDATE_THEME_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void updateTheme(Map<String, String> testDatList[]) {
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

		updateThemeRequest.setImageUrl(CommonValues.themeValues.get("image_url"));
		updateThemeRequest.setStoreId(Integer.parseInt(CommonValues.themeValues.get("store_id")));
		updateThemeRequest.setThemeId(Integer.parseInt(CommonValues.themeValues.get("theme_id")));
		updateThemeRequest.setThemeName(CommonValues.themeValues.get("theme_name"));

		themeAttribute.setAttributeId(Integer.parseInt(testData.get("attribute_id")));
		themeAttribute.setAttributeName(testData.get("attribute_name"));
		themeAttribute.setAttributeValue(testData.get("attribute_value"));

		List<ThemeAttribute> listThemeAttribute = new ArrayList<ThemeAttribute>();
		listThemeAttribute.add(themeAttribute);

		updateThemeRequest.setThemeAttributes(listThemeAttribute);

		Response response = requestGenarator.getRequest(BASE_URL, PathParam, updateThemeRequest.toString()).when()
				.put(UPDATE_THEME).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("theme_name", equalTo(CommonValues.themeValues.get("theme_name")), "theme_id",
						equalTo(Integer.parseInt(CommonValues.themeValues.get("theme_id"))), "image_url",
						equalTo(CommonValues.themeValues.get("image_url")))
				.and().extract().response();

		List<Object> list = response.jsonPath().getList("theme_attributes");

		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(response.jsonPath().get("theme_attributes[" + i + "].attribute_value"),
					testData.get("attribute_value"));
		}
		loggerReport.info("Theme is updated with the latest image url and theme ID ---->  \n" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.asPrettyString());
		log.debug(response.statusCode());
	}

}
