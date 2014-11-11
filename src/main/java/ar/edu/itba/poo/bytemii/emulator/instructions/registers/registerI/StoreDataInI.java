package ar.edu.itba.poo.bytemii.emulator.instructions.registers.registerI;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.AddressInstruction;

/**
 * Annn - LD I, addr
 * Set I = nnn.
 * The value of register I is set to nnn.
 */

public class StoreDataInI extends AddressInstruction {
	@Override
	public void execute( CPU cpu ) {
		cpu.getRegisterI().set(address);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xA) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
