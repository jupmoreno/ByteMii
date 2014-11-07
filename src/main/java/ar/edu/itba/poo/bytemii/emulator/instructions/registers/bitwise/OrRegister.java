package ar.edu.itba.poo.bytemii.emulator.instructions.registers.bitwise;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionInstruction;

public class OrRegister extends DoublePositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		System.out.println("OR ------------------------------------------------------------------------------------------------------");
		cpu.getRegistry(position1).or(cpu.getRegistry(position2));
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x1) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
