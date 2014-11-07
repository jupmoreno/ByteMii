package ar.edu.itba.poo.bytemii.emulator.instructions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AddressInstruction implements Instruction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected int address;

	protected void setValues(OpCode opCode) {
		System.out.println("Opcode: " + opCode);
		this.address = opCode.getLast3Bytes();
		System.out.println("Data: " + address);
	}
}
