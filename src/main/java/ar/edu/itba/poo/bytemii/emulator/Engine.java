package ar.edu.itba.poo.bytemii.emulator;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.hardware.Display;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.hardware.Sound;
import ar.edu.itba.poo.bytemii.emulator.instructions.InstructionException;
import javafx.application.Platform;

public class Engine implements Runnable {
	private static final int MIN_SPEED = 1;
	private static final int DEFAULT_SPEED = 2;
	private static final int MAX_SPEED = 3;

	private volatile boolean interrupted;

	private CPU cpu;
	private Display display;
	private Sound sound;

	private int speed;

	public Engine(CPU cpu, Display display, Sound sound) {
		this.cpu = cpu;
		this.display = display;
		this.sound = sound;

		interrupted = false;
		this.speed = DEFAULT_SPEED;
	}

	/**
	 *
	 */
	public void run() {
		interrupted = false;
		while (!interrupted) {
			try {
				cpu.fetchOpCode();
				cpu.runOpCode(cpu.decodeOpCode());

				if(cpu.getDisplayRedraw()) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							display.paint(cpu.getMemoryMap().getMemory(MemoryType.DISPLAY));
						}
					});
					cpu.setDisplayRedraw(false);
				}
				if (cpu.getSoundTimer().get() > 0) { //While this is satisfied, the game's sound is played
					cpu.getSoundTimer().set(cpu.getSoundTimer().get() - 1);
					sound.play();
				}
				if (cpu.getDelayTimer().get() > 0)
					cpu.getDelayTimer().set(cpu.getDelayTimer().get() - 1);

				Thread.sleep(speed); // Simulates original emulator's speed

			} catch (InstructionException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isInterrupted() {
		return interrupted;
	}

	public void setInterrupted( boolean interrupted ) {
		this.interrupted = interrupted;
	}

	public void setSpeed( int speed ) {
		if(speed < MIN_SPEED || speed > MAX_SPEED)
			throw new IllegalArgumentException();
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}
}
