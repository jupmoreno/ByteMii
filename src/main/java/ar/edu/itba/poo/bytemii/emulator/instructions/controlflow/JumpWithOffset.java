package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.AddressInstruction;

public class JumpWithOffset extends AddressInstruction {
	@Override
	public void execute( CPU cpu ) {
		if(logger.isDebugEnabled()) {
			logger.debug("Jump {} + V0{{}}", address, cpu.getRegistry(0x0));
		}
		cpu.getInstPointer().set(address + cpu.getRegistry(0x0).get());
		logger.info("Instruction Pointer set to {}", cpu.getInstPointer().get());
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
