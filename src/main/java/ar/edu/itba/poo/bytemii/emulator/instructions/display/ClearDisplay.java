package ar.edu.itba.poo.bytemii.emulator.instructions.display;

import ar.edu.itba.poo.bytemii.emulator.hardware.cpu.CPU;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;
import ar.edu.itba.poo.bytemii.emulator.instructions.OpCode;
import ar.edu.itba.poo.bytemii.emulator.instructions.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClearDisplay implements Instruction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void execute( CPU cpu ) {
		if(logger.isDebugEnabled()) {
			logger.debug("Clear Display");
		}
		cpu.getMemoryMap().getMemory(MemoryType.DISPLAY).clear();
		cpu.setDisplayRedraw(true);
		logger.info("Display cleared; Set needRedraw to {}", cpu.getDisplayRedraw());
		cpu.getInstPointer().add(STEP);
	}

	@Override
	public boolean validate( OpCode opCode ) {
		return opCode.get() == 0x00E0;
	}
}
