package ar.edu.itba.poo.bytemii.emulator.instructions;

import ar.edu.itba.poo.bytemii.emulator.utilities.Bitwise;

public class OpCode {
	private int data;

	public int get() {
		return data & 0xFFFF;
	}

	public void set(int part1, int part2) {
		data = Bitwise.merge(part1, part2);
	}

	public int getNibble(int position) {
		return Bitwise.getNibbleAsInt(data, position);
	}

	public int getByte(int position) {
		return Bitwise.getByteAsInt(data, position);
	}

	public int getLast3Bytes() {
		return data & 0xFFF;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(! (o instanceof OpCode)) {
			return false;
		}

		OpCode opCode = (OpCode) o;

		return data == opCode.data;
	}

	@Override
	public int hashCode() {
		return data;
	}

	@Override
	public String toString() {
		return Integer.toHexString(data);
	}
}
