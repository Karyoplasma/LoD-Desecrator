package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TerrorZoneHandler {

	private static TerrorZoneHandler INSTANCE;
	private Map<Integer, String[]> monsters, superuniques, levels;
	private Map<String, Integer> monstersLookup, superuniquesLookup;

	private TerrorZoneHandler() {
		this.monsters = this.readMonstersFromOriginal();
		this.superuniques = this.readSuperUniquesFromOriginal();
		this.levels = this.readLevelsFromOriginal();
	}

	public static TerrorZoneHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TerrorZoneHandler();
		}
		return INSTANCE;
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
		String[] monsterArray = new String[monsterStrings.size()];
		int i = 0;
		for (String s : monsterStrings) {
			monsterArray[i++] = s;
		}
		String[] presetsArray = new String[presetsSet.size()];
		i = 0;
		for (String preset : presetsSet) {
			presetsArray[i++] = preset;
		}
		SpecialMonster[] specialArray = new SpecialMonster[specialEntries.size()];
		i = 0;
		for (SpecialMonster s : specialEntries) {
			specialArray[i++] = s;
		}
		int[] levelsArray = new int[levelsSet.size()];
		i = 0;
		for (int s : levelsSet) {
			levelsArray[i++] = s;
		}
		this.desecrateMonsters(monsterArray, presetsArray, charlevel);
		this.handleSpecialMonsters(specialArray, charlevel);
		this.adjustAreaLevels(levelsArray, charlevel);
	}

	public void applyTerrorZone(TerrorZone selection, int charlevel) {
		if (selection == TerrorZone.TRAVINCAL || selection == TerrorZone.DURANCE_OF_HATE) {
			this.adjustCouncilTC();
		}
		String[] monsterSpawns = this.gatherMonsters(selection);
		this.desecrateMonsters(monsterSpawns, selection.getPresetMonsters(), charlevel);
		this.adjustAreaLevels(selection.getLevelLines(), charlevel);
		this.handleSpecialMonsters(selection.getSpecialCases(), charlevel);
	}
	
	private void handleSpecialMonsters(SpecialMonster[] specialMonsters, int charlevel) {
		for (SpecialMonster specialMonster : specialMonsters) {
			if (this.superuniquesLookup.containsKey(specialMonster.toString())) {
				int superuniqueIndex = this.superuniquesLookup.get(specialMonster.toString());
				String[] superuniqueLines = this.superuniques.get(superuniqueIndex);
				if (this.monsterIsBoss(superuniqueLines[2])) {
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
			String[] monsterLines = monsters.get(monstersLookup.get(monsterID));		
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
			String[] superuniqueLines = superuniques.get(superuniquesLookup.get(boss.toString()));
			String[] superuniquesTCSuffixes = tcCalc.getBossTCSuffixes(boss); 
			superuniqueLines[17] += superuniquesTCSuffixes[0];
			superuniqueLines[18] += superuniquesTCSuffixes[1];
			superuniqueLines[19] += superuniquesTCSuffixes[2];
			break;
		default:
			System.err.println("default case for setBossTC should never happen! Boss: " + boss.toString());
		}
	}

	private boolean monsterIsBoss(String monsterID) {
		String[] monsterLines = monsters.get(monstersLookup.get(monsterID));
		return !monsterLines[87].isEmpty();
	}

	private void adjustCouncilTC() {
		String suffix = " Desecrated A";
		int firstID = monstersLookup.get("councilmember1");
		String[] first = monsters.get(firstID);
		int secondID = monstersLookup.get("councilmember2");
		String[] second = monsters.get(secondID);
		int lastID = monstersLookup.get("councilmember3");
		String[] last = monsters.get(lastID);
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
			String[] levelLine = this.levels.get(area);
			// don't adjust monlvl1ex as this will screw up presets, the other columns are fine
			levelLine[60] = Integer.toString(Math.max(Math.min(71, charlevel + 2), Integer.parseInt(levelLine[60])));
			levelLine[61] = Integer.toString(Math.max(Math.min(96, charlevel + 2), Integer.parseInt(levelLine[61])));
		}
	}

	private void desecrateMonsters(String[] monsterStrings, String[] presets, int charlevel) {
		for (String monster : monsterStrings) {
			this.desecrateMonster(monster, presets, charlevel);
		}
	}

	private void desecrateMonster(String monster, String[] presets, int charlevel) {
		int monsterID = this.monstersLookup.get(monster);
		String[] monsterLine = this.monsters.get(monsterID);
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
		List<String> presets = Arrays.asList(tz.getPresetMonsters()); 
		Set<String> monstersSet = new HashSet<String>(presets);
		
		for (int level : tz.getLevelLines()) {
			for (int i = 74; i < 105; i++) {
				if (i == 84) {
					continue;
				}
				if (!levels.get(level)[i].isEmpty()) {
					monstersSet.add(levels.get(level)[i]);
				}
			}
		}
		for (SpecialMonster special : tz.getSpecialCases()) {
			if (superuniquesLookup.containsKey(special.toString())) {
				String superuniqueID = superuniques.get(superuniquesLookup.get(special.toString()))[2];
				monstersSet.add(superuniqueID);
			} else {
				monstersSet.add(special.toString());
			}
		}
		Set<String> minions = new HashSet<String>();
		
		do {
			minions.clear();
			for (String monster : monstersSet) {
				String spawn = monsters.get(monstersLookup.get(monster))[15];
				String minion1 = monsters.get(monstersLookup.get(monster))[19];
				String minion2 = monsters.get(monstersLookup.get(monster))[20];
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
		}
		while (!minions.isEmpty());
		monstersSet.remove("baaltaunt");
		String[] ret = new String[monstersSet.size()];
		int index = 0;
		for (String monster : monstersSet) {
			ret[index++] = monster;
		}
		return ret;
	}
	
	
	// I/O stuff from here on
	
	private Map<Integer, String[]> readLevelsFromOriginal() {
		Map<Integer, String[]> levels = new HashMap<Integer, String[]>();
		try (BufferedReader reader = new BufferedReader(new FileReader("OriginalFiles\\Levels.txt"))) {
			String in = reader.readLine();
			int lineNumber = 1;
			levels.put(0, in.split("\\t"));
			while ((in = reader.readLine()) != null) {
				String[] line = in.split("\\t");
				levels.put(lineNumber, line);
				lineNumber++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return levels;
	}

	private Map<Integer, String[]> readSuperUniquesFromOriginal() {
		Map<Integer, String[]> superuniques = new HashMap<Integer, String[]>();
		this.superuniquesLookup = new HashMap<String, Integer>();
		try (BufferedReader reader = new BufferedReader(new FileReader("OriginalFiles\\SuperUniques.txt"))) {
			String in = reader.readLine();
			int lineNumber = 1;
			superuniques.put(0, in.split("\\t"));
			while ((in = reader.readLine()) != null) {
				String[] line = in.split("\\t");
				superuniques.put(lineNumber, line);
				superuniquesLookup.put(line[0], lineNumber);
				lineNumber++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return superuniques;
	}

	private Map<Integer, String[]> readMonstersFromOriginal() {
		Map<Integer, String[]> monsters = new HashMap<Integer, String[]>();
		this.monstersLookup = new HashMap<String, Integer>();
		try (BufferedReader reader = new BufferedReader(new FileReader("OriginalFiles\\monstats.txt"))) {
			String in = reader.readLine();
			int lineNumber = 1;
			monsters.put(0, in.split("\\t"));
			while ((in = reader.readLine()) != null) {
				String[] line = in.split("\\t");
				monsters.put(lineNumber, line);
				monstersLookup.put(line[0], lineNumber);
				lineNumber++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return monsters;
	}

	public int rereadOriginals() {
		this.levels = this.readLevelsFromOriginal();
		this.superuniques = this.readSuperUniquesFromOriginal();
		this.monsters = readMonstersFromOriginal();
		if (this.levels == null || this.superuniques == null || this.monsters == null) {
			return 1;
		}
		return 0;
	}

	public int writeChanges() {
		int superuniqueStatus = this.writeSuperUniques();
		int monstatsStatus = this.writeMonstats();
		int levelsStatus = this.writeLevels();
		int rereadStatus = this.rereadOriginals();
		return Math.max(Math.max(superuniqueStatus, monstatsStatus), Math.max(levelsStatus, rereadStatus));
	}

	private int writeSuperUniques() {
		StringBuilder builder = new StringBuilder();
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("D:\\Games\\Diablo II\\Data\\Global\\Excel\\SuperUniques.txt"))) {
			for (int i = 0; i < this.superuniques.size(); i++) {
				builder.setLength(0);
				String[] temp = this.superuniques.get(i);
				for (String s : temp) {
					builder.append(s).append("\t");
				}
				builder.setLength(builder.length() - 1);
				builder.append(System.lineSeparator());
				writer.write(builder.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	private int writeMonstats() {
		StringBuilder builder = new StringBuilder();
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("D:\\Games\\Diablo II\\Data\\Global\\Excel\\monstats.txt"))) {
			for (int i = 0; i < this.monsters.size(); i++) {
				builder.setLength(0);
				String[] temp = this.monsters.get(i);
				for (String s : temp) {
					builder.append(s).append("\t");
				}
				builder.setLength(builder.length() - 1);
				builder.append(System.lineSeparator());
				writer.write(builder.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	private int writeLevels() {
		StringBuilder builder = new StringBuilder();
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("D:\\Games\\Diablo II\\Data\\Global\\Excel\\Levels.txt"))) {
			for (int i = 0; i < this.levels.size(); i++) {
				builder.setLength(0);
				String[] temp = this.levels.get(i);
				for (String s : temp) {
					builder.append(s).append("\t");
				}
				builder.setLength(builder.length() - 1);
				builder.append(System.lineSeparator());
				writer.write(builder.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	public static int resetTerrorZones() {
		Path superuniquesFile = Paths.get("OriginalFiles\\SuperUniques.txt");
		Path superuniquesPath = Paths.get("D:\\Games\\Diablo II\\Data\\Global\\Excel\\SuperUniques.txt");
		Path monstatsFile = Paths.get("OriginalFiles\\monstats.txt");
		Path monstatsPath = Paths.get("D:\\Games\\Diablo II\\Data\\Global\\Excel\\monstats.txt");
		Path levelsFile = Paths.get("OriginalFiles\\Levels.txt");
		Path levelsPath = Paths.get("D:\\Games\\Diablo II\\Data\\Global\\Excel\\Levels.txt");
		try {
			Files.copy(superuniquesFile, superuniquesPath, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(monstatsFile, monstatsPath, StandardCopyOption.REPLACE_EXISTING);
			Files.copy(levelsFile, levelsPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred: " + e.getMessage());
			return 1;
		}
		return 0;
	}
}
