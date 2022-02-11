package com.onmobile.vol.cataloghub.api.themes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.PropertyReader;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class GetThemeAttributes extends RestBaseClass implements Constants {

	/**
	 * 
	 * API is developed to validate theme attributes
	 * 
	 */
	RequestGenerator requestGenarator = new RequestGenerator();

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH,
				GET_THEME_ATTRIBUTE_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void getAttributes(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;
		List<String> attribute_Value = new ArrayList<String>();

		String[] attribute_values = testData.get("attribute_values").split(",");
		for (int i = 0; i < attribute_values.length; i++) {
			attribute_Value.add(attribute_values[i]);
		}
		
		Response response = requestGenarator.getRequest(BASE_URL).when().get(GET_THEME_ATTRIBUTES).then()
				.body("theme_attributes.attribute_id", hasItem(Integer.parseInt(testData.get("attribute_id"))),
						"theme_attributes.attribute_name", hasItem(testData.get("attribute_name")),
						"theme_attributes.attribute_values", hasItem(attribute_Value))
				.and().assertThat().statusCode(Integer.parseInt(testData.get("statusCode"))).and().extract().response();

		response.then().body(matchesJsonSchemaInClasspath(
				PropertyReader.getProperty(CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE, GET_THEME_ATTRIBUTES_SCHEMA)));

		loggerReport.info("Response" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
	}

}
