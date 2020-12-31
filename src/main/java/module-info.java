module com.boc_dev.a_star_alg.visual_test {

	exports com.boc_dev.a_star_alg.visual_test.model;
	exports com.boc_dev.a_star_alg.visual_test.model.CoR;
	exports com.boc_dev.a_star_alg.visual_test.model.sprite;
	exports com.boc_dev.a_star_alg.visual_test.utils;
	exports com.boc_dev.a_star_alg.visual_test;
	exports com.boc_dev.a_star_alg.visual_test.controller;

	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;

	requires com.boc_dev.a_star_alg.AStarAlg;
}