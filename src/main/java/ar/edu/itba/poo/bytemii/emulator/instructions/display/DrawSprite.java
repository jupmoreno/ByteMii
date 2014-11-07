package ar.edu.itba.poo.bytemii.emulator.instructions.display;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.hardware.Display;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionDataInstruction;

public class DrawSprite extends DoublePositionDataInstruction {
	@Override
	public void execute( CPU cpu ) {
		int x = cpu.getRegistry(position1).get();
		int y = cpu.getRegistry(position2).get();
		int height = data;

		cpu.getRegistry(0xF).set(0);
		for(int offsetY = 0; offsetY < height; offsetY++) {
			int line = cpu.getMemoryMap().getMemory(MemoryType.RAM).get(cpu.getRegisterI().get() + offsetY);
			for(int offsetX = 0; offsetX < 8; offsetX++) {
				int pixel =  line & (0x80 >> offsetX);
				if(pixel != 0) {
					int totalX = (x + offsetX) % Display.WIDTH;
					int totalY = (y + offsetY) % Display.HEIGHT;
					int index;

					index = (totalY * Display.WIDTH) + totalX;
					if(cpu.getMemoryMap().getMemory(MemoryType.DISPLAY).get(index) == 1)
						cpu.getRegistry(0xF).set(Display.PIXEL_ON);
					cpu.getMemoryMap().getMemory(MemoryType.DISPLAY).set(index, cpu.getMemoryMap().getMemory(MemoryType.DISPLAY).get(index) ^ 1);
				}
			}
		}
		cpu.setDisplayRedraw(true);
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xD) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
