package ar.edu.itba.poo.bytemii.uielements;

import ar.edu.itba.poo.bytemii.emulator.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;

public class MainMenuBar extends MenuBar {
	private final Stage prefs;
	private final Stage about;
	private final Stage controls;

	private final Menu menuFile;
	private final MenuItem itemOpenROM;
	private final Menu itemMenuOpenRecent;
	private final MenuItem itemStop;
	private final MenuItem itemRestart;
	private final MenuItem itemPause;
	private final MenuItem itemResume;
	private final MenuItem itemPrefs;
	private final MenuItem itemClose;

	private final Menu menuHelp;
	private final MenuItem itemControls;
	private final MenuItem itemAbout;

	public MainMenuBar(  ) {
		this(null, null, null); // Permite que los items Preferences, About y Controls no tengan accion por defecto
	}

	public MainMenuBar(Stage prefs, Stage about, Stage controls) {
		super();

		this.controls = controls;
		this.about = about;
		this.prefs = prefs;

		menuFile = new Menu("File");
		itemOpenROM = new MenuItem("Open ROM...");
		itemMenuOpenRecent = new Menu("Open Recent");
		itemStop = new MenuItem("Stop");
		itemRestart = new MenuItem("Restart");
		itemPause = new MenuItem("Pause");
		itemResume = new MenuItem("Resume");
		itemPrefs = new MenuItem("Preferences...");
		itemClose = new MenuItem("Quit");

		menuHelp = new Menu("Help");
		itemControls = new MenuItem("Controls...");
		itemAbout = new MenuItem("About");

		configMenuBar();
		setState(GameState.NO_GAME);
		configFile();
		this.getMenus().add(menuFile);
		setHelp();
		this.getMenus().add(menuHelp);

		setDefaultBehavior();
	}

	private void setDefaultBehavior() {
		itemClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				System.exit(0);
			}
		});

		if(prefs != null) {
			itemPrefs.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle( ActionEvent event ) {
					prefs.show();
				}
			});
		}

		if(about != null) {
			itemAbout.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle( ActionEvent event ) {
					about.show();
				}
			});
		}

		if(controls != null) {
			itemControls.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle( ActionEvent event ) {
					controls.show();
				}
			});
		}
	}

	private void configMenuBar() {
		this.setUseSystemMenuBar(true);
	}

	private void configFile() {
		itemMenuOpenRecent.setDisable(true);

		menuFile.getItems().add(itemOpenROM);
		menuFile.getItems().add(itemMenuOpenRecent);
		menuFile.getItems().add(new SeparatorMenuItem());
		menuFile.getItems().add(itemStop);
		menuFile.getItems().add(itemRestart);
		menuFile.getItems().add(new SeparatorMenuItem());
		menuFile.getItems().add(itemResume);
		menuFile.getItems().add(itemPause);
		menuFile.getItems().add(new SeparatorMenuItem());
		menuFile.getItems().add(itemPrefs);
		menuFile.getItems().add(new SeparatorMenuItem());
		menuFile.getItems().add(itemClose);

		itemPrefs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle( ActionEvent event ) {
				System.out.println("ASD");
			}
		});
	}

	private void setHelp() {
		menuHelp.getItems().add(itemControls);
		menuHelp.getItems().add(new SeparatorMenuItem());
		menuHelp.getItems().add(itemAbout);
	}

	public void setState(GameState state) {
		switch( state ) {
			case PLAYING:
				itemOpenROM.setDisable( true );
				itemStop.setDisable( false );
				itemRestart.setDisable( false );
				itemResume.setDisable( true );
				itemPause.setDisable( false );
				break;
			case NO_GAME:
				itemOpenROM.setDisable( false );
				itemStop.setDisable( true );
				itemRestart.setDisable( true );
				itemResume.setDisable( true );
				itemPause.setDisable( true );
				break;
			case PAUSE:
				itemOpenROM.setDisable( true );
				itemStop.setDisable( false );
				itemRestart.setDisable( true );
				itemResume.setDisable( false );
				itemPause.setDisable( true );
				break;
		}
	}

	public Menu getMenuFile() {
		return menuFile;
	}

	public MenuItem getItemOpenROM() {
		return itemOpenROM;
	}

	public Menu getItemMenuOpenRecent() {
		return itemMenuOpenRecent;
	}

	public MenuItem getItemStop() {
		return itemStop;
	}

	public MenuItem getItemRestart() {
		return itemRestart;
	}

	public MenuItem getItemPause() {
		return itemPause;
	}

	public MenuItem getItemResume() {
		return itemResume;
	}

	public MenuItem getItemPrefs() {
		return itemPrefs;
	}

	public MenuItem getItemClose() {
		return itemClose;
	}

	public Menu getMenuHelp() {
		return menuHelp;
	}

	public MenuItem getItemControls() {
		return itemControls;
	}

	public MenuItem getItemAbout() {
		return itemAbout;
	}
}
