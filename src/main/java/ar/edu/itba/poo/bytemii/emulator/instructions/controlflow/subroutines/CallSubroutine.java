package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.subroutines;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.Jump;

public class CallSubroutine extends Jump {
	@Override
	public void execute( CPU cpu ) {
		if(logger.isDebugEnabled()) {
			logger.debug("Call subroutine at {}", address);
		}
		cpu.getStack().push(cpu.getInstPointer().get()); // TODO: Hacer logger en Stack
        super.execute(cpu);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x2) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}