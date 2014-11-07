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
	private final Resources resources;

	private final Display display;
	private final Sound sound;

	private final CPU cpu;

	private final Engine engine;
	private Thread engineThread;

	private Map<String, Integer> speeds;
	private String activeSpeed;

	private File rom;

	GameState gameState;

	public Emulator(Display display, Sound sound) {
		if(sound == null || display == null)
			throw new IllegalArgumentException();

		this.sound = sound;
		this.display = display;
		this.resources = Resources.getInstance();

		cpu = new CPU(initMemoryMap(), resources.getInstructions());

		engine = new Engine(cpu, display, sound);

		gameState = GameState.NO_GAME;

		speeds = new HashMap<>();
		speeds.put("Slow", 3);
		speeds.put("Normal", 2);
		speeds.put("Fast", 1);
		activeSpeed = "Normal";
		setSpeed(activeSpeed);
	}

	public void start(File file) throws IOException {
		if(file == null || !file.isFile() || !file.canRead())
			throw new IllegalArgumentException();
		rom = file;

		if(gameState != GameState.NO_GAME)
			throw new  IllegalStateException(); // TODO: cual exception tirar?

		loadROM(rom);
		loadFontSet();

		engineThread = new Thread(engine);
		engine.resume();
		engineThread.start();

		gameState = GameState.PLAYING;
	}

	public void stop() {
		if(gameState == GameState.NO_GAME)
			throw new  IllegalStateException();

		engine.stop();
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

		engine.stop();
		engineThread = null;

		gameState = GameState.PAUSE;
	}

	public void resume() {
		if(gameState != GameState.PAUSE)
			throw new  IllegalStateException();

		engine.resume();
		engineThread = new Thread(engine);
		engineThread.start();

		gameState = GameState.PLAYING;
	}

	private MemoryMap initMemoryMap() {
		MemoryMap memoryMap = new MemoryMap();
		memoryMap.setMemory(MemoryType.RAM, new Memory(Resources.RAM_SIZE));
		memoryMap.setMemory(MemoryType.DISPLAY, new Memory(Display.WIDTH * Display.HEIGHT));
		memoryMap.setMemory(MemoryType.KEYBOARD, new Memory(Resources.KEYBOARD_SIZE));
		return memoryMap;
	}

	private void loadFontSet() {
		for(int i = 0; i < Resources.FONTSET.length; i++)
			cpu.getMemoryMap().getMemory(MemoryType.RAM).set( Resources.FONTSET_POSITION + i, Resources.FONTSET[i]);
	}

	private void loadROM(File rom) throws IOException {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(rom));
			int data = in.read();

			for(int i = 0; data != -1; i++) {
				cpu.getMemoryMap().getMemory(MemoryType.RAM).set(Resources.ROM_POSITION + i, data);
				data = in.read();
			}
		} catch(IOException e) {
			System.out.println("Failed to read ROM file.");
			e.printStackTrace(); // TODO: SALIR...
		} finally {
			try { if(in != null) in.close(); } catch(IOException e) {/* Quiet closing */}
		}
	}

	public void pressedKey(int position) {
		cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).set(position, Resources.KEY_PRESSED);
	}

	public void releasedKey(int position) {
		cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).set(position, Resources.KEY_RELEASED);
	}

	public Set<String> getSpeeds() {
		return speeds.keySet();
	}

	public String getActiveSpeed() {
		return activeSpeed;
	}

	public boolean setSpeed(String wantedSpeed) {
		if(speeds.containsKey(wantedSpeed)) {
			engine.setSpeed(speeds.get(wantedSpeed));
			activeSpeed = wantedSpeed;
			return true;
		}
		return false;
	}

	public GameState getState() {
		return gameState;
	}
}
