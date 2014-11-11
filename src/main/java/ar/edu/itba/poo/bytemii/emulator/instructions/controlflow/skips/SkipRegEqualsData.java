package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;

/**
 * 3xkk - SE Vx, byte
 * Skip next instruction if Vx = kk.
 * The interpreter compares register Vx to kk, and if they are equal, increments the program counter by 2.
 */

public class SkipRegEqualsData extends SkipIfData {

	public SkipRegEqualsData() {
		super(true);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x3) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
