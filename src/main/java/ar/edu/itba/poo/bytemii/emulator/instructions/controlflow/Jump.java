package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.AddressInstruction;

/**
 * 1nnn - JP addr
 * Jump to location nnn.
 * The interpreter sets the program counter to nnn.
 */
public class Jump extends AddressInstruction {
	@Override
	public void execute( CPU cpu ) {
		if(logger.isDebugEnabled()) {
			logger.debug("Jump {}", address);
		}
		cpu.getInstPointer().set(address);
		logger.info("Instruction Pointer set to {}", cpu.getInstPointer().get());
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x1) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
