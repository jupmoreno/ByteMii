package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.subroutines;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetSubroutine implements Instruction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void execute( CPU cpu ) {
		if(logger.isDebugEnabled()) {
			logger.debug("Return from Subroutine");
		}
		cpu.getInstPointer().set(cpu.getStack().pop());
		logger.info("Instruction Pointer set to {}", cpu.getInstPointer().get());
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		return opCode.get() == 0x00EE;
	}
}
