package ar.edu.itba.poo.bytemii;

import ar.edu.itba.poo.bytemii.dialogs.AboutDialog;
import ar.edu.itba.poo.bytemii.dialogs.ControlsDialog;
import ar.edu.itba.poo.bytemii.dialogs.PreferencesDialog;
import ar.edu.itba.poo.bytemii.display.CanvasDisplay;
import ar.edu.itba.poo.bytemii.emulator.Emulator;
import ar.edu.itba.poo.bytemii.sound.BackSoundPlayer;
import ar.edu.itba.poo.bytemii.sound.ForeSoundPlayer;
import ar.edu.itba.poo.bytemii.uielements.MainMenuBar;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
	private KeyCode[] keyboard = {KeyCode.X, KeyCode.DIGIT1, KeyCode.DIGIT2, KeyCode.DIGIT3,
								 KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.A,
								 KeyCode.S, KeyCode.D, KeyCode.Z, KeyCode.C,
								 KeyCode.DIGIT4, KeyCode.R, KeyCode.F, KeyCode.V};

	private Stage mainStage;
	private VBox mainPane;
	private MainMenuBar mainMenuBar;
	private CanvasDisplay display;

	private ForeSoundPlayer forePlayer;
	private BackSoundPlayer backPlayer;

	private PreferencesDialog prefsDialog;
	private AboutDialog aboutDialog;
	private ControlsDialog controlsDialog;

	private FileChooser fileChooser;
	private File file;

	private Emulator emulator;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainStage) {
		this.mainStage = mainStage;
		setUserAgentStylesheet(STYLESHEET_MODENA);
		mainStage.setTitle("ByteMii");
		mainStage.setResizable(false);
		mainStage.centerOnScreen();
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});

		forePlayer = new ForeSoundPlayer();
		backPlayer = new BackSoundPlayer();

		mainPane = new VBox();

		display = new CanvasDisplay();

		prefsDialog = new PreferencesDialog(mainStage, display, forePlayer, backPlayer);
		aboutDialog = new AboutDialog(mainStage);
		controlsDialog = new ControlsDialog(mainStage);

		mainMenuBar = new MainMenuBar(prefsDialog, aboutDialog, controlsDialog);
		buildFileChooser();

		emulator = new Emulator(display, forePlayer);

		configMenuBar();
		configPrefs();

		mainPane.getChildren().add(mainMenuBar);
		mainPane.getChildren().add(display);
		mainStage.setScene(new Scene(mainPane));
		setKeyboardListener();

		mainStage.show();
	}

	private void buildFileChooser() {
		fileChooser = new FileChooser();
		fileChooser.setTitle("Open a ROM File...");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ROM Files", "*.rom"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Chip8 Files", "*.ch8"));
	}

	private void configMenuBar() {
		mainMenuBar.getItemOpenROM().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				file = fileChooser.showOpenDialog(mainStage);
				if(file != null) {
					try {
						emulator.start(file);
						mainMenuBar.setState(emulator.getState());
					} catch(IllegalArgumentException | IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		mainMenuBar.getItemStop().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				emulator.stop();
				mainMenuBar.setState( emulator.getState() );
			}
		});
		mainMenuBar.getItemRestart().setOnAction( new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				emulator.restart();
				mainMenuBar.setState( emulator.getState() );
			}
		} );
		mainMenuBar.getItemPause().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				emulator.pause();
				mainMenuBar.setState( emulator.getState() );
			}
		});
		mainMenuBar.getItemResume().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				emulator.resume();
				mainMenuBar.setState( emulator.getState() );
			}
		});
	}

	private void configPrefs() {
		prefsDialog.getSpeedChoice().getItems().addAll(emulator.getSpeeds());
		prefsDialog.getSpeedChoice().setValue(emulator.getActiveSpeed());
		prefsDialog.getSpeedChoice().getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue ) {
				if(!emulator.setActiveSpeed(prefsDialog.getSpeedChoice().getItems().get(newValue.intValue())))
					prefsDialog.getSpeedChoice().setValue(emulator.getActiveSpeed());
			}
		});
	}

	private void setKeyboardListener() {
		mainStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle( KeyEvent event ) {
				for(int i = 0; i < keyboard.length; i++)
					if(event.getCode() == keyboard[i]) {
						emulator.pressedKey(i);
						return;
					}
			}
		});
		mainStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle( KeyEvent event ) {
				for(int i = 0; i < keyboard.length; i++)
					if(event.getCode() == keyboard[i]) {
						emulator.releasedKey(i);
						return;
					}
			}
		});
	}
}
