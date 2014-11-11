package ar.edu.itba.poo.bytemii.emulator.instructions.registers.math;

import ar.edu.itba.poo.bytemii.emulator.utilities.Bitwise;
import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionDataInstruction;

import java.util.Random;

/**
 * Cxkk - RND Vx, byte
 * Set Vx = random byte AND kk.
 * The interpreter generates a random number from 0 to 255, which is then ANDed with the value kk. The results are
 * stored in Vx. See instruction 8xy2 for more information on AND.
 */

public class RandomData extends PositionDataInstruction {
	private Random rand;

	public RandomData() {
		this.rand = new Random();
	}

	@Override
	public void execute( CPU cpu ) {
		cpu.getRegistry(position).set(Bitwise.and(data, rand.nextInt(cpu.getRegistry(position).getMaxValue())));
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
