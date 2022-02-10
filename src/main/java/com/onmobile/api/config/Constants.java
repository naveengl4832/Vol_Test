package com.onmobile.api.config;

public interface Constants {

	/****** HEADERS *********/
	String APPLICATION_JSON = "application/json";
	String MULTIPART_FORM_DATA = "multipart/form-data";
	String FORM_DATA_TYPE = "multipart/form-data";
	String BASE_URL = "BASE_URL";
	String ENVIRONMENT = System.getProperty("env");
	String STORE_ID = System.getProperty("store_id");

	/********** URL PATH PROPERTIES **************/
	String URL_PROPERTIES_PATH = "./src/main/java/com/onmobile/api/config/URL_Config.properties";

	/**** Excel Path ******/
	String GET_THEME_XL_PATH = "./TestData/Catalog/Theme_Api.xlsx";
	String GET_CHART_XL_PATH = "./TestData/Catalog/Chat_Api.xlsx";
	String GET_BANNER_XL_PATH = "./TestData/Catalog/Banner_Api.xlsx";
	String GET_FAQ_XL_PATH = "./TestData/Catalog/Faq.xlsx";

	/**************** Images Path **********************/
	String IMAGE_PATH = "./src/main/java/com/onmobile/api/config/ThemeImages.properties";

	/**** JSON-SCHEMA PATH ***********/
	String CATALOG_HUB_JSON_SCHEMA_PROPERTY_FILE = "./src/main/java/com/onmobile/api/config/JsonSchemaConfig.properties";
	String CATALOG_HUB_THEMES = "CATALOG_HUB_THEMES";
	String GET_THEME_ATTRIBUTES_SCHEMA = "GET_THEME_ATTRIBUTES_SCHEMA";
	String CREATE_CHART_JSON_SCHEMA = "CREATE_CHART_JSON_SCHEMA";
	String GET_ALL_BANNER_JSON_SCHEMA = "GET_ALL_BANNER_JSON_SCHEMA";
	String GET_BANNER_BY_ID_JSON_SCHEMA = "GET_BANNER_BY_ID_JSON_SCHEMA";
	String GET_CHART_COLLECTION_BY_ID_JSON_SCHEMA = "GET_CHART_COLLECTION_BY_ID_JSON_SCHEMA";
	String GET_ALL_CHART_COLLECTION_JSON_SCHEMA = "GET_ALL_CHART_COLLECTION_JSON_SCHEMA";
	String SEARCH_BY_CHART_COLLECTIONNAME_JSON_SCHEMA = "SEARCH_BY_CHART_COLLECTIONNAME_JSON_SCHEMA";
	String SEARCH_BY_CHART_NAME_JSON_SCHEMA = "SEARCH_BY_CHART_NAME_JSON_SCHEMA";
	
	String GET_COLLECTION_BANNER_BY_ID_JSON_SCHEMA="GET_COLLECTION_BANNER_BY_ID_JSON_SCHEMA";

	/*************** Get Theme BY ID **************/
	String GET_CATALOG_HUB_THEAMS = "/catalog-hub/{store_id}/themes/{theme_id}";
	String GET_THEME_BY_ID_POSITIVE_SHEET = "CATALOG_HUB_POSITIVE_SHEET";
	String GET_THEME_BY_ID_NAGATIVE_SHEET = "CATALOG_HUB_NEGATIVE_SHEET";

	/********************* Get Theme Attribute ********************/
	String GET_THEME_ATTRIBUTE_POSITIVE_SHEET = "GET_THEME_ATTRIBUTE_POSITIVE";
	String GET_THEME_ATTRIBUTES = "/catalog-hub/configurations/theme_attributes";

	/********** Create Theme **************************************/
	String CREATE_THEME_BY_THEME_NAME = "/catalog-hub/{store_id}/themes";
	String CREATE_THEME_POSITIVE_SHEET = "CREATE_THEME_POSITIVE_SHEET";
	String CREATE_THEME_NEGATIVE_SHEET = "CREATE_THEME_NEGATIVE_SHEET";

	/************ Delete Theme ********/
	String DELETE_THEME_BY_THEME_ID_POSITIVE_SHEET = "DELETE_THEME_BY_THEMEID_POSITIV";
	String DELETE_THEME_BY_THEME_ID = "/catalog-hub/{store_id}/themes/{theme_id}";

	/****** Update Theme IMAGE ***********************************************/
	String UPDATE_THEME_POSITIVE_SHEET = "UPDATE_THEME_POSITIVE_SHEET";
	String UPDATE_THEME_BY_THEME_ID = "/catalog-hub/{store_id}/themes/{theme_id}/image";

	/********** Get All Themes *******************************************/
	String GET_ALL_THEMES_POSITIVE_SHEET = "GET_ALL_THEMES_POSITIVE_SHEET";
	String GET_ALL_THEMES = "/catalog-hub/{store_id}/themes";
	String GET_ALL_THEMES_SCHEMA = "GET_ALL_THEMES_SCHEMA";

	/******** UPDATE THEME ***********************************************/
	String UPDATE_THEME = "/catalog-hub/{store_id}/themes";

	/******* Search Theme By Theme Name ********************/
	String SEARCH_THEME_BY_THEMENAME_POSITIVE_SHEET = "SEARCH_BY_THEMENAME_POSITIVE";
	String SEARCH_BY_THEME_NAME = "/catalog-hub/{store_id}/search/theme/theme_name";
	String SEARCH_THEME_BY_THEME_NAME_SCHEMA = "SEARCH_THEME_BY_THEME_NAME_SCHEMA";
	String SEARCH_THEME_BY_THEMENAME_NEGATIVE_SHEET = "SEARCH_BY_THEMENAME_NEGATIVE";

	/********** Search Theme Collection by collection Theme Name ***************/

	String SEARCH_THEME_COLLECTION_BY_COLLECTION_THEME_NAME = "/catalog-hub/{store_id}/search/theme/theme_collection_name";
	String SEARCH_THEME_COLLECTION_BY_THEMENAME_POSITIVE_SHEET = "SEARCH_THEME_COLLECTION_POSITIV";
	String SEARCH_THEME_BY_COLLECECTION_THEMENAME_NEGATIVE_SHEET = "SEARCH_THEME_COLLECTION_NEGATIV";
	String SEARCH_THEME_COLLECTION_BY_THEME_NAME_SCHEMA = "";

	/**** create theme Collection by Theme Name ****/
	String CREATE_THEME_COLLECTION_POSITIVE_SHEET = "CREATE_THEME_COLLECTION_POSITIV";
	String CREATE_THEME_COLLECTION = "/catalog-hub/{store_id}/themeCollections";

	/**** Get Collection Theme By Theme_ID *********/
	String GET_THEME_COLLECTION_POSITIVE_SHEET = "GET_THEME_COLLECTION_POSITIVE";
	String GET_THEME_COLLECTION = "/catalog-hub/{store_id}/themeCollections/{theme_collection_id}";
	String GET_COLLECTION_THEMES_SCHEMA = "GET_COLLECTION_THEMES_SCHEMA";
	String GET_THEME_COLLECTION_NEGATIVE_SHEET = "GET_THEME_COLLECTION_NEGATIVE";

	/************ Delete Theme collection by Theme collection ID **********/
	String DELETE_THEME_COLLECTION_POSITIVE_SHEET = "DELETE_THEME_COLLECTION_POSITIV";
	String DELETE_THEME_COLLECTION = "/catalog-hub/{store_id}/themeCollections/{theme_collection_id}";

	/********* Get all Collections by Theme collection ID ****************/
	String GET_ALL_THEMES_COLLECTION_POSITIVE_SHEET = "GET_ALL_THEMES_COLLECTION_POSIT";
	String GET_ALL_THEMES_COLLECTION = "/catalog-hub/{store_id}/themeCollections";
	String GET_ALL_THEME_COLLECTION_SCHEMA = "GET_ALL_THEME_COLLECTION_SCHEMA";

	/************ Update Theme by collection ************/
	String UPDATE_THEME_COLLECTION_POSITIVE_SHEET = "UPDATE_THEME_COLLECTION_POSITIV";
	String UPDATE_THEME_COLLECTION = "/catalog-hub/{store_id}/themeCollections";

	/*********** Upload theme Image ******************/
	String UPLOAD_THEME_IMAGE_POSTIVE_SHEET = "UPLOAD_THEME_IMAGE_POSITIVE";
	String UPLOAD_THEME_IMAGE = "/catalog-hub/image/{store_id}/upload/theme/{theme_id}";
	String THEME_IMAGE = "THEME_IMAGE";
	String FORM_DATA_KEY = "image_file";

	/**********************************************************************************/

	/******* Create Chart ***************/
	String CREATE_CHART_POSITIVE_SHEET = "CREATE_CHAT_POSITIVE_SHEET";
	String CREATE_CHART_NEGATIVE_SHEET = "CREATE_CHAT_NEGATIVE_SHEET";
	String CREATE_CHART_BY_CHART_NAME = "/catalog-hub/{store_id}/charts";

	/***********
	 * delete CHART BY ID
	 ******************************************************/
	String DELETE_CHART_BY_ID_POSITIVE_SHEET = "DELETE_CHART_BY_ID_POSITIVE_SHE";
	String DELETE_CHART_BY_ID = "/catalog-hub/{store_id}/charts/{chart_id}";

	/******** Get All Charts ************************/

	String GET_ALL_CHART_POSITIVE_SHEET = "GET_ALL_CHART_POSITIVE_SHEET";
	String GET_ALL_CHART_NEGATIVE_SHEET = "GET_ALL_CHART_NEGATIVE_SHEET";
	String GET_ALL_CHART = "/catalog-hub/{store_id}/charts";
	String GET_ALL_CHART_JSON_SCHEMA = "GET_ALL_CHART_JSON_SCHEMA";

	/************** Get Chart By ID ***********************/
	String GET_CHART_BY_ID_POSITIVE_SHEET = "GET_CHART_BY_ID_POSITIVE_SHEET";
	String GET_CHART_BY_ID_NEGATIVE_SHEET = "GET_CHART_BY_ID_NEGATIVE_SHEET";
	String GET_CHART_BY_ID = "/catalog-hub/{store_id}/chart/{chart_id}";
	String GET_CHART_BY_ID_JSON_SCHEMA = "GET_CHART_BY_ID_JSON_SCHEMA";

	/************ SEARCH CHART BY CHART NAME ******************************/
	String SEARCH_CHART_BY_CHART_NAME_POSITIVE_SHEET = "SEARCH_CHART_NAME_POSITIVE_SHEE";
	String SEARCH_CHART_BY_CHARTNAME_NEGATIVE_SHEET = "SEARCH_CHART_NAME_NEGATIVE_SHEE";
	String SEARCH_CHART_BY_CHARTNAME = "/catalog-hub/{store_id}/search/chart/chart_name";

	/***************************************************************************************************************/

	/******************** Create Banner *************************/

	String CREATE_BANNER_POSTIVE_SHEET = "CREATE_BANNER_POSITIVE_SHEET";
	String CREATE_BANNER_NEGATIVE_SHEET = "CREATE_BANNER_POSITIVE_SHEET";
	String CREATE_BANNER = "/catalog-hub/{store_id}/banners";

	/********************* Get All Banner ********************************/

	String GET_BANNER_POSTIVE_SHEET = "GET_ALL_BANNER_POSITIVE_SHEET";
	String GET_BANNER_NEGATIVE_SHEET = "GET_ALL_BANNER_NEGATIVE_SHEET";
	String GET_ALL_BANNER = "/catalog-hub/{store_id}/banners";

	/********************* Get Banner By ID ********************************/

	String GET_BANNER_BY_ID_NEGATIVE_SHEET = "GET_BANNER_BY_ID_POSITIVE_SHEETT";
	String GET_BANNER_BY_ID_POSITIVE_SHEET = "GET_BANNER_BY_ID_NEGATIVE_SHEET";
	String GET_BANNER_BY_ID = "/catalog-hub/{store_id}/banner/{banner_id}";

	/**************** Delete Banner ID ****************************************/

	String DELETE_BANNER_ID_POSITIVE_SHEET = "DELETE_BANNER_ID_POSITIVE_SHEET";
	String DELETE_BANNER_ID_NEGATIVE_SHEET = "DELETE_BANNER_ID_NEGATIVE_SHEET";
	String DELETE_BANNER_BY_ID = "/catalog-hub/{store_id}/banners/{banner_id}";

	/********************* Update Banner ***********************************/

	String UPDATE_BANNER_POSITIVE_SHEET = "UPDATE_BANNER_POSITIVE_SHEET";
	String UPDATE_BANNER_BY_ID = "catalog-hub/{store_id}/banners";

	/********************************************************************************************************/

	/*********************** Create chart collection *********************/

	String CREATE_CHART_COLLECTION_POSITIVE_SHEET = "CREATE_CHART_COLLECTION_POSTIVE";

	String CREATE_CHART_COLLECTION_NEGATIVE_SHEET = "CREATE_CHART_COLLECTION_NEGATIV";

	String CREATE_CHART_COLLECTION = "/catalog-hub/{store_id}/chartGroups";

	/******************* Delete chart collection *******************/

	String DELETE_CHART_COLLECTION_POSITIVE_SHEET = "DELETE_CHART_COLLECTION_POSTIVE";
	String DELETE_CHART_COLLECTION = "/catalog-hub/{store_id}/chartGroups/{chart_collection_id}";

	/********************** Get Chart collection By ID **********************/

	String GET_CHART_COLLECTION_BY_ID_POSITIVE_SHEET = "GET_CHART_COLLECTION_BY_ID_POSI";
	String GET_CHART_COLLECTION_BY_ID_NEGATIVE_SHEET = "GET_CHART_COLLECTION_BY_ID_NEGA";
	String GET_CHART_COLLECTION_BY_ID = "/catalog-hub/{store_id}/chartGroups/{chart_collection_id}";

	/***************** Get All collection Chart ********************************/

	String GET__ALL_CHART_COLLECTION__POSITIVE_SHEET = "GET_ALL_COLLECTION_CHART_POSITI";
	String GET__ALL_CHART_COLLECTION__NEGATIVE_SHEET = "GET_ALL_COLLECTION_CHART_NEGATI";
	String GET_ALL_CHART_COLLECTION = "/catalog-hub/{store_id}/chartGroups";

	/***************** SEARCH BY CHART *******************/
	String SEARCH_BY_CHART_COLLECTION_NAME = "/catalog-hub/{store_id}/search/chart/chart_collection_name";
	String SEARCH_BY_CHART_COLLECTION_NAME_POSTIVE_SHEET = "SEARCH_CHART_COLLECTION_NAME_PO";
	String SEARCH_BY_CHART_COLLECTION_NAME_NEGATIVE_SHEET = "SEARCH_CHART_COLLECTION_NAME_NE";

	/******************************************************************************************************************************/

	/********************** CREATE FAQ *********************************/

	String CREATE_FAQ_POSITIVE_SHEET = "CREATE_FAQ_POSITIVE_SHEET";
	String CREATE_FAQ = "/catalog-hub/{store_id}/faq";

	/********************** Get FAQ *********************************************/

	String GET_FAQ_POSITIVE_SHEET = "GET_FAQ_POSITIVE_SHEET";
	String GET_FAQ_NEGATIVE_SHEET = "GET_FAQ_NEGATIVE_SHEET";
	String GET_FAQ = "/catalog-hub/{store_id}/faq";

	/*********************************************************************************************************************************/

	/********************* Create Banner Collection **************/
	String CREATE_BANNER_POSITIVE_SHEET = "CREATE_BANNER_COLLECTION_POSITI";
	String CREATE_BANNER_COLLECTION = "/catalog-hub/{store_id}/bannerGroups";

	/*********************** DELETE Banner collection ***************/

	String DELETE_BANNER_COLLECTION = "/catalog-hub/{store_id}/bannerGroups/{banner_collection_id}";
	String DELETE_BANNER_COLLECTION_POSITIVE_SHEET = "DELETE_BANNER_COLLECTION_POSITI";

	/************ Get Collection BY Collection ID ************************/

	String GET_BANNER_COLLECTION_BY_ID_POSITIVE_SHEET = "GET_BANNER_COLLECTION_BY_ID_POS";
	String GET_BANNER_COLLECTION_BY_ID_NEGATIVE_SHEET = "GET_BANNER_COLLECTION_BY_ID_NEG";
	String GET_COLLECTION_BY_ID = "/catalog-hub/{store_id}/bannerGroups/{banner_collection_id}";

}
