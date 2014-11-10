package ar.edu.itba.poo.bytemii.emulator.hardware.cpu;

import ar.edu.itba.poo.bytemii.emulator.hardware.memory.Memory;
import ar.edu.itba.poo.bytemii.emulator.hardware.memory.MemoryType;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class MemoryMap {
	private Map<MemoryType, Memory> memoryMap;

	public MemoryMap() {
		memoryMap = new HashMap<>();
	}

	/**
	 * Returns the desired memory
	 * @param name
	 * @return Memory
	 */

	public Memory getMemory(MemoryType name) {
		return memoryMap.get(name);
	}

	/**
	 *
	 * @param name
	 * @param memory
	 * @return boolean
	 */

	public boolean addMemory( MemoryType name, Memory memory ) {
		return ! memoryMap.containsKey(name) && memoryMap.put(name, memory) == null;
	}
}
