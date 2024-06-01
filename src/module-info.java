module Zatre {
	exports persistentie;
	exports ui;
	exports gui;
	exports main;
	exports domein;
	exports testen;

	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;
	requires javafx.fxml;
	requires java.desktop;

	opens gui to javafx.graphics, javafx.fxml;

}