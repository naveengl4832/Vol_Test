package com.onmobile.api.helper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CommonValues {

	public static Map<String, String> chartValues = new HashMap<String, String>();
	public static Map<String, String> bannerValues = new HashMap<String, String>();
	public static Map<String, String> chartCollectionValues = new HashMap<String, String>();
	public static Map<String, String> faqValues = new HashMap<String, String>();
	public static Map<String, String> themeValues = new HashMap<String, String>();
	public static Map<String, String> bannerCollectionValues = new HashMap<String, String>();
	public static Map<String, String> themeCollectionValues = new HashMap<String, String>();

	public static String getDateAndTime() {

		return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now());

	}

}
