package ar.edu.itba.poo.bytemii.emulator.instructions.keyboard;

import ar.edu.itba.poo.bytemii.emulator.Resources;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

public class WaitForKey extends PositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		int keyboardSize = cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).size();

		for(int i = 0; i < keyboardSize; i++) {
			if(cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).get(i) == Resources.KEY_PRESSED) {
				cpu.getRegistry(position).set(i);
				cpu.getMemoryMap().getMemory(MemoryType.KEYBOARD).clear();
				cpu.getInstPointer().add(STEP);
				return;
			}
		}
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xF && opCode.getByte(1) == 0x0A) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
