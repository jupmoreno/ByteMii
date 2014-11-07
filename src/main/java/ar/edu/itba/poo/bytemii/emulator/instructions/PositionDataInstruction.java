package ar.edu.itba.poo.bytemii.emulator.instructions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PositionDataInstruction implements Instruction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected int position;
	protected int data;

	protected void setValues(OpCode opCode) {
		System.out.println("Opcode: " + opCode);
		position = opCode.getNibble(1);
		data = opCode.getByte(1);
		System.out.println("Pos: " + position + ", Data: " + data);
	}
}
