package ar.edu.itba.poo.bytemii.emulator.instructions.registers.registerI;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

/**
 * Fx33 - LD B, Vx
 * Store BCD representation of Vx in memory locations I, I+1, and I+2.
 * The interpreter takes the decimal value of Vx, and places the hundreds digit in memory at location in I, the tens
 * digit at location I+1, and the ones digit at location I+2.
 */

public class StoreRegisterBCDToI extends PositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		int data = cpu.getRegistry(position).get();
		int hundreds = (data - (data % 100)) / 100;
		int tens;

		data -= hundreds * 100;
		tens = (data - (data % 10)) / 10;
		data -= tens * 10;
		cpu.getMemoryMap().getMemory(MemoryType.RAM).set(cpu.getRegisterI().get(), hundreds);
		cpu.getMemoryMap().getMemory(MemoryType.RAM).set(cpu.getRegisterI().get() + 1, tens);
		cpu.getMemoryMap().getMemory(MemoryType.RAM).set(cpu.getRegisterI().get() + 2, data);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xF && opCode.getByte(1) == 0x33) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
