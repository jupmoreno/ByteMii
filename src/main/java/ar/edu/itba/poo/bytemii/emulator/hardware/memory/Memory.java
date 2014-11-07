package ar.edu.itba.poo.bytemii.emulator.hardware.memory;

import java.util.Arrays;

/**
 *
 * Represents a virtual memory of bytes.
 *
 */

public class Memory {
	private final int size;
	private final byte[] memory;

	/**
	 * Generates the Memory of specified size.
	 * @param size cannot be <= 0
	 */

	public Memory(int size) {
		if(size <= 0)
			throw new IllegalArgumentException();
		memory = new byte[size];
		this.size = size;
	}

	/**
	 * Returns size of Memory.
	 * @return int
	 */

	public int size() {
		return size;
	}

	public byte get(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		return memory[index];
	}

	/**
	 * Returns the byte, represented as unsigned, of the specified index.
	 * @param index cannot be less than 0 or greater than size()
	 * @return unsigned byte
	 */

	public int getIntValue(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		return Byte.toUnsignedInt(memory[index]);
	}

	/**
	 * Sets data value into Memory's index.
	 * @param index cannot be less than 0 or greater than size()
	 * @param data cannot be less than 0 or greater than 255
	 */

	public void set(int index, byte data) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		memory[index] = data;
	}

	/**
	 * TODO: Doc
	 * @param index
	 * @param data
	 */

	public void set(int index, int data) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		memory[index] = (byte) data;
	}

	/**
	 * Sets every Memory element to the specified value.
	 * @param data cannot be less than 0 or greater than 255
	 */

	public void fill(int data) {
		for(int i = 0; i < size; i++)
			set(i, data);
	}

    /**
     * Sets every Memory element to 0.
     */

    public void clear() {
        fill(0);
    }

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(! (o instanceof Memory)) {
			return false;
		}

		Memory memory1 = (Memory) o;

		if(size != memory1.size) {
			return false;
		}

		return Arrays.equals(memory, memory1.memory);

	}

	@Override
	public int hashCode() {
		int result = size;
		result = 31 * result + Arrays.hashCode(memory);
		return result;
	}
}