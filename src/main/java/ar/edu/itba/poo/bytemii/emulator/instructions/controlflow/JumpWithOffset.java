package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.AddressInstruction;

/**
 * Bnnn - JP V0, addr
 * Jump to location nnn + V0.
 * The program counter is set to nnn plus the value of V0.
 */

public class JumpWithOffset extends AddressInstruction {
	@Override
	public void execute( CPU cpu ) {
		cpu.getInstPointer().set(address + cpu.getRegistry(0x0).get());
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xB) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
