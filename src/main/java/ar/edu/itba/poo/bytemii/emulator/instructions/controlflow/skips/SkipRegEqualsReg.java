package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;

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
