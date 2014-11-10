package ar.edu.itba.poo.bytemii.emulator.instructions;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;

public interface Instruction {
	static final int STEP = 2;

	void execute(CPU cpu);
	boolean validate(OpCode opCode);
}