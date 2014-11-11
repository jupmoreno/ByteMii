package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;

/**
 * 4xkk - SNE Vx, byte
 * Skip next instruction if Vx != kk.
 * The interpreter compares register Vx to kk, and if they are not equal, increments the program counter by 2.
 */

public class SkipRegNotEqualsData extends SkipIfData {

	public SkipRegNotEqualsData() {
		super(false);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x4) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
