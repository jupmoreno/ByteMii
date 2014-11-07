package ar.edu.itba.poo.bytemii.emulator.hardware.memory;

import ar.edu.itba.poo.bytemii.emulator.utilities.Bitwise;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.Register;

import java.util.EmptyStackException;

public class Stack {
	private static final int START_POSITION = 0x01E0;
	private static final int SIZE = 16;

	private final Memory reference;
	private final Register stackPointer;

	public Stack(Memory reference) {
		this.reference = reference;
		stackPointer = new Register(Bitwise.UNSIGNED_SHORT_MAX);
		stackPointer.set(START_POSITION);
	}

	public void push(int data) {
		if(isFull())
			throw new StackOverflowError();
		reference.set(stackPointer.get(), Bitwise.getByte(data, 0));
		reference.set(stackPointer.get() + 1, Bitwise.getByte(data, 1));
		stackPointer.add(2);
	}


	public int pop() {
		if(this.isEmpty())
			throw new EmptyStackException();
		byte part1 = reference.get(stackPointer.get() - 2);
		byte part2 = reference.get(stackPointer.get() - 1);

		stackPointer.add(-2);
		return Bitwise.merge(part1, part2);
	}

	public void clear() {
		stackPointer.set(START_POSITION);
	}

	public boolean isEmpty() {
		return stackPointer.get() == START_POSITION;
	}

	public boolean isFull() {
		return stackPointer.get() == (START_POSITION + SIZE);
	}
}
