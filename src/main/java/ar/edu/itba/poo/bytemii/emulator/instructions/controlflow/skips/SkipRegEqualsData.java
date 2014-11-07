package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;

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
