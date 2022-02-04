package com.onmobile.vol.cataloghub.api.themes.request;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateThemeRequest {

	@SerializedName("theme_name")
	@Expose
	private String themeName;

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this, CreateThemeRequest.class);
	}
}
