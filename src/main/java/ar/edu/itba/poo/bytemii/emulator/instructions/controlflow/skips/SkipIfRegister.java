package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.DoublePositionInstruction;

public abstract class SkipIfRegister extends DoublePositionInstruction {
	private boolean condition;

	protected SkipIfRegister( boolean condition ) {
		this.condition = condition;
	}

	@Override
	public void execute( CPU cpu ) {
		if((cpu.getRegistry(position1).equals(cpu.getRegistry(position2))) == condition) {
			cpu.getInstPointer().add(STEP);
		}
		cpu.getInstPointer().add(STEP);
	}
}
