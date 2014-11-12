package ar.edu.itba.poo.bytemii.dialogs;

import ar.edu.itba.poo.bytemii.emulator.Resources;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public final class ControlsDialog extends Stage {
	private static final int WIDTH = 300;
	private static final int HEIGHT = 200;

	private final KeyCode[] keyboard = {
			KeyCode.X, KeyCode.DIGIT1, KeyCode.DIGIT2, KeyCode.DIGIT3,
			KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.A,
			KeyCode.S, KeyCode.D, KeyCode.Z, KeyCode.C,
			KeyCode.DIGIT4, KeyCode.R, KeyCode.F, KeyCode.V
	};

	private final GridPane pane;

	public ControlsDialog(Stage mainStage) {
		super();

		pane = new GridPane();

		Label emulatorsLabel = new Label("This Emulator's");
		Label originalLabel = new Label("Original's");
		GridPane emulatorsPane = new GridPane();
		emulatorsPane.setVgap(20);
		emulatorsPane.setHgap(20);
		GridPane originalPane = new GridPane();
		originalPane.setVgap(20);
		originalPane.setHgap(20);

		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++) {
				Label label = new Label(keyboard[Resources.getKeyboardMap()[i][j]].getName());
				emulatorsPane.add(label, j, i);
				Label label1 = new Label(Integer.toHexString(Resources.getKeyboardMap()[i][j]).toUpperCase());
				originalPane.add(label1, j, i);
			}

		pane.add(emulatorsLabel, 0, 0);
		pane.add(emulatorsPane, 0, 1);
		pane.add(originalLabel, 1,0);
		pane.add(originalPane, 1, 1);

		configPane();
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
		pane.setHgap(60);
	}
}
