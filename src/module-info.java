module com.nick.wood.RTS {

	exports com.nick.wood.rts to javafx.graphics;
	exports com.nick.wood.rts.controller to javafx.fxml;

	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
}