package ar.edu.itba.poo.bytemii.emulator.hardware.cpu;

import ar.edu.itba.poo.bytemii.emulator.utilities.Bitwise;

public class Register {
	public static final int MIN_VALUE = 0;
	public final int maxValue;

	private int data;

	public Register( int maxValue ) {
		this.maxValue = maxValue;
	}

	public int get() {
		return data;
	}

	public void set(int data) {
		this.data = data % (maxValue + 1);
		if(this.data < 0)
			this.data += (maxValue + 1);
	}

	public void set(Register reg) {
		set(reg.get());
	}

	public void and(Register reg) {
		set(Bitwise.and(data, reg.get()));
	}

	public void or(Register reg) {
		set(Bitwise.or(data, reg.get()));
	}

	public void xor(Register reg) {
		set(Bitwise.xor(data, reg.get()));
	}

	public boolean add(Register reg) {
		int ret = data + reg.get();
		set(ret);
		return ret > maxValue;
	}

	public boolean add(int value) {
		int ret = data + value;
		set(ret);
		return ret > maxValue;
	}

	public boolean sub(Register reg) {
		int ret = data - reg.get();
		set(ret);
		return ret < MIN_VALUE;
	}

	public boolean sub(int value) {
		int ret = data - value;
		set(ret);
		return ret < MIN_VALUE;
	}

	@Override
	public boolean equals( Object o ) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		Register register = (Register) o;

		return data == register.data;
	}

	@Override
	public int hashCode() {
		return data;
	}

	public int getMaxValue() {
		return maxValue;
	}
}
