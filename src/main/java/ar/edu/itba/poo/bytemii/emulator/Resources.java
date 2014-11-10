package ar.edu.itba.poo.bytemii.emulator;

import ar.edu.itba.poo.bytemii.emulator.instructions.Instruction;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.Jump;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.JumpWithOffset;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips.SkipRegEqualsData;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips.SkipRegEqualsReg;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips.SkipRegNotEqualsData;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.skips.SkipRegNotEqualsReg;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.subroutines.CallSubroutine;
import ar.edu.itba.poo.bytemii.emulator.instructions.controlflow.subroutines.RetSubroutine;
import ar.edu.itba.poo.bytemii.emulator.instructions.display.ClearDisplay;
import ar.edu.itba.poo.bytemii.emulator.instructions.display.DrawSprite;
import ar.edu.itba.poo.bytemii.emulator.instructions.keyboard.SkipIfKeyNotPressed;
import ar.edu.itba.poo.bytemii.emulator.instructions.keyboard.SkipIfKeyPressed;
import ar.edu.itba.poo.bytemii.emulator.instructions.keyboard.WaitForKey;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.StoreData;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.StoreRegisterData;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.bitwise.*;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.math.*;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.memory.SetRAMFromRegistry;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.memory.SetRegistryFromRAM;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.registerI.AddRegisterToI;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.registerI.StoreDataInI;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.registerI.StoreRegisterBCDToI;
import ar.edu.itba.poo.bytemii.emulator.instructions.registers.registerI.StoreSpriteInI;
import ar.edu.itba.poo.bytemii.emulator.instructions.timers.SetDelayToReg;
import ar.edu.itba.poo.bytemii.emulator.instructions.timers.SetRegToDelay;
import ar.edu.itba.poo.bytemii.emulator.instructions.timers.SetRegToSound;

import java.util.ArrayList;
import java.util.List;

public final class Resources {
	//Singleton
	private static Resources instance = null;
	public static Resources getInstance() {
		if(instance == null)
			instance = new Resources();
		return instance;
	}

	//MEMORY
	public static final int RAM_SIZE = 0x1000;
	public static final int ROM_POSITION = 0x200;

	//KEYBOARD
	public static final int KEYBOARD_SIZE = 16;
	public static final int KEY_PRESSED = 1;
	public static final int KEY_RELEASED = 0;
	private static final int[][] keyboardMap = {
			{0x1, 0x2, 0x3, 0xC},
			{0x4, 0x5, 0x6, 0xD},
			{0x7, 0x8, 0x9, 0xE},
			{0xA, 0x0, 0xB, 0xF}
	};
	public static int[][] getKeyboardMap() {
		return keyboardMap;
	}

	//fontSet
	public static final int FONTSET_POSITION = 0x50;
	private static final int[] fontSet = {
			0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
			0x20, 0x60, 0x20, 0x20, 0x70, // 1
			0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
			0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
			0x90, 0x90, 0xF0, 0x10, 0x10, // 4
			0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
			0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
			0xF0, 0x10, 0x20, 0x40, 0x40, // 7
			0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
			0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
			0xF0, 0x90, 0xF0, 0x90, 0x90, // A
			0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
			0xF0, 0x80, 0x80, 0x80, 0xF0, // C
			0xE0, 0x90, 0x90, 0x90, 0xE0, // D
			0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
			0xF0, 0x80, 0xF0, 0x80, 0x80  // F
	};
	public static int[] getFontSet() {
		return fontSet;
	}

	//INSTRUCTIONS
	private List<Instruction> instructions;
	public List<Instruction> getInstructions() {
		if(instructions != null)
			return instructions;

		instructions = new ArrayList<>();

		// Control Flow
		// General
		instructions.add(new Jump());
		instructions.add(new JumpWithOffset());
		// Skips
		instructions.add(new SkipRegEqualsData());
		instructions.add(new SkipRegEqualsReg());
		instructions.add(new SkipRegNotEqualsData());
		instructions.add(new SkipRegNotEqualsReg());
		// Subroutiones
		instructions.add(new CallSubroutine());
		instructions.add(new RetSubroutine());
		// ^^ Control Flow ^^

		// display
		instructions.add(new DrawSprite());
		instructions.add(new ClearDisplay());
		// ^^ display ^^

		// Keyboard
		instructions.add(new SkipIfKeyPressed());
		instructions.add(new SkipIfKeyNotPressed());
		instructions.add(new WaitForKey());
		// ^^ Keyboard ^^

		// Registers
		// General
		instructions.add(new StoreData());
		instructions.add(new StoreRegisterData());
		// Bitwise
		instructions.add(new AndRegister());
		instructions.add(new OrRegister());
		instructions.add(new XorRegister());
		instructions.add(new RightShiftRegister());
		instructions.add(new LeftShiftRegister());
		instructions.add(new RandomData());
		// Math
		instructions.add(new AddData());
		instructions.add(new AddRegisterData());
		instructions.add(new SetSubRegisterData());
		instructions.add(new SubRegisterData());
		// Register I
		instructions.add(new AddRegisterToI());
		instructions.add(new StoreDataInI());
		instructions.add(new StoreRegisterBCDToI());
		instructions.add(new SetRegistryFromRAM());
		instructions.add(new SetRAMFromRegistry());
		instructions.add(new StoreSpriteInI());
		// ^^ Registers ^^

		// Timers
		instructions.add(new SetDelayToReg());
		instructions.add(new SetRegToDelay());
		instructions.add(new SetRegToSound());
		// ^^ Timers ^^

		return instructions;
	}
}
