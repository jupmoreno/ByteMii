package ar.edu.itba.poo.bytemii.emulator.instructions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DoublePositionDataInstruction implements Instruction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected int position1;
	protected int position2;
	protected int data;

	protected void setValues(OpCode opCode) {
		System.out.println("Opcode: " + opCode);
		position1 = opCode.getNibble(1);
		position2 = opCode.getNibble(2);
		data = opCode.getNibble(3);
		System.out.println("Pos1: " + position1 + ", Pos2: " + position2 + ", Data: " + data);
	}
}
