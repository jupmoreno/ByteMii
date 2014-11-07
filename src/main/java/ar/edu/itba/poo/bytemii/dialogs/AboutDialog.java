package ar.edu.itba.poo.bytemii.dialogs;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Random;

public class AboutDialog extends Stage {
	private static final String DEFAULT_TITLE = "ByteMii";
	private static final String DEFAULT_SUBTITLE = "Copyright \u00A9 2014 - " + Calendar.getInstance().get(Calendar.YEAR);
	private static final String DEFAULT_BODY = "Gonzalo Ibars & Juan Moreno";

	private static final String TITLE_FONT_URL = "/fonts/GameOver.ttf";

	private static final int WIDTH = 270;
	private static final int HEIGHT = 130;
	private static final int TITLE_SIZE = 100;

	private VBox pane;
	private final Label title;
	private final Label subtitle;
	private final Label body;

	private Random rand;

	public AboutDialog(Stage mainStage) {
		super();

		rand = new Random();

		pane = new VBox();
		title = new Label(DEFAULT_TITLE);
		subtitle = new Label(DEFAULT_SUBTITLE);
		body = new Label(DEFAULT_BODY);

		configTitle();
		configStage(mainStage);
		configPane();
	}

	private void configTitle() {
		title.setFont(Font.loadFont(getClass().getResourceAsStream(TITLE_FONT_URL), TITLE_SIZE));
	}

	private void configSubtitle() {

	}

	private void configBody() {

	}

	private void configStage(Stage mainStage) {
		this.initOwner(mainStage);
		this.setTitle("About");
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.centerOnScreen();

		this.setScene(new Scene(pane, WIDTH, HEIGHT));
	}

	private void configPane() {
		pane.setAlignment(Pos.CENTER);

		pane.getChildren().add(title);
		pane.getChildren().add(subtitle);
		pane.getChildren().add(body);

		pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				float r = rand.nextFloat();
				float g = rand.nextFloat();
				float b = rand.nextFloat();
				title.setTextFill(Color.color(r, g, b));
			}
		});
	}

	public Label getTitleLabel() {
		return title;
	}

	public Label getSubtitleLabel() {
		return subtitle;
	}

	public Label getBodyLabel() {
		return body;
	}
}
