package ar.edu.itba.poo.bytemii.emulator.instructions.keyboard;

import ar.edu.itba.poo.bytemii.emulator.Resources;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

/**
 * ExA1 - SKNP Vx
 * Skip next instruction if key with the value of Vx is not pressed.
 * Checks the keyboard, and if the key corresponding to the value of Vx is currently in the up position, PC is increased by 2.
 */

public class SkipIfKeyNotPressed extends PositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		int key = cpu.getRegistry(position).get();

		if(cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).getIntValue(key) == Resources.KEY_RELEASED)
			cpu.getInstPointer().add(STEP);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xE && opCode.getByte(1) == 0xA1) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
