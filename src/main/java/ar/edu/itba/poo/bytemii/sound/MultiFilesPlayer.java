package ar.edu.itba.poo.bytemii.sound;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.prefs.Preferences;

public abstract class MultiFilesPlayer implements Playable {
	protected static final String SOUND_FILE = "sound_file";
	protected static final String SOUND_ENABLED = "sound_enabled";

	protected final Preferences prefs;

	protected final Map<String, URL> soundFiles = new HashMap<>();
	protected String activeSound;

	protected boolean enabled;

	protected MultiFilesPlayer() {
		prefs = Preferences.userRoot().node(getClass().toString());
		enabled = prefs.getBoolean(SOUND_ENABLED, true);
	}

	public boolean setFile(String sound) {
		if(!soundFiles.containsKey(sound))
			return false;
		activeSound = sound;
		initPlayer();
		prefs.put(SOUND_FILE, activeSound);
		return true;
	}

	public String getActiveSound() {
		return activeSound;
	}

	public Set<String> getSounds() {
		return soundFiles.keySet();
	}

	protected abstract void initPlayer();

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled( boolean enabled ) {
		if(!enabled && this.enabled)
			stop();
		this.enabled = enabled;
		prefs.putBoolean(SOUND_ENABLED, this.enabled);
	}
}
