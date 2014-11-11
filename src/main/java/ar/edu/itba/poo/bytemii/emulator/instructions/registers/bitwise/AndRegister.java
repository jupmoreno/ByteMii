package ar.edu.itba.poo.bytemii.emulator.instructions.registers.bitwise;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionInstruction;

/**
 * 8xy2 - AND Vx, Vy
 * Set Vx = Vx AND Vy.
 * Performs a bitwise AND on the values of Vx and Vy, then stores the result in Vx. A bitwise AND compares the
 * corrseponding bits from two values, and if both bits are 1, then the same bit in the result is also 1.
 * Otherwise, it is 0.
 */

public class AndRegister extends DoublePositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		cpu.getRegistry(position1).and(cpu.getRegistry(position2));
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x2) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
