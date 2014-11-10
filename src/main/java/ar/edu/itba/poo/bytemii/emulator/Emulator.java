package ar.edu.itba.poo.bytemii.emulator;

import ar.edu.itba.poo.bytemii.emulator.hardware.Display;
import ar.edu.itba.poo.bytemii.emulator.hardware.Sound;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.MemoryMap;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.Memory;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Emulator {
	private final Display display;
	private final Sound sound;
	private final CPU cpu;

	private final Engine engine;
	private Thread engineThread;
	private GameState gameState;


	private Map<String, Integer> speeds;
	private String activeSpeed;

	private File rom;

	public Emulator(Display display, Sound sound) {
		if(sound == null || display == null)
			throw new IllegalArgumentException();

		this.sound = sound;
		this.display = display;

		cpu = new CPU(initMemoryMap(), Resources.getInstance().getInstructions());

		engine = new Engine(cpu, display, sound);

		gameState = GameState.NO_GAME;

		speeds = new HashMap<>();

        setAvailableSpeeds();
	}

	private void setAvailableSpeeds() {
	    speeds.put("Slow", 3);
		speeds.put("Normal", 2);
		speeds.put("Fast", 1);

		activeSpeed = "Normal";
		setActiveSpeed(activeSpeed);
	}

	private MemoryMap initMemoryMap() {
		MemoryMap memoryMap = new MemoryMap();
		memoryMap.addMemory(MemoryType.RAM, new Memory(Resources.RAM_SIZE));
		memoryMap.addMemory(MemoryType.DISPLAY, new Memory(Display.WIDTH * Display.HEIGHT));
		memoryMap.addMemory(MemoryType.KEYBOARD, new Memory(Resources.KEYBOARD_SIZE));
		return memoryMap;
	}

	/**
	 * Initialices the engine in a new Thread and dumps the ROM file into the RAM memory to start emulation
	 * @param file
	 * @throws IOException
	 */

	public void start(File file) throws IOException {
		if(file == null || !file.isFile() || !file.canRead())
			throw new IllegalArgumentException("Illegal rom file");
		rom = file;

		if(gameState != GameState.NO_GAME)
			throw new  IllegalStateException("There should be no Game playing"); // TODO: cual exception tirar?

		loadROM(rom);
		loadFontSet();

		engineThread = new Thread(engine);
		engineThread.start();

		gameState = GameState.PLAYING;
	}

	public void stop() {
		if(gameState == GameState.NO_GAME)
			throw new  IllegalStateException();

		engine.setInterrupted(true);
		engineThread = null;

		cpu.getMemoryMap().getMemory(MemoryType.RAM).clear();
		cpu.getMemoryMap().getMemory(MemoryType.DISPLAY).clear();
		cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).clear();
		cpu.clear();

		display.paint(cpu.getMemoryMap().getMemory(MemoryType.DISPLAY));

		gameState = GameState.NO_GAME;
	}

	public void restart() {
		if(gameState == GameState.NO_GAME)
			throw new  IllegalStateException();

		cpu.getMemoryMap().getMemory(MemoryType.DISPLAY).clear();
		cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).clear();
		cpu.clear();

		display.paint(cpu.getMemoryMap().getMemory(MemoryType.DISPLAY));
	}

	public void pause() {
		if(gameState != GameState.PLAYING)
			throw new  IllegalStateException();

		engine.setInterrupted(true);
		engineThread = null;

		gameState = GameState.PAUSE;
	}

	public void resume() {
		if(gameState != GameState.PAUSE)
			throw new  IllegalStateException();

		engineThread = new Thread(engine);
		engineThread.start();

		gameState = GameState.PLAYING;
	}

	private void loadFontSet() {
		for(int i = 0; i < Resources.getFontSet().length; i++)
			cpu.getMemoryMap().getMemory(MemoryType.RAM).set( Resources.FONTSET_POSITION + i, Resources.getFontSet()[i]);
	}

	private void loadROM(File rom) throws IOException {
		try(InputStream in = new BufferedInputStream(new FileInputStream(rom))) { // Automatically cleans up the InputStream
 			int data = in.read();

			for(int i = 0; data != -1; i++) {
				cpu.getMemoryMap().getMemory(MemoryType.RAM).set(Resources.ROM_POSITION + i, data);
				data = in.read();
			}
		}
	}

	public void pressedKey(int position) {
		cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).set(position, Resources.KEY_PRESSED);
	}

	public void releasedKey(int position) {
		cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).set(position, Resources.KEY_RELEASED);
	}

	public boolean setActiveSpeed( String wantedSpeed ) {
		if(speeds.containsKey(wantedSpeed)) {
			engine.setSpeed(speeds.get(wantedSpeed));
			activeSpeed = wantedSpeed;
			return true;
		}
		return false;
	}

	public Set<String> getSpeeds() {
		return speeds.keySet();
	}

	public String getActiveSpeed() {
		return activeSpeed;
	}

	public GameState getState() {
		return gameState;
	}
}
