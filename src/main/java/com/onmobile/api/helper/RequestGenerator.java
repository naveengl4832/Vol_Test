package com.onmobile.api.helper;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

import com.onmobile.api.config.Constants;

import io.restassured.specification.RequestSpecification;

/**
 * Request Generator class with all different types of request parameters
 *
 */
public class RequestGenerator extends RestBaseClass implements Constants {

	static String sourceClass = RequestGenerator.class.getName();
	static Logger LOGGER = Logger.getLogger(sourceClass);
	String sourceMethod;

	RequestSpecification requestSpecification;

	/**
	 * @param baseUri
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(String baseUri) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);

		try {
			LOGGER.info("baseUri " + baseUri);
			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;
	}

	/**
	 * @param baseUri
	 * @param request
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(String baseUri, Object request) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {

			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("request " + request.toString());
			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).body(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("requestSpecification : " + requestSpecification);
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

	/**
	 * @param header
	 * @param baseUri
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(Map<String, String> header, String baseUri) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("header " + header);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).headers(header).with();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;
	}

	/**
	 * @param header
	 * @param baseUri
	 * @param request
	 * @return RequestSpecification
	 */

	public RequestSpecification getRequest(Map<String, String> header, String baseUri, Object request) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("request " + request.toString());
			LOGGER.info("header " + header);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).headers(header)
					.body(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;
	}

	/**
	 * @param baseUri
	 * @param queryParam
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(String baseUri, Map<String, String> queryParam) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("queryParam " + queryParam);
			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).queryParams(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;
	}

	/**
	 * @param baseUri
	 * @param request
	 * @param queryParam
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(String baseUri, Object request, Map<String, String> queryParam) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("request " + request.toString());
			LOGGER.info("queryParam " + queryParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).queryParams(queryParam)
					.body(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

	/**
	 * @param baseUri
	 * @param pathParam
	 * @param request
	 * @return
	 */
	public RequestSpecification getRequest(String baseUri, Map<String, String> pathParam, Object request) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("request " + request.toString());
			LOGGER.info("queryParam " + pathParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).pathParams(pathParam)
					.body(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		log.debug(requestSpecification.log().body());
		return requestSpecification;

	}

	/**
	 * @param header
	 * @param baseUri
	 * @param pathParam
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(Map<String, String> header, String baseUri, Map<String, String> pathParam) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("header " + header);
			LOGGER.info("pathParam " + pathParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).headers(header)
					.pathParams(pathParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;
	}

	/**
	 * @param header
	 * @param baseUri
	 * @param queryParam
	 * @param pathParam
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(Map<String, String> header, String baseUri, Map<String, String> queryParam,
			Map<String, String> pathParam) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("header " + header);
			LOGGER.info("queryParam " + queryParam);
			LOGGER.info("pathParam " + pathParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).headers(header)
					.queryParams(queryParam).pathParams(pathParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

	/**
	 * 
	 * @param baseUri
	 * @param queryParam
	 * @param pathParam
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(Map<String, String> queryParam, Map<String, String> pathParam,
			String baseUri) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("queryParam " + queryParam);
			LOGGER.info("pathParam " + pathParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).queryParams(queryParam)
					.pathParams(pathParam);

			LOGGER.info(requestSpecification.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;
	}

	/**
	 * @param header
	 * @param baseUri
	 * @param queryParam
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(String baseUri, Map<String, String> header, Map<String, String> queryParam) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("header " + header);
			LOGGER.info("queryParam " + queryParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).headers(header)
					.queryParams(queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

	/**
	 * 
	 * @param header
	 * @param baseUri
	 * @param queryParam
	 * @param request
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(String baseUri, Map<String, String> header, Map<String, String> queryParam,
			Object request) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("header " + header);
			LOGGER.info("queryParam " + queryParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).headers(header)
					.queryParams(queryParam).body(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

	/**
	 * @param header
	 * @param baseUri
	 * @param pathParam
	 * @param request
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequest(Map<String, String> header, String baseUri, Map<String, String> pathParam,
			Object request) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("header " + header);
			LOGGER.info("pathParam " + pathParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).headers(header)
					.pathParams(pathParam).body(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

	/*
	 * @param BaseURI
	 * 
	 * @param PathParam
	 * 
	 * 
	 */

	public RequestSpecification getRequest_PathParam_URL(String baseUri, Map<String, String> pathParam) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("queryParam " + pathParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).pathParams(pathParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

	/**
	 * @param queryParam
	 * @param baseUri
	 * @param pathParam
	 * @param request
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequestWithPathQueryReqobject(Map<String, String> queryParam, String baseUri,
			Map<String, String> pathParam, Object request) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("header " + queryParam);
			LOGGER.info("pathParam " + pathParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).queryParams(queryParam)
					.pathParams(pathParam).body(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

	/**
	 *
	 * @param baseUri
	 * @param pathParam
	 * @param request
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequestWithPathReqobject(String baseUri, Map<String, String> pathParam,
			Map<String, String> request) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("pathParam " + pathParam);

			requestSpecification = given().contentType(APPLICATION_JSON)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).pathParams(pathParam)
					.body(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

	/**
	 *
	 * @param baseUri
	 * @param pathParam
	 * @param Multipartt
	 * @return RequestSpecification
	 */
	public RequestSpecification getRequestWithMultiPart(String baseUri, Map<String, String> pathParam,
			String formDataKey, String imagePath, String imageType) {
		sourceMethod = "getRequest";
		LOGGER.entering(sourceClass, sourceMethod);
		try {
			LOGGER.info("baseUri " + baseUri);
			LOGGER.info("pathParam " + pathParam);

			requestSpecification = given().contentType(MULTIPART_FORM_DATA)
					.baseUri(PropertyReader.getPropertyValue(URL_PROPERTIES_PATH, baseUri)).pathParams(pathParam)
					.multiPart(formDataKey, new File(PropertyReader.getProperty(IMAGE_PATH, imagePath)), imageType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return requestSpecification;

	}

}
