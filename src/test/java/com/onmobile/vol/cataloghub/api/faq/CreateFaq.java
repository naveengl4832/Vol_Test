package com.onmobile.vol.cataloghub.api.faq;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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

public class CreateFaq extends RestBaseClass implements Constants {

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
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_FAQ_XL_PATH, CREATE_FAQ_POSITIVE_SHEET);
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

		requestBody.put("text", testData.get("text"));
		requestBody.put("language", testData.get("language") + "_" + RandomStringUtils.randomAlphanumeric(2));
		requestBody.put("url", testData.get("url"));

		Response response = requestGenarator.getRequestWithPathReqobject(BASE_URL, PathParam, requestBody).when()
				.post(CREATE_FAQ).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("language",
						containsString(testData.get("language")), "text", equalTo(testData.get("text")), "url",
						equalTo(testData.get("url")), "last_updated", is(notNullValue()), "faq_id", is(notNullValue()))
				.and().extract().response();

		CommonValues.faqValues.put("faq_id", response.jsonPath().getString("faq_id"));
		CommonValues.faqValues.put("text", response.jsonPath().getString("text"));
		CommonValues.faqValues.put("url", response.jsonPath().getString("url"));
		CommonValues.faqValues.put("language", response.jsonPath().getString("language"));
		CommonValues.faqValues.put("store_id", STORE_ID);

		loggerReport.info("Chart is created ---> " + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.asPrettyString());

	}
}
