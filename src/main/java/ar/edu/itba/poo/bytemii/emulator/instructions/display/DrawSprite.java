package ar.edu.itba.poo.bytemii.emulator.instructions.display;

import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.hardware.Display;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionDataInstruction;

/**
 * Dxyn - DRW Vx, Vy, nibble
 * Display n-byte sprite starting at memory location I at (Vx, Vy), set VF = collision.
 * The interpreter reads n bytes from memory, starting at the address stored in I. These bytes are then displayed as
 * sprites on screen at coordinates (Vx, Vy). Sprites are XORed onto the existing screen. If this causes any pixels to
 * be erased, VF is set to 1, otherwise it is set to 0. If the sprite is positioned so part of it is outside the
 * coordinates of the display, it wraps around to the opposite side of the screen. See instruction 8xy3 for more
 * information on XOR, and section 2.4, Display, for more information on the Chip-8 screen and sprites.
 */

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
