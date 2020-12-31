package com.boc_dev.a_star_alg.visual_test.model.CoR;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class Handler {

	protected Handler successor;

	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}

	public abstract URI handleRequest(Request request) throws URISyntaxException;
}
