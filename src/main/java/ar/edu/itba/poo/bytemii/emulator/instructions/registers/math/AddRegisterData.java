package ar.edu.itba.poo.bytemii.emulator.instructions.registers.math;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionInstruction;

/**
 * 8xy4 - ADD Vx, Vy
 * Set Vx = Vx + Vy, set VF = carry.
 * The values of Vx and Vy are added together. If the result is greater than 8 bits (i.e., > 255,) VF is set to 1,
 * otherwise 0. Only the lowest 8 bits of the result are kept, and stored in Vx.
 */

public class AddRegisterData extends DoublePositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		if(cpu.getRegistry(position1).add(cpu.getRegistry(position2)))
			cpu.getRegistry(0xF).set(0x1);
		else
			cpu.getRegistry(0xF).set(0x0);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x4) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
