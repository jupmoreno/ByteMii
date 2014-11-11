package ar.edu.itba.poo.bytemii.emulator.instructions.registers.bitwise;

import ar.edu.itba.poo.bytemii.emulator.utilities.Bitwise;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionInstruction;

/**
 * 8xy6 - SHR Vx {, Vy}
 * Set Vx = Vx SHR 1.
 * If the least-significant bit of Vx is 1, then VF is set to 1, otherwise 0. Then Vx is divided by 2.
 */

public class RightShiftRegister extends DoublePositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		cpu.getRegistry(0xF).set(Bitwise.and(cpu.getRegistry(position1).get(), 0x0001) > 0 ? 1 : 0);
		cpu.getRegistry(position1).set(cpu.getRegistry(position1).get() / 2);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x6) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
