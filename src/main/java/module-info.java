module com.nick.wood.a_star_alg.visual_test {

	exports com.nick.wood.a_star_alg.visual_test.model;
	exports com.nick.wood.a_star_alg.visual_test.model.CoR;
	exports com.nick.wood.a_star_alg.visual_test.model.sprite;
	exports com.nick.wood.a_star_alg.visual_test.utils;
	exports com.nick.wood.a_star_alg.visual_test;
	exports com.nick.wood.a_star_alg.visual_test.controller;

	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;

	requires com.nick.wood.a_star_alg.AStarAlg;
}