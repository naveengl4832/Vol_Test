package com.onmobile.vol.cataloghub.api.faq;

import static org.hamcrest.Matchers.hasItem;

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

public class GetFaq extends RestBaseClass implements Constants {

	/**
	 * 
	 * Get Request with Catalog-Hub Api
	 * 
	 */

	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> queryParam;
	public Map<String, String> pathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_FAQ_XL_PATH, GET_FAQ_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@DataProvider(name = "getNegativeTestdata")
	public Object[][] getNegativeTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_FAQ_XL_PATH, GET_FAQ_NEGATIVE_SHEET);

		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void getFaqByFaqId(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));
		queryParam.put("language", CommonValues.faqValues.get("language"));


		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", CommonValues.faqValues.get("store_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when().get(GET_FAQ)
				.then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body( "faqs.language",
						hasItem(CommonValues.faqValues.get("language")), "faqs.faq_id",
						hasItem(Integer.parseInt(CommonValues.faqValues.get("faq_id"))), "faqs.url",
						hasItem(CommonValues.faqValues.get("url")))
				.extract().response();

	
		loggerReport.pass("Response" + response.prettyPrint());
		loggerReport.pass("Veified the ChartID : " + response.jsonPath().getString("chart_id"));
		LOGGER.info("Response : " + response.prettyPrint());

	}

	@Test(dataProvider = "getNegativeTestdata")
	public void getFaqByFaqIdNegativeTest(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		queryParam = new HashMap<String, String>();
		queryParam.put("response", testData.get("response"));
		queryParam.put("language", testData.get("language"));

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("store_id"));

		Response response = requestGenarator.getRequest(queryParam, pathParam, BASE_URL).when().get(GET_FAQ)
				.then().statusCode(Integer.parseInt(testData.get("statusCode"))).extract().response();

		loggerReport.info("Response \n" + response.asString());
		LOGGER.info("Response : " + response.asString());
		log.debug(response.prettyPrint());
	}
}