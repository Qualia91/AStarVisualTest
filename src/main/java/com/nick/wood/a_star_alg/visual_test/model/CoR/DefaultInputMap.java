package com.nick.wood.a_star_alg.visual_test.model.CoR;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class DefaultInputMap extends Handler {
	@Override
	public URI handleRequest(Request request) throws URISyntaxException {

		// default ot tesMap.bmp which is in resources folder
		URL url = getClass().getClassLoader().getResource(request.getDefaultFileString());
		return Objects.requireNonNull(url).toURI();

	}
}
