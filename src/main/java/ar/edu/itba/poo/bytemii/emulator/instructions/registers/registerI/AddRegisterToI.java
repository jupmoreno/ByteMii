package ar.edu.itba.poo.bytemii.emulator.instructions.registers.registerI;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

/**
 * Fx1E - ADD I, Vx
 * Set I = I + Vx.
 * The values of I and Vx are added, and the results are stored in I.
 */

public class AddRegisterToI extends PositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		cpu.getRegisterI().add(cpu.getRegistry(position));
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xF && opCode.getByte(1) == 0x1E) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
