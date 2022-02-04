package com.onmobile.vol.cataloghub.api.themeSearchRequest;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThemeCollectionTheme {

	@SerializedName("theme_id")
	@Expose
	private String themeId;
	@SerializedName("theme_name")
	@Expose
	private String themeName;

	public String getThemeId() {
		return themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this, ThemeCollectionTheme.class);
	}

}
