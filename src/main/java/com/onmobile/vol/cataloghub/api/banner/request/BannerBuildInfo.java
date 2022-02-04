package com.onmobile.vol.cataloghub.api.banner.request;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerBuildInfo {

	@SerializedName("banner_attribute")
	@Expose
	private String bannerAttribute;
	@SerializedName("banner_attribute_value")
	@Expose
	private String bannerAttributeValue;

	public String getBannerAttribute() {
	return bannerAttribute;
	}

	public void setBannerAttribute(String bannerAttribute) {
	this.bannerAttribute = bannerAttribute;
	}

	public String getBannerAttributeValue() {
	return bannerAttributeValue;
	}

	public void setBannerAttributeValue(String bannerAttributeValue) {
	this.bannerAttributeValue = bannerAttributeValue;
	}
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this, BannerBuildInfo.class);
	}
}
