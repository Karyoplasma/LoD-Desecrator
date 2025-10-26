package core;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TerrorZoneHandler {

	private final DataBase dataBase;
	private final Map<String, String[]> changedMonsters, changedSuperuniques, changedLevels;

	public TerrorZoneHandler() {
		this.dataBase = DataBase.getInstance();
		this.changedMonsters = new HashMap<String, String[]>();
		this.changedSuperuniques = new HashMap<String, String[]>();
		this.changedLevels = new HashMap<String, String[]>();
	}

	public void applyChaos(int charlevel) {
		Set<String> monsterStrings = new HashSet<String>();
		Set<SpecialMonster> specialEntries = new HashSet<SpecialMonster>();
		Set<Integer> levelsSet = new HashSet<Integer>();
		Set<String> presetsSet = new HashSet<String>();
		for (TerrorZone tz : TerrorZone.values()) {
			if (tz == TerrorZone.RANDOM) {
				continue;
			}
			monsterStrings.addAll(Arrays.asList(this.gatherMonsters(tz)));
			specialEntries.addAll(Arrays.asList(tz.getSpecialCases()));
			presetsSet.addAll(Arrays.asList(tz.getPresetMonsters()));
			for (int level : tz.getLevelLines()) {
				levelsSet.add(level);
			}
		}
		String[] monsterArray = monsterStrings.toArray(new String[0]);
		String[] presetsArray = presetsSet.toArray(new String[0]);
		SpecialMonster[] specialArray = specialEntries.toArray(new SpecialMonster[0]);
		int[] levelsArray = levelsSet.stream().mapToInt(Integer::intValue).toArray();
		this.desecrateMonsters(monsterArray, presetsArray, charlevel);
		this.handleSpecialMonsters(specialArray, charlevel);
		this.adjustAreaLevels(levelsArray, charlevel);
	}

	public void applyTerrorZone(TerrorZone selection, int charlevel) {
		String[] monsterSpawns = this.gatherMonsters(selection);
		this.desecrateMonsters(monsterSpawns, selection.getPresetMonsters(), charlevel);
		this.adjustAreaLevels(selection.getLevelLines(), charlevel);
		this.handleSpecialMonsters(selection.getSpecialCases(), charlevel);
		if (selection == TerrorZone.TRAVINCAL || selection == TerrorZone.DURANCE_OF_HATE) {
			this.adjustCouncilTC();
		}
	}

	private void handleSpecialMonsters(SpecialMonster[] specialMonsters, int charlevel) {
		for (SpecialMonster specialMonster : specialMonsters) {
			String[] superuniqueLines = dataBase.getSuperUniqueLineFromName(specialMonster.toString());
			if (superuniqueLines != null) {
				changedSuperuniques.put(superuniqueLines[0], superuniqueLines);
				if (dataBase.monsterIsBoss(superuniqueLines[2])) {
					superuniqueLines[1] += "Terror";
					this.setBossTC(specialMonster, superuniqueLines[2], charlevel);
					continue;
				}

				switch (specialMonster) {
				case ISMAIL:
				case BREMM:
					this.adjustCouncilTC();
				default:
					superuniqueLines[1] += "Terror";
					if (specialMonster.hasSpecialTC()) {
						String tcSuffix = " Desecrated";
						if (specialMonster != SpecialMonster.NIHLATHAK) {
							tcSuffix += " A";
						}
						superuniqueLines[17] += tcSuffix;
						superuniqueLines[18] += tcSuffix;
						superuniqueLines[19] += tcSuffix;
					}
				}
			} else {
				this.setBossTC(specialMonster, specialMonster.toString(), charlevel);
			}
		}
	}

	private void setBossTC(SpecialMonster boss, String monsterID, int charlevel) {
		TCCalculator tcCalc = new TCCalculator(charlevel);
		switch (boss) {
		case ANDARIEL:
		case BAAL:
		case BLOODRAVEN:
		case DIABLO:
		case DURIEL:
		case IZUAL:
		case MEPHISTO:
		case SUMMONER:
			String[] monsterLines = changedMonsters.get(monsterID);
			String[] monstatsTCSuffixes = tcCalc.getBossTCSuffixes(boss);
			for (int i = 0; i < 3; i++) {
				monsterLines[236 + (4 * i)] += monstatsTCSuffixes[i];
				monsterLines[237 + (4 * i)] = monsterLines[236 + (4 * i)];
				monsterLines[238 + (4 * i)] = monsterLines[236 + (4 * i)];
				monsterLines[239 + (4 * i)] = monsterLines[236 + (4 * i)];
			}
			break;
		case GRISWOLD:
		case NIHLATHAK:
		case RADAMENT:
			String[] superuniqueLines = changedSuperuniques.get(boss.toString());
			String[] superuniquesTCSuffixes = tcCalc.getBossTCSuffixes(boss);
			superuniqueLines[17] += superuniquesTCSuffixes[0];
			superuniqueLines[18] += superuniquesTCSuffixes[1];
			superuniqueLines[19] += superuniquesTCSuffixes[2];
			break;
		default:
			System.err.println("default case for setBossTC should never happen! Boss: " + boss.toString());
		}
	}

	private void adjustCouncilTC() {
		String suffix = " Desecrated A";
		String[] first = changedMonsters.get("councilmember1");
		String[] second = changedMonsters.get("councilmember2");
		String[] last = changedMonsters.get("councilmember3");
		for (int index = 236; index < 247; index++) {
			if (!first[index].contains("Desecrated")) {
				first[index] += suffix;
			}
			if (!second[index].contains("Desecrated")) {
				second[index] += suffix;
			}
			if (!last[index].contains("Desecrated")) {
				last[index] += suffix;
			}
			if (index == 238 || index == 242) {
				index++;
			}
		}
	}

	private void adjustAreaLevels(int[] levelLines, int charlevel) {
		for (int area : levelLines) {
			// max alvls are 45, 71 and 96
			String[] levelLine = dataBase.getLevelLine(area);
			// don't adjust monlvl1ex as this will screw up presets, the other columns are
			// fine
			levelLine[60] = Integer.toString(Math.max(Math.min(71, charlevel + 2), Integer.parseInt(levelLine[60])));
			levelLine[61] = Integer.toString(Math.max(Math.min(96, charlevel + 2), Integer.parseInt(levelLine[61])));
			this.changedLevels.put(levelLine[0], levelLine);
		}
	}

	private void desecrateMonsters(String[] monsterStrings, String[] presets, int charlevel) {
		for (String monster : monsterStrings) {
			this.desecrateMonster(monster, presets, charlevel);
		}
	}

	private void desecrateMonster(String monster, String[] presets, int charlevel) {
		String[] monsterLine = dataBase.getMonsterLineFromName(monster);
		// change name
		if (monsterLine[5].endsWith("Terror")) {
			return;
		}
		monsterLine[5] += "Terror";
		// change level
		if (!monsterLine[87].isEmpty()) {
			monsterLine[31] = Integer
					.toString(Math.max(Math.min(48, charlevel + 5), Integer.parseInt(monsterLine[31])));
			monsterLine[32] = Integer
					.toString(Math.max(Math.min(74, charlevel + 5), Integer.parseInt(monsterLine[32])));
			monsterLine[33] = Integer
					.toString(Math.max(Math.min(99, charlevel + 5), Integer.parseInt(monsterLine[33])));
		} else {
			if (!this.isPreset(monster, presets)) {
				monsterLine[31] = Integer
						.toString(Math.max(Math.min(45, charlevel + 2), Integer.parseInt(monsterLine[31])));
			}
			monsterLine[32] = Integer
					.toString(Math.max(Math.min(71, charlevel + 2), Integer.parseInt(monsterLine[32])));
			monsterLine[33] = Integer
					.toString(Math.max(Math.min(96, charlevel + 2), Integer.parseInt(monsterLine[33])));
		}
		// adjust xp
		monsterLine[158] = Integer.toString((int) (Integer.parseInt(monsterLine[158]) * 1.25));
		monsterLine[171] = Integer.toString((int) (Integer.parseInt(monsterLine[171]) * 1.25));
		monsterLine[184] = Integer.toString((int) (Integer.parseInt(monsterLine[184]) * 1.25));

		changedMonsters.put(monsterLine[0], monsterLine);
	}

	private boolean isPreset(String monster, String[] presets) {
		for (String preset : presets) {
			if (monster.equals(preset)) {
				return true;
			}
		}
		return false;
	}

	private String[] gatherMonsters(TerrorZone tz) {
		Set<String> monstersSet = new HashSet<String>(Arrays.asList(tz.getPresetMonsters()));
		for (int level : tz.getLevelLines()) {
			for (int i = 74; i < 105; i++) {
				if (i == 84) {
					continue;
				}
				String mon = dataBase.getMonsterNameFromLevel(level, i);
				if (!mon.isEmpty()) {
					monstersSet.add(mon);
				}
			}
		}
		for (SpecialMonster special : tz.getSpecialCases()) {
			String superuniqueID = dataBase.getSuperuniqueBaseMonster(special.toString());
			if (superuniqueID != null) {
				monstersSet.add(superuniqueID);
			} else {
				monstersSet.add(special.toString());
			}
		}
		Set<String> minions = new HashSet<String>();

		do {
			minions.clear();
			for (String monster : monstersSet) {
				String spawn = dataBase.getMonsterFieldFromName(monster, 15);
				String minion1 = dataBase.getMonsterFieldFromName(monster, 19);
				String minion2 = dataBase.getMonsterFieldFromName(monster, 20);
				if (!spawn.isEmpty() && !monstersSet.contains(spawn)) {
					minions.add(spawn);
				}
				if (!minion1.isEmpty() && !monstersSet.contains(minion1)) {
					minions.add(minion1);
				}
				if (!minion2.isEmpty() && !monstersSet.contains(minion2)) {
					minions.add(minion2);
				}
			}
			monstersSet.addAll(minions);
		} while (!minions.isEmpty());
		monstersSet.remove("baaltaunt");
		
		return monstersSet.toArray(new String[0]);
	}

	private void resetChanges() {
		this.changedMonsters.clear();
		this.changedSuperuniques.clear();
		this.changedLevels.clear();
	}

	public int writeChanges(Path modPath) {
		int superuniqueStatus = TxtFileIO.writeTxtFile(modPath.resolve("SuperUniques.txt"), changedSuperuniques,
				dataBase.getSuperuniques());
		int monstatsStatus = TxtFileIO.writeTxtFile(modPath.resolve("monstats.txt"), changedMonsters,
				dataBase.getMonsters());
		int levelsStatus = TxtFileIO.writeTxtFile(modPath.resolve("Levels.txt"), changedLevels, dataBase.getLevels());
		this.resetChanges(); //i think this is not necessary because we make a new handler every time
		return Math.max(Math.max(superuniqueStatus, monstatsStatus), levelsStatus);
	}
}
