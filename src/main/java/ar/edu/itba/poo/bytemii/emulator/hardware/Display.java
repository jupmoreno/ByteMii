package ar.edu.itba.poo.bytemii.emulator.hardware;

import ar.edu.itba.poo.bytemii.emulator.hardware.memory.Memory;

/**
 * Interface of the Emulator's display
 */

public interface Display {
	int WIDTH = 64;
	int HEIGHT = 32;

	byte PIXEL_ON = 1;
	byte PIXEL_OFF = 0;

	/**
	 * Paints the display using the memory as pixel ON and OFF reference
	 * @param memory must be of WIDTH * HEIGHT size
	 */

	void paint(final Memory memory);
}
