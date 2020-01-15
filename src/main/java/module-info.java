module com.nick.wood.a_star_alg.visual_test {

	exports com.nick.wood.a_star_alg.visual_test.model;

	exports com.nick.wood.a_star_alg.visual_test to javafx.graphics;
	exports com.nick.wood.a_star_alg.visual_test.controller to javafx.fxml;

	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;

	requires com.nick.wood.a_star_alg.AStarAlg;
}