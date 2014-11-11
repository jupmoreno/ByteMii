package ar.edu.itba.poo.bytemii.emulator.instructions.timers;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

/**
 * Fx18 - LD ST, Vx
 * Set sound timer = Vx.
 * ST is set equal to the value of Vx.
 */

public class SetRegToSound extends PositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		if(logger.isDebugEnabled()) {
			logger.debug("Load ST{{}} <- V{}{{}}", cpu.getSoundTimer().get(), position, cpu.getRegistry(position).get());
		}
		cpu.getSoundTimer().set(cpu.getRegistry(position));
		logger.info("SoundTimer set to: {}", cpu.getDelayTimer());
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xF && opCode.getByte(1) == 0x18) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}