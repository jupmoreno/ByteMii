package ar.edu.itba.poo.bytemii.emulator.instructions.registers.registerI;

import ar.edu.itba.poo.bytemii.emulator.Resources;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

public class StoreSpriteInI extends PositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		int character = cpu.getRegistry(position).get();
		cpu.getRegisterI().set(Resources.FONTSET_POSITION + character * 5);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xF && opCode.getByte(1) == 0x29) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
