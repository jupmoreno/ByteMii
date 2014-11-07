package ar.edu.itba.poo.bytemii.emulator.utilities;

public abstract class Bitwise {
	public static final int UNSIGNED_BYTE_MAX = Byte.MAX_VALUE * 2 + 1;
	public static final int UNSIGNED_SHORT_MAX = Short.MAX_VALUE * 2 + 1;

	public static int and(int value1, int value2) {
		return value1 & value2;
	}

	public static byte and(byte value1, byte value2) {
		return (byte) (value1 & value2);
	}

	public static int or(int value1, int value2) {
		return value1 | value2;
	}

	public static byte or(byte value1, byte value2) {
		return (byte) (value1 | value2);
	}

	public static int xor(int value1, int value2) {
		return value1 ^ value2;
	}

	public static byte xor(byte value1, byte value2) {
		return (byte) (value1 ^ value2);
	}

	public static int merge(int value1, int value2) {
		return (getByteAsInt(value1, 1) << 8) | getByteAsInt(value2, 1);
	}

	public static byte getNibble(int value, int index) {
		if(index < 0 || index >= 4)
			throw new IndexOutOfBoundsException();
		return (byte) ((value >>> (3 - index) * 4) & 0x0F);
	}

	public static int getNibbleAsInt(int value, int index) {
		if(index < 0 || index >= 4)
			throw new IndexOutOfBoundsException();
		return ((value >>> (3 - index) * 4) & 0x0F);
	}

	public static byte getByte(int value, int index) {
		if(index < 0 || index >= 2)
			throw new IndexOutOfBoundsException();
		return (byte) ((value >>> (1 - index) * 8) & 0xFF);
	}

	public static int getByteAsInt(int value, int index) {
		if(index < 0 || index >= 2)
			throw new IndexOutOfBoundsException();
		return ((value >>> (1 - index) * 8) & 0xFF);
	}
}
