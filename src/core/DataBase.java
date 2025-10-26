package core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
	private final GameData originalData;
	private final Map<String, Integer> monstersLookup, superuniquesLookup;
	private static DataBase INSTANCE;

	private DataBase() {
		try {
			this.originalData = TxtFileIO.loadAllOriginals();
		} catch (IOException e) {
			throw new IllegalStateException("Original files could not be loaded!", e);
		}

		this.monstersLookup = this.createLookup(originalData.getMonsters());
		this.superuniquesLookup = this.createLookup(originalData.getSuperuniques());
	}

	public static DataBase getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataBase();
		}
		return INSTANCE;
	}

	private Map<String, Integer> createLookup(Map<Integer, String[]> map) {
		Map<String, Integer> ret = new HashMap<String, Integer>();

		for (Map.Entry<Integer, String[]> line : map.entrySet()) {
			ret.put(line.getValue()[0], line.getKey());
		}

		return ret;
	}

	public String[] getSuperUniqueLineFromName(String name) {
		Integer index = superuniquesLookup.get(name);

		if (index == null) {
			return null;
		}

		String[] originalArray = originalData.getSuperuniques().get(index);
		if (originalArray == null) {
			return null;
		}

		return originalArray.clone();
	}

	public String[] getMonsterLineFromName(String name) {
		Integer index = monstersLookup.get(name);

		if (index == null) {
			return null;
		}

		String[] originalArray = originalData.getMonsters().get(index);
		if (originalArray == null) {
			return null;
		}

		return originalArray.clone();
	}

	public String[] getLevelLine(int index) {

		String[] originalArray = originalData.getLevels().get(index);
		if (originalArray == null) {
			return null;
		}

		return originalArray.clone();
	}

	public boolean monsterIsBoss(String name) {
		String[] monsterLines = originalData.getMonsters().get(monstersLookup.get(name));
		return !monsterLines[87].isEmpty();
	}

	public String getMonsterFieldFromName(String name, int field) {
		return originalData.getMonsters().get(monstersLookup.get(name))[field];
	}

	public String getMonsterNameFromLevel(int level, int index) {
		return originalData.getLevels().get(level)[index];
	}

	public String getSuperuniqueBaseMonster(String name) {
		Integer index = superuniquesLookup.get(name);
		if (index == null) {
			return null;
		}
		return originalData.getSuperuniques().get(index)[2];
	}

	public Map<Integer, String[]> getSuperuniques() {
		return originalData.getSuperuniques();
	}

	public Map<Integer, String[]> getMonsters() {
		return originalData.getMonsters();
	}

	public Map<Integer, String[]> getLevels() {
		return originalData.getLevels();
	}
}
