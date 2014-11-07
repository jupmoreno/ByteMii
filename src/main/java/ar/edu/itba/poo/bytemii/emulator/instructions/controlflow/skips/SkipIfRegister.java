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
		if(logger.isDebugEnabled()) {
			logger.debug("Skip V{}{{}} {}= V{}{{}}", position1, cpu.getRegistry(position1).get(),  condition ? "=" : "!", position2, cpu.getRegistry(position2).get());
		}
		if((cpu.getRegistry(position1).equals(cpu.getRegistry(position2))) == condition) {
			cpu.getInstPointer().add(STEP);
			logger.info("Skipped next Instruction");
		}
		cpu.getInstPointer().add(STEP);
	}
}
