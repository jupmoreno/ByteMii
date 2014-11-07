package ar.edu.itba.poo.bytemii.dialogs;

import ar.edu.itba.poo.bytemii.display.BasicGameDisplay;
import ar.edu.itba.poo.bytemii.display.Resolution;
import ar.edu.itba.poo.bytemii.sound.MultiFilesPlayer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.prefs.Preferences;

public final class PreferencesDialog extends Stage {
	private static final int WIDTH = 300;
	private static final int HEIGHT = 150;

	private static final String DISPLAY_RES = "display_resolution";
	private static final String FORE_COLOR = "display_fore_color";
	private static final String BACK_COLOR = "display_back_color";

	private Preferences prefs;

	Stage mainStage;
	BasicGameDisplay display;
	MultiFilesPlayer forePlayer;
	MultiFilesPlayer backPlayer;

	private final TabPane pane;
	private final Tab displayTab;
	private final Tab soundTab;
	private final Tab gameTab;

	private final GridPane displayPane;
	private final GridPane soundPane;
	private final GridPane gamePane;

	private ChoiceBox<Resolution> sizeChoice;
	private ColorPicker foreColorPicker;
	private ColorPicker backColorPicker;

	private CheckBox foreSoundCheck;
	private ChoiceBox<String> backSoundChoice;
	private CheckBox backSoundCheck;
	private ChoiceBox<String> foreSoundChoice;

	private ChoiceBox<String> speedChoice;

	public PreferencesDialog( Stage mainStage, BasicGameDisplay display, MultiFilesPlayer forePlayer, MultiFilesPlayer backPlayer ) {
		super();
		prefs = Preferences.userRoot().node(getClass().toString());
		this.mainStage = mainStage;
		this.display = display;
		this.forePlayer = forePlayer;
		this.backPlayer = backPlayer;

		pane = new TabPane();
		displayTab = new Tab("Display");
		soundTab = new Tab("Sound");
		gameTab = new Tab("Game");

		displayPane = new GridPane();
		soundPane = new GridPane();
		gamePane = new GridPane();

		configPane();
		configDisplayTab();
		configSoundTab();
		configGameTab();
		configStage();
	}
	
	private void configStage() {
		this.initOwner( mainStage );
		this.setTitle("Preferences");
		this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.centerOnScreen();

		this.setScene(new Scene( pane ));
	}
	
	private void configPane() {
		pane.getTabs().add( displayTab );
		pane.getTabs().add( soundTab );
		pane.getTabs().add( gameTab );
	}
	
	private void configDisplayTab() {
		displayTab.setClosable(false);
		displayTab.setContent(displayPane);

		displayPane.setAlignment(Pos.CENTER);
		displayPane.setPrefSize(WIDTH, HEIGHT);
		displayPane.setHgap(10);
		displayPane.setVgap(10);

		final Label sizeLabel = new Label("Resolution: ");
		sizeChoice = new ChoiceBox<>();

		final Label foregroundLabel = new Label("Foreground color: ");
		foreColorPicker = new ColorPicker();
		final Label backgroundLabel = new Label("Background color: ");
		backColorPicker = new ColorPicker();

		displayPane.add(sizeLabel, 0, 0);
		displayPane.add(sizeChoice, 1, 0);
		displayPane.add(foregroundLabel, 0, 1);
		displayPane.add(foreColorPicker, 1, 1);
		displayPane.add(backgroundLabel, 0, 2);
		displayPane.add(backColorPicker, 1, 2);

		setDisplayDefaults();
	}

	private void setDisplayDefaults() {
		for(Resolution res : display.getValidResolutions())
			sizeChoice.getItems().add(res);
		Resolution defRes = new Resolution(prefs.get(DISPLAY_RES, display.getResolution().toString()));
		if(sizeChoice.getItems().contains(defRes)) {
			display.setResolution(defRes);
			prefs.put(DISPLAY_RES, defRes.toString());
			mainStage.sizeToScene();
			mainStage.centerOnScreen();
		} else {
			defRes = display.getResolution();
			if(!sizeChoice.getItems().contains(defRes)) {
				display.getValidResolutions().add(defRes);
				sizeChoice.getItems().add(defRes);
			}
		}
		sizeChoice.setValue(defRes);
		sizeChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed( ObservableValue<? extends Number> observable, Number oldValue, Number newValue ) {
				display.setResolution(sizeChoice.getItems().get(newValue.intValue()));
				prefs.put(DISPLAY_RES, display.getResolution().toString());
				mainStage.sizeToScene();
				mainStage.centerOnScreen();
			}
		});

		foreColorPicker.setValue(Color.valueOf(prefs.get(FORE_COLOR, display.getForeColor().toString())));
		display.setForeColor(foreColorPicker.getValue());
		foreColorPicker.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				display.setForeColor(foreColorPicker.getValue());
				prefs.put(FORE_COLOR, display.getForeColor().toString());
			}
		});

		backColorPicker.setValue(Color.valueOf(prefs.get(BACK_COLOR, display.getBackColor().toString())));
		display.setBackColor(backColorPicker.getValue());
		backColorPicker.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				display.setBackColor(backColorPicker.getValue());
				prefs.put(BACK_COLOR, display.getBackColor().toString());

			}
		});
	}

	private void configSoundTab() {
		soundTab.setClosable(false);
		soundTab.setContent(soundPane);

		soundPane.setAlignment(Pos.CENTER);
		soundPane.setPrefSize(WIDTH, HEIGHT);
		soundPane.setHgap(10);
		soundPane.setVgap(5);

		backSoundCheck = new CheckBox("Background's");
		backSoundChoice = new ChoiceBox<>();
		foreSoundCheck = new CheckBox("Game's");
		foreSoundChoice = new ChoiceBox<>();

		soundPane.add(backSoundCheck, 0, 0);
		soundPane.add(backSoundChoice, 0, 1);
		soundPane.add(foreSoundCheck, 0, 2);
		soundPane.add(foreSoundChoice, 0, 3);

		setSoundDefaults();
	}

	private void setSoundDefaults() {
		setSoundState(forePlayer.isEnabled(), foreSoundCheck, foreSoundChoice);
		foreSoundCheck.setSelected(forePlayer.isEnabled());
		foreSoundChoice.setDisable(!forePlayer.isEnabled());
		foreSoundChoice.getItems().addAll(forePlayer.getSounds());
		foreSoundChoice.setValue(forePlayer.getActiveSound());

		foreSoundCheck.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				forePlayer.setEnabled(foreSoundCheck.isSelected());
				setSoundState(forePlayer.isEnabled(), foreSoundCheck, foreSoundChoice);
				foreSoundChoice.setDisable(!forePlayer.isEnabled());
			}
		});
		foreSoundChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				forePlayer.stop();
				if(!forePlayer.setFile(foreSoundChoice.getItems().get(newValue.intValue())))
					foreSoundChoice.setValue(forePlayer.getActiveSound());
			}
		});

		setSoundState(backPlayer.isEnabled(), backSoundCheck, backSoundChoice);
		backSoundCheck.setSelected(backPlayer.isEnabled());
		backSoundChoice.setDisable(!backPlayer.isEnabled());
		backSoundChoice.getItems().addAll(backPlayer.getSounds());
		backSoundChoice.setValue(backPlayer.getActiveSound());
		if(backPlayer.isEnabled())
			backPlayer.play();

		backSoundCheck.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				backPlayer.setEnabled(backSoundCheck.isSelected());
				setSoundState(backPlayer.isEnabled(), backSoundCheck, backSoundChoice);
				backSoundChoice.setDisable(!backPlayer.isEnabled());
				if(backPlayer.isEnabled())
					backPlayer.play();
			}
		});
		backSoundChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				backPlayer.stop();
				if(!backPlayer.setFile(backSoundChoice.getItems().get(newValue.intValue())))
					backSoundChoice.setValue(backPlayer.getActiveSound());
				if(backPlayer.isEnabled())
					backPlayer.play();
			}
		});
	}

	private void configGameTab() {
		gameTab.setClosable(false);
		gameTab.setContent(gamePane);

		gamePane.setPrefSize(WIDTH, HEIGHT);
		gamePane.setAlignment(Pos.CENTER);

		final Label speedLabel = new Label("Speed: ");
		speedChoice = new ChoiceBox<>();

		gamePane.add(speedLabel, 0, 0);
		gamePane.add(speedChoice, 1, 0);
	}
	
	public ChoiceBox<Resolution> getSizeChoice() {
		return sizeChoice;
	}

	public ColorPicker getForeColorPicker() {
		return foreColorPicker;
	}

	public ColorPicker getBackColorPicker() {
		return backColorPicker;
	}

	public ChoiceBox<String> getBackSoundChoice() {
		return backSoundChoice;
	}

	public ChoiceBox<String> getForeSoundChoice() {
		return foreSoundChoice;
	}

	public CheckBox getBackSoundCheck() {
		return backSoundCheck;
	}

	public CheckBox getForeSoundCheck() {
		return foreSoundCheck;
	}

	public ChoiceBox<String> getSpeedChoice() {
		return speedChoice;
	}

	private void setSoundState(boolean state, CheckBox check, ChoiceBox choice) {
		if(state) {
			check.setSelected( true );
			check.setDisable( false );
			choice.setDisable( false );
		} else {
			check.setSelected( false );
			check.setDisable( false );
			choice.setDisable( true );
		}
	}
}
