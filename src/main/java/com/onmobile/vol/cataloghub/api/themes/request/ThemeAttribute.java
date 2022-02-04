package com.onmobile.vol.cataloghub.api.themes.request;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThemeAttribute {

	@SerializedName("attribute_id")
	@Expose
	private Integer attributeId;
	@SerializedName("attribute_name")
	@Expose
	private String attributeName;
	@SerializedName("attribute_value")
	@Expose
	private String attributeValue;

	public Integer getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this, ThemeAttribute.class);
	}
}
