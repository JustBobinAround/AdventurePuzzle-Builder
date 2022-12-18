module justbobinaround.adventure_builder {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.google.gson;
	
	
	opens justbobinaround.adventure_builder to javafx.fxml;
	exports justbobinaround.adventure_builder;
}