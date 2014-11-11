package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;

/**
 * 9xy0 - SNE Vx, Vy
 * Skip next instruction if Vx != Vy.
 * The values of Vx and Vy are compared, and if they are not equal, the program counter is increased by 2.
 */

public class SkipRegNotEqualsReg extends SkipIfRegister {

	public SkipRegNotEqualsReg() {
		super(false);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x9 && opCode.getNibble(3) == 0x0) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
