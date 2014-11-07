package ar.edu.itba.poo.bytemii.dialogs;/*
** Created by jpmoreno on 10/23/14.
*/

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlsDialog extends Stage {
	private static final int WIDTH = 200;
	private static final int HEIGHT = 200;

	private static final int LISTENER_WIDTH = 150;
	private static final int LISTENER_HEIGHT = 60;

	private final GridPane pane;
	private final Stage keyListener;

	public ControlsDialog(Stage mainStage) {
		super();

		pane = new GridPane();
		keyListener = new Stage();

		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++) {
				Button button = new Button();
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						keyListener.show();
						keyListener.requestFocus();
					}
				});
				pane.add(button, i, j);
			}

		configPane();
		configKeyListener();
		configStage(mainStage);
	}

	private void configStage(Stage mainStage) {
		this.initOwner(mainStage);
		this.setTitle("Controls...");
		this.setResizable(false);
		this.centerOnScreen();
		this.setScene(new Scene(pane, WIDTH, HEIGHT));
	}

	private void configPane() {
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(10);
		pane.setHgap(10);
	}

	private void configKeyListener() {
		keyListener.initOwner(this);
		keyListener.centerOnScreen();
		keyListener.setAlwaysOnTop(true);
		keyListener.setResizable(false);

		VBox pane = new VBox(new Label("Press a key..."));
		pane.setAlignment(Pos.CENTER);

		Scene scene = new Scene( pane, LISTENER_WIDTH, LISTENER_HEIGHT );
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().isDigitKey())
					System.out.println("Digit");
				if(event.getCode().isArrowKey())
					System.out.println("Arrow");
				if(event.getCode().isKeypadKey())
					System.out.println("Keypad");
				if(event.getCode().isLetterKey())
					System.out.println("Letter");
				if(event.getCode().isModifierKey())
					System.out.println("Modifier");
				if(event.getCode().isNavigationKey())
					System.out.println("Navigation");
				if(event.getCode().isWhitespaceKey())
					System.out.println("Whitespace");
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				//keyListener.hide();
			}
		});

		keyListener.setScene(scene);
	}
}
