package com.nick.wood.a_star_alg.visual_test.model.CoR;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class UserInputMap extends Handler {
	@Override
	public URI handleRequest(Request request) throws URISyntaxException {
		if (request.getUserFileString() == null || getClass().getClassLoader().getResource(request.getUserFileString()) == null) {

			// if user string is null let successor do it
			return successor.handleRequest(request);

		} else {

			URL url = getClass().getClassLoader().getResource(request.getUserFileString());

			return Objects.requireNonNull(url).toURI();

		}

	}
}
