package ar.edu.itba.poo.bytemii.emulator.instructions.timers;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.PositionInstruction;

public class SetDelayToReg extends PositionInstruction {
	@Override
	public void execute( CPU cpu ) {
		if(logger.isDebugEnabled()) {
			logger.debug("Load V{}{{}} <- DT{{}}", position, cpu.getRegistry(position).get(), cpu.getDelayTimer().get());
		}
		cpu.getRegistry(position).set(cpu.getDelayTimer());
		logger.info("V{} set to: {}", position, cpu.getRegistry(position).get());
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		if(opCode.getNibble(0) == 0xF && opCode.getByte(1) == 0x07) {
			setValues(opCode);
			return true;
		}
		return false;
	}
}