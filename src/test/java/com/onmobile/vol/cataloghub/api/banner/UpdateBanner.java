package com.onmobile.vol.cataloghub.api.banner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.onmobile.api.config.Constants;
import com.onmobile.api.config.ExcelConfig;
import com.onmobile.api.helper.CommonValues;
import com.onmobile.api.helper.RequestGenerator;
import com.onmobile.api.helper.RestBaseClass;
import com.onmobile.vol.cataloghub.api.banner.request.BannerBuildInfo;
import com.onmobile.vol.cataloghub.api.banner.request.BannerName;
import com.onmobile.vol.cataloghub.api.banner.request.UpdateBannerRequest;

import io.restassured.response.Response;

public class UpdateBanner extends RestBaseClass implements Constants {
	/**
	 * 
	 * API is developed to update banner as per the request Details
	 * 
	 */

	public RequestGenerator requestGenarator = new RequestGenerator();
	public UpdateBannerRequest updateBannerRequest;

	public BannerName banner_name;
	public BannerBuildInfo bannerBuildInfo;
	public Map<String, String> PathParam;
	public List<BannerBuildInfo> listBannerBuildInfo;
	public List<BannerName> listBannerName;

	@DataProvider(name = "getPositiveTestdata")
	public Object[][] getPositiveTestData() {
		sourceMethod = "getTestData";
		LOGGER.entering(sourceClass, sourceMethod);
		Object[][] testData = ExcelConfig.getTestDataAsMap(GET_BANNER_XL_PATH, UPDATE_BANNER_POSITIVE_SHEET);
		LOGGER.entering(sourceClass, sourceMethod);
		return testData;
	}

	@Test(dataProvider = "getPositiveTestdata")
	public void updateTheme(Map<String, String> testDatList[]) {
		Map<String, String> testData = testDatList[0];
		while (testData.values().remove(""))
			;

		updateBannerRequest = new UpdateBannerRequest();
		banner_name = new BannerName();
		bannerBuildInfo = new BannerBuildInfo();

		try {
			BeanUtils.populate(updateBannerRequest, testData);
			BeanUtils.populate(banner_name, testData);
			BeanUtils.populate(bannerBuildInfo, testData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		PathParam = new HashMap<String, String>();
		PathParam.put("store_id", CommonValues.bannerValues.get("store_id"));

		updateBannerRequest.setBannerId(Integer.parseInt(CommonValues.bannerValues.get("banner_id")));
		updateBannerRequest.setName(CommonValues.bannerValues.get("name"));
		updateBannerRequest.setLanguage(CommonValues.bannerValues.get("language"));
		updateBannerRequest.setStoreId(Integer.parseInt(CommonValues.bannerValues.get("store_id")));
		updateBannerRequest.setType(testData.get("type"));
		updateBannerRequest.setId(testData.get("id"));
		updateBannerRequest.setImageUrl(testData.get("image_url"));
		updateBannerRequest.setGifUrl(testData.get("gif_url"));
		updateBannerRequest.setVideoUrl(testData.get("video_url"));

		updateBannerRequest.setDescription((testData.get("description")));
		updateBannerRequest.setNavigationDescription(testData.get("navigation_description"));
		updateBannerRequest.setBannerSubscriptions(null);

		String[] languages = testData.get("banner_names.language_name").split(",");
		String[] names = testData.get("banner_names.name").split(",");
		String[] bannerAttribute = testData.get("banner_build_infos.banner_attribute").split(",");
		String[] attributeValue = testData.get("banner_build_infos.banner_attribute_value").split(",");

		listBannerName = new ArrayList<BannerName>();
		listBannerBuildInfo = new ArrayList<BannerBuildInfo>();

		for (int i = 0; i < languages.length; i++) {

			banner_name = new BannerName();
			bannerBuildInfo = new BannerBuildInfo();
			banner_name.setName(names[i]);
			banner_name.setLanguageName(languages[i]);
			listBannerName.add(banner_name);

			bannerBuildInfo.setBannerAttribute(bannerAttribute[i]);
			bannerBuildInfo.setBannerAttributeValue(attributeValue[i]);

			listBannerBuildInfo.add(bannerBuildInfo);

		}

		updateBannerRequest.setBannerBuildInfos(listBannerBuildInfo);
		updateBannerRequest.setBannerNames(listBannerName);

		Response response = requestGenarator.getRequest(BASE_URL, PathParam, updateBannerRequest.toString()).when()
				.put(UPDATE_BANNER_BY_ID).then().statusCode(Integer.parseInt(testData.get("statusCode"))).and()
				.body("name", equalTo(CommonValues.bannerValues.get("name")), "banner_id",
						equalTo(Integer.parseInt(CommonValues.bannerValues.get("banner_id"))), "image_url",
						equalTo(CommonValues.bannerValues.get("image_url")),

						"banner_names.name", hasItem(CommonValues.bannerValues.get("name")))
				.and().extract().response();

		loggerReport.info("Banner is updated ---->  \n" + response.prettyPrint());
		LOGGER.info("Response : " + response.prettyPrint());
		log.debug(response.asPrettyString());
		log.debug(response.statusCode());
	}

}