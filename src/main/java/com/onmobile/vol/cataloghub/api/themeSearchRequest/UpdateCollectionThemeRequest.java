package com.onmobile.vol.cataloghub.api.themeSearchRequest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCollectionThemeRequest {

	@SerializedName("store_id")
	@Expose
	private Integer storeId;
	@SerializedName("end_datetime")
	@Expose
	@JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "yyyy-MM-dd HH:mm a z")
	private Object endDatetime;
	@SerializedName("start_datetime")
	@Expose
	@JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "yyyy-MM-dd HH:mm a z")
	private Object startDatetime;
	@SerializedName("theme_collection_name")
	@Expose
	private String themeCollectionName;
	@SerializedName("theme_collection_id")
	@Expose
	private Integer themeCollectionId;
	@SerializedName("theme_collection_themes")
	@Expose
	private List<ThemeCollectionTheme> themeCollectionThemes = null;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Object getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Object getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Object startDatetime) {
		this.startDatetime = startDatetime;
	}

	public String getThemeCollectionName() {
		return themeCollectionName;
	}

	public void setThemeCollectionName(String themeCollectionName) {
		this.themeCollectionName = themeCollectionName;
	}

	public Integer getThemeCollectionId() {
		return themeCollectionId;
	}

	public void setThemeCollectionId(Integer themeCollectionId) {
		this.themeCollectionId = themeCollectionId;
	}

	public List<ThemeCollectionTheme> getThemeCollectionThemes() {
		return themeCollectionThemes;
	}

	public void setThemeCollectionThemes(List<ThemeCollectionTheme> themeCollectionThemes) {
		this.themeCollectionThemes = themeCollectionThemes;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this, UpdateCollectionThemeRequest.class);
	}
}
