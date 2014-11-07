package ar.edu.itba.poo.bytemii.emulator.instructions.registers.memory;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

public class SetRegistryFromRAM extends PositionInstruction {
	public void execute(CPU cpu) {
		for(int i=0; i <= position; i++) {
			cpu.getRegistry(i).set(cpu.getMemoryMap().getMemory(MemoryType.RAM).get(cpu.getRegisterI().get() + i));
		}
		//cpu.getRegisterI().add(position + 1);
		cpu.getInstPointer().add(STEP);
	}
	public boolean validate(OpCode opCode) {
		if(opCode.getNibble(0) == 0x0F && opCode.getByte(1) == 0x65) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
