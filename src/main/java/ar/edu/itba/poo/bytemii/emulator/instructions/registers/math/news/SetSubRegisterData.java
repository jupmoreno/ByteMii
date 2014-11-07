//package ar.edu.itba.poo.ByteMii.emulator.instructions.registers.math.news;
//
//import ar.edu.itba.poo.ByteMii.emulator.hardware.cpu.CPU;
//import ar.edu.itba.poo.ByteMii.emulator.OpCode;
//import ar.edu.itba.poo.ByteMii.emulator.instructions.DoublePositionInstruction;
//
//public class SetSubRegisterData extends DoublePositionInstruction { // TODO: CAMBIARLE EL NOMBRE
//	@Override
//	public void execute( CPU cpu ) {
//
//		cpu.getInstPointer().add(STEP);
//	}
//
//	@Override
//	public boolean validate( OpCode opCode ) {
//		if(opCode.getNibble(0) == 0x8 && opCode.getNibble(3) == 0x7) {
//			setValues(opCode);
//			return true;
//		}
//		return false;
//	}
//}
