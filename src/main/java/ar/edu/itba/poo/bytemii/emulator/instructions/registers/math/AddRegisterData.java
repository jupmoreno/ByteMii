package ar.edu.itba.poo.bytemii.emulator.instructions.registers.math;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionInstruction;

public class AddRegisterData extends DoublePositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		if(cpu.getRegistry(position1).add(cpu.getRegistry(position2)))
			cpu.getRegistry(0xF).set(0x1);
		else
			cpu.getRegistry(0xF).set(0x0);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x4) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
