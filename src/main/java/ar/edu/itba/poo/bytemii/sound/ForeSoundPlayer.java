package ar.edu.itba.poo.bytemii.sound;

import ar.edu.itba.poo.bytemii.emulator.hardware.Sound;
import javafx.scene.media.AudioClip;

public class ForeSoundPlayer extends MultiFilesPlayer implements Sound {
	private static final String SOUND_FOLDER = "/sounds/fore/";
	private static final String[] SOUNDS = {"Beep.mp3"};

	private AudioClip player;

	public ForeSoundPlayer() {
		super();
		setDefaultFiles();
		setFile(prefs.get(SOUND_FILE, SOUNDS[0]));
	}

	@Override
	protected void initPlayer() {
		player = new AudioClip(soundFiles.get(activeSound).toExternalForm());
	}

	private void setDefaultFiles() {
		for(String sound : SOUNDS) {
			soundFiles.put(sound, getClass().getResource(SOUND_FOLDER + sound));
		}
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
