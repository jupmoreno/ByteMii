package ar.edu.itba.poo.bytemii.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackSoundPlayer extends MultiFilesPlayer {
	private static final String SOUND_FOLDER = "/sounds/back/";
	private static final String[] SOUNDS = {"AroundTheWorld.mp3", "GameOfThrones.mp3", "GetLucky.mp3", "GhostBusters.mp3",
											"OldMario.mp3", "SmellsLikeTeenSpirit.mp3", "WhatIsLove.mp3"};

	private MediaPlayer player;

	public BackSoundPlayer() {
		super();
		setDefaultFiles();
		setFile(prefs.get(SOUND_FILE, SOUNDS[0]));
	}

	@Override
	protected void initPlayer() {
		player = new MediaPlayer(new Media(soundFiles.get(activeSound).toExternalForm()));
		player.setOnEndOfMedia(new Runnable() {
			@Override
			public void run() {
				player.stop();
				player.play();
			}
		});
	}

	private void setDefaultFiles() {
		for(String sound : SOUNDS)
			soundFiles.put(sound, getClass().getResource(SOUND_FOLDER + sound));
	}

	@Override
	public void play() {
		if(!enabled)
			return;
		player.play();
	}

	@Override
	public void stop() {
		player.stop();
	}
}
