package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;

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
