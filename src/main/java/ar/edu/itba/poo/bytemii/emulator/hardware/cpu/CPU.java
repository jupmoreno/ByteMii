package ar.edu.itba.poo.bytemii.emulator.hardware.cpu;

import ar.edu.itba.poo.bytemii.emulator.utilities.Bitwise;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.Stack;
import ar.edu.itba.poo.bytemii.emulator.instructions.Instruction;
import ar.edu.itba.poo.bytemii.emulator.instructions.InstructionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Emulates a real CPU
 */

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

	/**
	 * Creates a CPU with desired Memory Map and the Instructions it can execute
	 * @param memoryMap
	 * @param instructions
	 */

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

	/**
	 * Sets the CPU to it's initial state.
	 */

	public void clear() {
		instPointer.set(CPU.INST_POINTER_START);
		for(int i = 0; i < CPU.REGISTRY_SIZE; i++)
			registry.get(i).set(0x0);
		registerI.set(0x0);
		soundTimer.set(0x0);
		delayTimer.set(0x0);
		stack.clear();
	}

	/**
	 * Brings from the memory Map the RAM which has the dump of the ROM File and fetches the next instruction to be decoded.
	 */

	public void fetchOpCode() {
		byte part1 = memoryMap.getMemory(MemoryType.RAM).get(instPointer.get());
		byte part2 = memoryMap.getMemory(MemoryType.RAM).get(instPointer.get() + 1);
		opCode.set(part1, part2);
		System.out.println("EXECUTING OPCODE: " + Integer.toHexString(opCode.get()));
	}

	/**
	 * Parses the fetched opCode and the CPU decides whether the binary code corresponds to an existing instruction
	 * @return int
	 * @throws InstructionException
	 */

	public int decodeOpCode() throws InstructionException {
		for (int i = 0; i < instructions.size(); i++)
			if (instructions.get(i).validate(opCode))
				return i;
		throw new InstructionException("Instruction not Supported");
	}

	/**
	 * Runs the fetched and decoded instruction, modifying registers of the CPU or allocated memory for the game
	 * @param index
	 */

	public void runOpCode(int index) { // TODO: Deberia recibir INDEX o tenerlo dentro del CPU?
		instructions.get(index).execute(this);
	}

	/**
	 * Sets if the Display should be re painted or not.
	 * @param value boolean
	 */

	public void setDisplayRedraw( boolean value ) {
		displayRedraw = value;
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

	/**
	 * Returns the Register at Registry's position.
	 * @param position
	 * @return Register
	 */

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
}
