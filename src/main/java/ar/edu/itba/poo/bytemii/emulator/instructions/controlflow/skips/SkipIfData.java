package ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionDataInstruction;

public abstract class SkipIfData extends PositionDataInstruction {
	private boolean condition;

	protected SkipIfData( boolean condition ) {
		this.condition = condition;
	}

	@Override
	public void execute( CPU cpu ) {
		if((cpu.getRegistry(position).get() == data) == condition) {
			cpu.getInstPointer().add(STEP);
		}
		cpu.getInstPointer().add(STEP);
	}
}
