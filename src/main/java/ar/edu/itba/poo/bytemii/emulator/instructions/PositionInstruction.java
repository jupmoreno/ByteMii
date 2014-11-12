package ar.edu.itba.poo.bytemii.emulator.instructions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PositionInstruction implements Instruction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected int position;

	protected void setValues(OpCode opCode) {
		position = opCode.getNibble(1);
		if(logger.isDebugEnabled()) {
			logger.debug("Decoding Opcode -> Position: {}", position);
		}
	}
}
