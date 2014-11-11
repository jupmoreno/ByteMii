package ar.edu.itba.poo.bytemii.emulator.instructions.registers.bitwise;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionInstruction;

/**
 * 8xy3 - XOR Vx, Vy
 * Set Vx = Vx XOR Vy.
 * Performs a bitwise exclusive OR on the values of Vx and Vy, then stores the result in Vx. An exclusive OR compares
 * the corrseponding bits from two values, and if the bits are not both the same, then the corresponding bit in the
 * result is set to 1. Otherwise, it is 0.
 */

public class XorRegister extends DoublePositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		cpu.getRegistry(position1).xor(cpu.getRegistry(position2));
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x3) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
