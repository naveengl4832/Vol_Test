package com.onmobile.vol.cataloghub.api.themes.request;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateThemeImageURLRequest {

	@SerializedName("theme_id")
	@Expose
	private Integer themeId;
	@SerializedName("store_id")
	@Expose
	private Integer storeId;
	@SerializedName("theme_name")
	@Expose
	private String themeName;
	@SerializedName("image_url")
	@Expose
	private Object imageUrl;
	@SerializedName("theme_attributes")
	@Expose
	private List<ThemeAttribute> themeAttributes = null;

	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public Object getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(Object imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<ThemeAttribute> getThemeAttributes() {
		return themeAttributes;
	}

	public void setThemeAttributes(List<ThemeAttribute> themeAttributes) {
		this.themeAttributes = themeAttributes;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this, UpdateThemeImageURLRequest.class);
	}

}
