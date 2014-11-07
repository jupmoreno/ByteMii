package ar.edu.itba.poo.bytemii.emulator.hardware.cpu;

import ar.edu.itba.poo.bytemii.emulator.utilities.Bitwise;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.Stack;
import ar.edu.itba.poo.bytemii.emulator.instructions.Instruction;
import ar.edu.itba.poo.bytemii.emulator.instructions.InstructionException;

import java.util.ArrayList;
import java.util.List;

public class CPU {
	public static final int REGISTRY_SIZE = 16;
	public static final int INST_POINTER_START = 0x200;

	private final MemoryMap memoryMap;
	private final List<Instruction> instructions;

	private final List<Register> registry;
	private final Register registerI;
	private final Register instPointer;
	private final Register delayTimer;
	private final Register soundTimer;

	private Stack stack;

	private OpCode opCode;

	private boolean displayRedraw;

	public CPU(MemoryMap memoryMap, List<Instruction> instructions) {
		this.memoryMap = memoryMap;
		this.instructions = instructions;

		registry = new ArrayList<>();
		initRegistry();

		registerI = new Register(Bitwise.UNSIGNED_SHORT_MAX);
		instPointer = new Register(Bitwise.UNSIGNED_SHORT_MAX);
		instPointer.set(INST_POINTER_START);

		delayTimer = new Register(Bitwise.UNSIGNED_BYTE_MAX);
		soundTimer = new Register(Bitwise.UNSIGNED_BYTE_MAX);

		stack = new Stack(memoryMap.getMemory(MemoryType.RAM));

		opCode = new OpCode();

		displayRedraw = false;
	}

	private void initRegistry() {
		for (int i = 0; i < REGISTRY_SIZE; i++)
			registry.add(i, new Register(Bitwise.UNSIGNED_BYTE_MAX));
	}

	public void fetchOpCode() {
		byte part1 = memoryMap.getMemory(MemoryType.RAM).get(instPointer.get());
		byte part2 = memoryMap.getMemory(MemoryType.RAM).get(instPointer.get() + 1);
		opCode.set(part1, part2);
		System.out.println("EXECUTING OPCODE: " + Integer.toHexString(opCode.get()));
	}

	public int decodeOpCode() throws InstructionException {
		for (int i = 0; i < instructions.size(); i++)
			if (instructions.get(i).validate(opCode))
				return i;
		throw new InstructionException("Instruction not Supported");
	}

	public void clear() {
		instPointer.set(CPU.INST_POINTER_START);
		for(int i = 0; i < CPU.REGISTRY_SIZE; i++)
			registry.get(i).set(0x0);
		registerI.set(0x0);
		soundTimer.set(0x0);
		delayTimer.set(0x0);
		stack.clear();
	}

	public void runOpCode(int index) {
		instructions.get(index).execute(this);
	}

	public MemoryMap getMemoryMap() {
		return memoryMap;
	}

	public Register getInstPointer() {
		return instPointer;
	}

	public OpCode getOpCode() {
		return opCode;
	}

	public Stack getStack() {
		return stack;
	}

	public Register getRegistry(int position) {
		return registry.get(position);
	}

	public Register getRegisterI() {
		return registerI;
	}

	public Register getDelayTimer() {
		return delayTimer;
	}

	public Register getSoundTimer() {
		return soundTimer;
	}

	public boolean getDisplayRedraw() {
		return displayRedraw;
	}

	public void setDisplayRedraw( boolean value ) {
		displayRedraw = value;
	}
}
