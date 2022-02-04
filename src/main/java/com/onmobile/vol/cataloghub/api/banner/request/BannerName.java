package com.onmobile.vol.cataloghub.api.banner.request;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerName {

	@SerializedName("language_name")
	@Expose
	private String languageName;
	@SerializedName("name")
	@Expose
	private String name;

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this, BannerName.class);
	}
}
