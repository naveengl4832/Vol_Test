package com.onmobile.vol.cataloghub.api.imageupload;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.config.ImageExtensions;
import com.onmobile.api.helper.CommonValues;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;

import io.restassured.response.Response;

public class UploadThemeImage extends RestBaseClass implements Constants {
	/**
	 * 
	 * Post Request to upload the images using form data.
	 * 
	 */
	public RequestGenerator requestGenarator = new RequestGenerator();
	public Map<String, String> pathParam;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_THEME_XL_PATH, UPLOAD_THEME_IMAGE_POSTIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void uploadThemeImage(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		pathParam = new HashMap<String, String>();
		pathParam.put("store_id", testData.get("store_id"));
		pathParam.put("theme_id", CommonValues.theme_Id);

		Response response = requestGenarator
				.getRequestWithMultiPart(BASE_URL, pathParam, FORM_DATA_KEY, THEME_IMAGE,
						ImageExtensions.IMAGE + "/" + ImageExtensions.JPEG)
				.when().post(UPLOAD_THEME_IMAGE).then().extract().response();

		System.out.println(response.asPrettyString());

		loggerReport.info("Theme Image has been uploaded..." + response.prettyPrint());

		LOGGER.info("Response : " + response.prettyPrint());

	}

}
