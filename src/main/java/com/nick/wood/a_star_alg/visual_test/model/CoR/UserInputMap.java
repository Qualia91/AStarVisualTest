package com.nick.wood.a_star_alg.visual_test.model.CoR;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class UserInputMap extends Handler {
	@Override
	public URI handleRequest(Request request) throws URISyntaxException {

		if (request.getUserFileString() != null) {
			File file = new File(request.getUserFileString());

			if (file.exists()) {
				return new File(request.getUserFileString()).toURI();
			}
		}

		// if user string is null let successor do it
		return successor.handleRequest(request);

	}
}
