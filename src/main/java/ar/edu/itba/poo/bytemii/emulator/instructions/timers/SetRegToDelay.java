package ar.edu.itba.poo.bytemii.emulator.instructions.timers;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

/**
 * Fx15 - LD DT, Vx
 * Set delay timer = Vx.
 * DT is set equal to the value of Vx.
 */

public class SetRegToDelay extends PositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		if(logger.isDebugEnabled()) {
			logger.debug("Load DT{{}} <- V{}{{}}", cpu.getDelayTimer().get(), position, cpu.getRegistry(position).get());
		}
		cpu.getDelayTimer().set(cpu.getRegistry(position));
		logger.info("DelayTimer set to: {}", cpu.getDelayTimer());
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xF && opCode.getByte(1) == 0x15) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}