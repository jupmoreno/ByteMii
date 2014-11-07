package ar.edu.itba.poo.bytemii.emulator.instructions.registers.math;

import ar.edu.itba.poo.bytemii.emulator.utilities.Bitwise;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionDataInstruction;

import java.util.Random;

public class RandomData extends PositionDataInstruction {
	Random rand;

	public RandomData() {
		this.rand = new Random();
	}

	@Override
	public void execute( CPU cpu ) {
		cpu.getRegistry(position).set(Bitwise.and(data, rand.nextInt(cpu.getRegistry(position).MAX_VALUE)));
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xC) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}
