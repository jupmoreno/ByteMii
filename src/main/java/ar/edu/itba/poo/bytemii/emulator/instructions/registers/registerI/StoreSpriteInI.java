package ar.edu.itba.poo.bytemii.emulator.instructions.registers.registerI;

import ar.edu.itba.poo.bytemii.emulator.Resources;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

/**
 * Fx29 - LD F, Vx
 * Set I = location of sprite for digit Vx.
 * The value of I is set to the location for the hexadecimal sprite corresponding to the value of Vx. See section 2.4,
 * Display, for more information on the Chip-8 hexadecimal font.
 */

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
