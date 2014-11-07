package ar.edu.itba.poo.bytemii.emulator.hardware.cpu;

import ar.edu.itba.poo.bytemii.emulator.hardware.memory.Memory;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;

import java.util.HashMap;
import java.util.Map;

public class MemoryMap {
	private Map<MemoryType, Memory> memoryMap;

	public MemoryMap() {
		memoryMap = new HashMap<>();
	}

	public Memory getMemory(MemoryType name) {
		return memoryMap.get(name);
	}

	public boolean setMemory(MemoryType name, Memory memory) {
		return ! memoryMap.containsKey(name) && memoryMap.put(name, memory) == null;
	}
}
