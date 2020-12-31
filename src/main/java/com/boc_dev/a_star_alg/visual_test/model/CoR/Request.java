package com.boc_dev.a_star_alg.visual_test.model.CoR;

public class Request {

	private final String userFileString;
	private final String defaultFileString;

	public Request(String userFileString, String defaultFileString) {
		this.userFileString = userFileString;
		this.defaultFileString = defaultFileString;
	}

	public String getUserFileString() {
		return userFileString;
	}

	public String getDefaultFileString() {
		return defaultFileString;
	}
}
