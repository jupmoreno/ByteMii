package ar.edu.itba.poo.bytemii.emulator.instructions.registers;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionDataInstruction;

public class StoreData extends PositionDataInstruction {
	@Override
	public void execute( CPU cpu ) {
		cpu.getRegistry(position).set(data);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0x6) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
