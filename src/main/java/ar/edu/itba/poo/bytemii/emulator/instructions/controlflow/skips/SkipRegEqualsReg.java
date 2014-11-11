package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;

/**
 * 5xy0 - SE Vx, Vy
 * Skip next instruction if Vx = Vy.
 * The interpreter compares register Vx to register Vy, and if they are equal, increments the program counter by 2.
 */

public class SkipRegEqualsReg extends SkipIfRegister {

	public SkipRegEqualsReg() {
		super(true);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x5 && opCode.getNibble(3) == 0x0) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
