package ar.edu.itba.poo.bytemii.emulator.instructions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DoublePositionDataInstruction implements Instruction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected int position1;
	protected int position2;
	protected int data;

	protected void setValues(OpCode opCode) {
		position1 = opCode.getNibble(1);
		position2 = opCode.getNibble(2);
		data = opCode.getNibble(3);
		if(logger.isDebugEnabled()) {
			logger.debug("Decoding Opcode -> Position 1: {} - Position 2: {} - Data: {}", position1, position2, data);
		}
	}
}
