package ar.edu.itba.poo.bytemii.emulator.instructions.registers.math;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionInstruction;

public class SubRegisterData extends DoublePositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		if(cpu.getRegistry(position1).sub(cpu.getRegistry(position2)))
			cpu.getRegistry(0xF).set(0x0);
		else
			cpu.getRegistry(0xF).set(0x1);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x5) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
