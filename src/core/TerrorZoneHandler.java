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
		if (this.levels == null && this.superuniques == null && this.monsters == null) {
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

	public void applyTerrorZone(TerrorZone selection, int charlevel) {
		if (selection == TerrorZone.TRAVINCAL || selection == TerrorZone.DURANCE_OF_HATE) {
			this.adjustCouncilTC();
		}
		this.desecrateMonsters(selection.getMonsters(), charlevel);
		this.adjustAreaLevels(selection.getLevelLines(), charlevel);
		this.handleSpecialMonsters(selection.getSpecialCases(), selection.getMonsters(), charlevel);
	}

	private void handleSpecialMonsters(SpecialMonster[] specialMonsters, String[] areaMonsters, int charlevel) {
		for (SpecialMonster specialMonster : specialMonsters) {
			if (this.superuniquesLookup.containsKey(specialMonster.toString())) {
				int superuniqueIndex = this.superuniquesLookup.get(specialMonster.toString());
				String[] superuniqueLines = this.superuniques.get(superuniqueIndex);
				if (this.monsterIsBoss(superuniqueLines[2])) {
					superuniqueLines[1] += "Terror";
					if (!this.isStringInArray(superuniqueLines[2], areaMonsters)) {
						this.desecrateMonster(superuniqueLines[2], charlevel);
					}
					this.setBossTC(specialMonster, superuniqueLines[2], charlevel);
					continue;
				}
				switch (specialMonster) {
				case ISMAIL:
				case BREMM:
					this.adjustCouncilTC();
				default:
					if (!this.isStringInArray(superuniqueLines[2], areaMonsters)) {
						this.desecrateMonster(superuniqueLines[2], charlevel);
					}
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
				this.desecrateMonster(specialMonster.toString(), charlevel);
				this.setBossTC(specialMonster, specialMonster.toString(), charlevel);
			}
		}
	}

	private void setBossTC(SpecialMonster boss, String monsterID, int charlevel) {
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
			monsterLines[236] += this.getBossTCSuffix(boss, 0, charlevel + 5);
			monsterLines[237] = monsterLines[236];
			monsterLines[238] = monsterLines[236];
			monsterLines[239] = monsterLines[236];
			monsterLines[240] += this.getBossTCSuffix(boss, 1, charlevel + 5);
			monsterLines[241] = monsterLines[240];
			monsterLines[242] = monsterLines[240];
			monsterLines[243] = monsterLines[240];
			monsterLines[244] += this.getBossTCSuffix(boss, 2, charlevel + 5);
			monsterLines[245] = monsterLines[244];
			monsterLines[246] = monsterLines[244];
			monsterLines[247] = monsterLines[244];
			break;
		case GRISWOLD:
		case NIHLATHAK:
		case RADAMENT:
			String[] superuniqueLines = superuniques.get(superuniquesLookup.get(boss.toString()));
			superuniqueLines[17] += this.getBossTCSuffix(boss, 0, charlevel + 5);
			superuniqueLines[18] += this.getBossTCSuffix(boss, 1, charlevel + 5);
			superuniqueLines[19] += this.getBossTCSuffix(boss, 2, charlevel + 5);
			break;
		default:
			System.err.println("default case for setBossTC should never happen! Boss: " + boss.toString());
		}
	}

	private String getBossTCSuffix(SpecialMonster boss, int difficulty, int bosslevel) {
		switch (boss) {
		case SUMMONER:
			return this.getSummonerTCSuffix(difficulty, bosslevel);
		case RADAMENT:
			return this.getRadamentTCSuffix(difficulty, bosslevel);
		case GRISWOLD:
			return this.getGriswoldTCSuffix(difficulty, bosslevel);
		case BLOODRAVEN:
			return this.getBloodRavenTCSuffix(difficulty, bosslevel);
		case ANDARIEL:
			return this.getAndarielTCSuffix(difficulty, bosslevel);
		case DURIEL:
			return this.getDurielTCSuffix(difficulty, bosslevel);
		case MEPHISTO:
			return this.getMephistoTCSuffix(difficulty, bosslevel);
		case DIABLO:
			return this.getDiabloTCSuffix(difficulty, bosslevel);
		case IZUAL:
			return this.getIzualTCSuffic(difficulty, bosslevel);
		default:
			return " Desecrated";
		}
	}

	private boolean monsterIsBoss(String monsterID) {
		String[] monsterLines = monsters.get(monstersLookup.get(monsterID));
		return !monsterLines[87].isEmpty();
	}

	private String getSummonerTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 32) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 63) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 84) {
				return " Desecrated A";
			}
			if (bosslevel < 87) {
				return " Desecrated B";
			}
			if (bosslevel < 90) {
				return " Desecrated C";
			}
			if (bosslevel < 93) {
				return " Desecrated D";
			}
			if (bosslevel >= 96) {
				return " Desecrated F";
			}
			return " Desecrated E";
		}
		return null;
	}

	private String getRadamentTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 33) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 62) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getGriswoldTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 29) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 57) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getIzualTCSuffic(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 38) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 66) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getDiabloTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel >= 48) {
				return " Desecrated B";
			}
			return " Desecrated A";
		case 1:
			if (bosslevel < 67) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel >= 96) {
				return " Desecrated B";
			}
			return " Desecrated A";
		}
		return null;
	}

	private String getMephistoTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 38) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 66) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getDurielTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 36) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 63) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getAndarielTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 32) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 57) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 78) {
				return " Desecrated A";
			}
			if (bosslevel < 81) {
				return " Desecrated B";
			}
			if (bosslevel < 84) {
				return " Desecrated C";
			}
			if (bosslevel < 87) {
				return " Desecrated D";
			}
			if (bosslevel < 90) {
				return " Desecrated E";
			}
			if (bosslevel < 93) {
				return " Desecrated F";
			}
			if (bosslevel >= 96) {
				return " Desecrated H";
			}
			return " Desecrated G";
		}
		return null;
	}

	private String getBloodRavenTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 29) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 57) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
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
		monsters.put(firstID, first);
		monsters.put(secondID, second);
		monsters.put(lastID, last);
	}

	public boolean isStringInArray(String str, String[] array) {
		for (String element : array) {
			if (element.equals(str)) {
				return true;
			}
		}
		return false;
	}

	private void adjustAreaLevels(int[] levelLines, int charlevel) {
		for (int area : levelLines) {
			// max alvls are 45, 71 and 96
			String[] levelLine = this.levels.get(area);
			levelLine[59] = Integer.toString(Math.max(Math.min(45, charlevel + 2), Integer.parseInt(levelLine[59])));
			levelLine[60] = Integer.toString(Math.max(Math.min(71, charlevel + 2), Integer.parseInt(levelLine[60])));
			levelLine[61] = Integer.toString(Math.max(Math.min(96, charlevel + 2), Integer.parseInt(levelLine[61])));
			this.levels.put(area, levelLine);
		}
	}

	private void desecrateMonsters(String[] monsterStrings, int charlevel) {
		for (String monster : monsterStrings) {
			this.desecrateMonster(monster, charlevel);
		}
	}

	private void desecrateMonster(String monster, int charlevel) {
		int monsterID = this.monstersLookup.get(monster);
		String[] monsterLine = this.monsters.get(monsterID);
		// change name
		monsterLine[5] += "Terror";
		// change level for normal or, if boss, for all
		if (!monsterLine[87].isEmpty()) {
			monsterLine[31] = Integer
					.toString(Math.max(Math.min(48, charlevel + 5), Integer.parseInt(monsterLine[31])));
			monsterLine[32] = Integer
					.toString(Math.max(Math.min(74, charlevel + 5), Integer.parseInt(monsterLine[32])));
			monsterLine[33] = Integer
					.toString(Math.max(Math.min(99, charlevel + 5), Integer.parseInt(monsterLine[33])));
		} else {
			monsterLine[31] = Integer
					.toString(Math.max(Math.min(45, charlevel + 2), Integer.parseInt(monsterLine[31])));
		}
		// adjust xp
		monsterLine[158] = Integer.toString((int) (Integer.parseInt(monsterLine[158]) * 1.25));
		monsterLine[171] = Integer.toString((int) (Integer.parseInt(monsterLine[171]) * 1.25));
		monsterLine[184] = Integer.toString((int) (Integer.parseInt(monsterLine[184]) * 1.25));
		// TCs are handled in the special cases
		this.monsters.put(monsterID, monsterLine);
	}

	public void applyChaos(int charlevel) {
		Set<String> monsterStrings = new HashSet<String>();
		Set<SpecialMonster> specialEntries = new HashSet<SpecialMonster>();
		Set<Integer> levelsSet = new HashSet<Integer>();
		for (TerrorZone tz : TerrorZone.values()) {
			if (tz == TerrorZone.RANDOM) {
				continue;
			}
			monsterStrings.addAll(Arrays.asList(tz.getMonsters()));
			specialEntries.addAll(Arrays.asList(tz.getSpecialCases()));
			for (int level : tz.getLevelLines()) {
				levelsSet.add(level);
			}
		}
		String[] monsterArray = new String[monsterStrings.size()];
		int i = 0;
		for (String s : monsterStrings) {
			monsterArray[i++] = s;
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

		this.desecrateMonsters(monsterArray, charlevel);
		this.handleSpecialMonsters(specialArray, monsterArray, charlevel);
		this.adjustAreaLevels(levelsArray, charlevel);
	}
}
