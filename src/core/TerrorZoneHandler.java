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

	private TerrorZoneHandler() throws IOException {
		this.monsters = this.readMonstersFromOriginal();
		this.superuniques = this.readSuperUniquesFromOriginal();
		this.levels = this.readLevelsFromOriginal();
	}

	public static TerrorZoneHandler getInstance() {
		if (INSTANCE == null) {
			try {
				INSTANCE = new TerrorZoneHandler();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return INSTANCE;
	}

	private Map<Integer, String[]> readLevelsFromOriginal() throws IOException {
		Map<Integer, String[]> levels = new HashMap<Integer, String[]>();
		BufferedReader reader = new BufferedReader(new FileReader("OriginalFiles\\Levels.txt"));
		String in = reader.readLine();
		int lineNumber = 1;
		levels.put(0, in.split("\\t"));
		while ((in = reader.readLine()) != null) {
			String[] line = in.split("\\t");
			levels.put(lineNumber, line);
			lineNumber++;
		}
		reader.close();
		return levels;
	}

	private Map<Integer, String[]> readSuperUniquesFromOriginal() throws IOException {
		Map<Integer, String[]> superuniques = new HashMap<Integer, String[]>();
		this.superuniquesLookup = new HashMap<String, Integer>();
		BufferedReader reader = new BufferedReader(new FileReader("OriginalFiles\\SuperUniques.txt"));
		String in = reader.readLine();
		int lineNumber = 1;
		superuniques.put(0, in.split("\\t"));
		while ((in = reader.readLine()) != null) {
			String[] line = in.split("\\t");
			superuniques.put(lineNumber, line);
			superuniquesLookup.put(line[0], lineNumber);
			lineNumber++;
		}
		reader.close();
		return superuniques;
	}

	private Map<Integer, String[]> readMonstersFromOriginal() throws IOException {
		Map<Integer, String[]> monsters = new HashMap<Integer, String[]>();
		this.monstersLookup = new HashMap<String, Integer>();
		BufferedReader reader = new BufferedReader(new FileReader("OriginalFiles\\monstats.txt"));
		String in = reader.readLine();
		int lineNumber = 1;
		monsters.put(0, in.split("\\t"));
		while ((in = reader.readLine()) != null) {
			String[] line = in.split("\\t");
			monsters.put(lineNumber, line);
			monstersLookup.put(line[0], lineNumber);
			lineNumber++;
		}
		reader.close();
		return monsters;
	}

	public void rereadOriginals() throws IOException {
		this.levels = this.readLevelsFromOriginal();
		this.superuniques = this.readSuperUniquesFromOriginal();
		this.monsters = readMonstersFromOriginal();
	}

	public void writeChanges() throws IOException {
		this.writeSuperUniques();
		this.writeMonstats();
		this.writeLevels();
		this.rereadOriginals();
	}

	private void writeSuperUniques() throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("D:\\Games\\Diablo II\\Data\\Global\\Excel\\SuperUniques.txt"));
		StringBuilder builder = new StringBuilder();

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
		writer.flush();
		writer.close();

	}

	private void writeMonstats() throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("D:\\Games\\Diablo II\\Data\\Global\\Excel\\monstats.txt"));
		StringBuilder builder = new StringBuilder();

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
		writer.flush();
		writer.close();

	}

	private void writeLevels() throws IOException {
		BufferedWriter writer = new BufferedWriter(
				new FileWriter("D:\\Games\\Diablo II\\Data\\Global\\Excel\\Levels.txt"));
		StringBuilder builder = new StringBuilder();

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
		writer.flush();
		writer.close();
	}

	public static void resetTerrorZones() {
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
		}
	}

	public void applyTerrorZone(TerrorZone selection, int charlevel) {
		if (selection == TerrorZone.TRAVINCAL || selection == TerrorZone.DURANCE_OF_HATE) {
			this.adjustCouncilTC();
		}
		this.desecrateMonsters(selection.getMonsters(), charlevel);
		this.adjustAreaLevels(selection.getLevelLines(), charlevel);
		this.handleSpecialCases(selection.getSpecialCases(), selection.getMonsters(), charlevel);
	}

	private void handleSpecialCases(SpecialHandlingEntry[] specialCases, String[] strings, int charlevel) {
		for (SpecialHandlingEntry entry : specialCases) {
			if (this.superuniquesLookup.containsKey(entry.toString())) {
				// we can change it in superuniques
				int superuniqueIndex = this.superuniquesLookup.get(entry.toString());
				String[] superuniqueLines = this.superuniques.get(superuniqueIndex);
				// if we haven't changed the base monster, change it now
				if (!this.isStringInArray(superuniqueLines[2], strings)) {
					this.desecrateMonster(superuniqueLines[2], charlevel);
				}
				// nameString
				superuniqueLines[1] += "Terror";
				// some monsters are weird
				if (entry.hasFixedTC()) {
					this.changeTCForSnowflakes(entry, charlevel);
					continue;
				}
				// change the tc if it has a special one
				if (entry.isMoreWork()) {
					String tcSuffix = " Desecrated";
					// Nihla has no ' A' suffix because of high level
					if (!(entry == SpecialHandlingEntry.NIHLATHAK)) {
						tcSuffix += " A";
					}
					superuniqueLines[17] += tcSuffix;
					superuniqueLines[18] += tcSuffix;
					superuniqueLines[19] += tcSuffix;
				}
			} else {
				// this is a bossmonster with a special tc, no other possibility
				int monsterIndex = this.monstersLookup.get(entry.toString());
				this.desecrateMonster(entry.toString(), charlevel);
				String[] monsterLine = monsters.get(monsterIndex);
				for (int index = 236; index < 248; index += 4) {
					// autotc upgrading does NOT work on placed bosses, so we have to do it manually
					monsterLine[index] += this.getMonstatsTCSuffix(entry, charlevel, index);
					monsterLine[index + 1] = monsterLine[index];
					monsterLine[index + 2] = monsterLine[index];
					monsterLine[index + 3] = monsterLine[index];
				}
			}
		}
	}

	private void changeTCForSnowflakes(SpecialHandlingEntry entry, int charlevel) {
		switch (entry) {
		case GRISWOLD:
		case RADAMENT:
			String[] superunique = superuniques.get(superuniquesLookup.get(entry.toString()));
			int arrayIndex = 17;
			for (int index = 236; index < 248; index += 4) {
				superunique[arrayIndex++] += this.getMonstatsTCSuffix(entry, charlevel, index);
			}
			break;
		default:
			System.err.println("DEFAULT SNOWFLAKE " + entry.name());
			String monster = superuniques.get(superuniquesLookup.get(entry.toString()))[2];
			int monsterIndex = this.monstersLookup.get(monster);
			String[] monsterLine = monsters.get(monsterIndex);
			for (int index = 236; index < 248; index += 4) {
				monsterLine[index] += this.getMonstatsTCSuffix(entry, charlevel, index);
				monsterLine[index + 1] = monsterLine[index];
				monsterLine[index + 2] = monsterLine[index];
				monsterLine[index + 3] = monsterLine[index];
			}
		}

	}

	private String getMonstatsTCSuffix(SpecialHandlingEntry entry, int charlevel, int index) {
		int bosslevel = charlevel + 5;
		int difficulty = 1;
		if (index > 243) {
			difficulty = 2;
		}
		if (index < 240) {
			difficulty = 0;
		}

		switch (entry) {
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
			System.err.println("Default case detected for: " + entry.name());
			return " Desecrated";
		}
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
			if (!second[index].contains("Desecrated")){
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
		// TCs are handles in the special cases. only the council mobs and bosses have a
		// special drop pool that doesn't scale
		this.monsters.put(monsterID, monsterLine);
	}

	public void applyChaos(int charlevel) {
		Set<String> monsterStrings = new HashSet<String>();
		Set<SpecialHandlingEntry> specialEntries = new HashSet<SpecialHandlingEntry>();
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
		SpecialHandlingEntry[] specialArray = new SpecialHandlingEntry[specialEntries.size()];
		i = 0;
		for (SpecialHandlingEntry s : specialEntries) {
			specialArray[i++] = s;
		}
		int[] levelsArray = new int[levelsSet.size()];
		i = 0;
		for (int s : levelsSet) {
			levelsArray[i++] = s;
		}
		
		this.desecrateMonsters(monsterArray, charlevel);
		this.handleSpecialCases(specialArray, monsterArray, charlevel);
		this.adjustAreaLevels(levelsArray, charlevel);
		this.adjustCouncilTC();
	}
}
