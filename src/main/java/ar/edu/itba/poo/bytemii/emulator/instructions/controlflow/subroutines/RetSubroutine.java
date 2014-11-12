package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.subroutines;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 00EE - RET
 * Jump to location nnn. The interpreter sets the program counter to nnn.
 */
public class RetSubroutine implements Instruction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void execute( CPU cpu ) {
		cpu.getInstPointer().set(cpu.getStack().pop());
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		return opCode.get() == 0x00EE;
	}
}
