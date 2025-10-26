package core;

import java.util.Map;

public final class GameData {
	private final Map<Integer, String[]> monsters, superuniques, levels;

	public GameData(Map<Integer, String[]> monsters, Map<Integer, String[]> superuniques,
			Map<Integer, String[]> levels) {
		this.monsters = monsters;
		this.superuniques = superuniques;
		this.levels = levels;
	}

	public Map<Integer, String[]> getMonsters() {
		return monsters;
	}

	public Map<Integer, String[]> getSuperuniques() {
		return superuniques;
	}

	public Map<Integer, String[]> getLevels() {
		return levels;
	}

}
