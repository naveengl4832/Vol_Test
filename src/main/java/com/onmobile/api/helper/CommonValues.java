package com.onmobile.api.helper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CommonValues {
	public static String theme_Id;
	public static String theme_Name;
	public static String image_url;
	public static String theme_Collection_Id;
	public static String theme_Collection_Name;

	public static Map<String, String> chartValues = new HashMap<String, String>();
	public static Map<String, String> bannerValues = new HashMap<String, String>();
	public static Map<String, String> chartCollectionValues = new HashMap<String, String>();
	public static Map<String, String> faqValues = new HashMap<String, String>();

	public static Map<String, String> bannerCollectionValues = new HashMap<String, String>();

	public static String getDateAndTime() {

		return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now());

	}

}
