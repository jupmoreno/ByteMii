//package ar.edu.itba.poo.ByteMii.emulator.instructions.registers.math.news;
//
//import ar.edu.itba.poo.ByteMii.emulator.hardware.cpu.CPU;
//import ar.edu.itba.poo.ByteMii.emulator.OpCode;
//import ar.edu.itba.poo.ByteMii.emulator.instructions.DoublePositionInstruction;
//
//public class SubRegisterData extends DoublePositionInstruction {
//	@Override
//	public void execute( CPU cpu ) {
//		cpu.getRegistry(position1).sub(cpu.getRegistry(position2));
//		cpu.getRegistry(0xF).set(cpu.getRegistry(position1).get() < 0 ? 1 : 0);
//		cpu.getInstPointer().add(STEP);
//	}
//
//	@Override
//	public boolean validate( OpCode opCode ) {
//		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x5) {
//			setValues(opCode);
//			return true;
//		}
//		return false;
//	}
//}
