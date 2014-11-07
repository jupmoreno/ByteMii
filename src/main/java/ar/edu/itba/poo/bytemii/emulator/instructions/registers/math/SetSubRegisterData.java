package ar.edu.itba.poo.bytemii.emulator.instructions.registers.math;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionInstruction;

public class SetSubRegisterData extends DoublePositionInstruction { // TODO: CAMBIARLE EL NOMBRE
	@Override
	public void execute( CPU cpu ) {
		int oldData = cpu.getRegistry(position2).get();
		if(cpu.getRegistry(position2).sub(cpu.getRegistry(position1)))
			cpu.getRegistry(0xF).set(0x0);
		else
			cpu.getRegistry(0xF).set(0x1);
		cpu.getRegistry(position1).set(cpu.getRegistry(position2));
		cpu.getRegistry(position2).set(oldData);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x7) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
