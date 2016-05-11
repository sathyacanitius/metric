package io.egen.test;

import groovyx.net.http.HTTPBuilder;

import java.util.HashMap;
import java.util.Map;

public class TestWebservice {

	public static void main(String args[]) {
		TestWebservice testClient = new TestWebservice();
		try {
			testClient.post("http://localhost:8080/metric/createMetric/", 190);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void post(String url, int value) throws Exception {
		HTTPBuilder http = new HTTPBuilder(url);

		Map<String, Object> map = new HashMap<String, Object>();
		String json = "{\"timeStamp\": \""
				+ String.valueOf(System.currentTimeMillis())
				+ "\", \"value\": \"" + value + "\"}";
		map.put("body", json);
		System.out.println("Posting data " + json + " to api at " + url);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", "application/json");
		map.put("headers", headers);

		try {
			http.post(map);
		} catch (Exception e) {
			System.out.println("API [" + url + "] not reachable. Error - "
					+ e.getMessage());
		}
	}
	
}
