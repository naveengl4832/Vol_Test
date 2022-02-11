package com.onmobile.vol.cataloghub.api.banner.request;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateBannerRequest {

	@SerializedName("banner_id")
	@Expose
	private Integer bannerId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("language")
	@Expose
	private String language;
	@SerializedName("store_id")
	@Expose
	private Integer storeId;
	@SerializedName("type")
	@Expose
	private String type;
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("image_url")
	@Expose
	private String imageUrl;
	@SerializedName("gif_url")
	@Expose
	private String gifUrl;
	@SerializedName("video_url")
	@Expose
	private String videoUrl;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("navigation_description")
	@Expose
	private String navigationDescription;
	@SerializedName("banner_names")
	@Expose
	private List<BannerName> bannerNames;
	@SerializedName("banner_build_infos")
	@Expose
	private List<BannerBuildInfo> bannerBuildInfos;
	@SerializedName("banner_subscriptions")
	@Expose
	private Boolean bannerSubscriptions;

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGifUrl() {
		return gifUrl;
	}

	public void setGifUrl(String gifUrl) {
		this.gifUrl = gifUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNavigationDescription() {
		return navigationDescription;
	}

	public void setNavigationDescription(String navigationDescription) {
		this.navigationDescription = navigationDescription;
	}

	public List<BannerName> getBannerNames() {
		return bannerNames;
	}

	public void setBannerNames(List<BannerName> bannerNames) {
		this.bannerNames = bannerNames;
	}

	public List<BannerBuildInfo> getBannerBuildInfos() {
		return bannerBuildInfos;
	}

	public void setBannerBuildInfos(List<BannerBuildInfo> bannerBuildInfos) {
		this.bannerBuildInfos = bannerBuildInfos;
	}

	public Boolean getBannerSubscriptions() {
		return bannerSubscriptions;
	}

	public void setBannerSubscriptions(Boolean bannerSubscriptions) {
		this.bannerSubscriptions = bannerSubscriptions;
	}

	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this, UpdateBannerRequest.class);
	}
}
