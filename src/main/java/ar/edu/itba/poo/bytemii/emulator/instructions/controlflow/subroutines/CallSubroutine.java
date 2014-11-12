package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.subroutines;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.Jump;

/**
 * 2nnn - CALL addr
 * The interpreter increments the stack pointer, then puts the current PC on the top of the stack.
 * 	The PC is then set to nnn.
 */
public class CallSubroutine extends Jump {
	@Override
	public void execute( CPU cpu ) {
		cpu.getStack().push(cpu.getInstPointer().get());
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