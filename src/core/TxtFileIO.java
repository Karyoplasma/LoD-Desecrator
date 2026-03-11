package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class TxtFileIO {

	private TxtFileIO() {
	}
	
	public static GameData loadAllOriginals() throws IOException {
		Map<Integer, String[]> monsters = readTxtFile("monstats.txt");
		Map<Integer, String[]> superuniques = readTxtFile("SuperUniques.txt");
		Map<Integer, String[]> levels = readTxtFile("Levels.txt");
		
		return new GameData(monsters, superuniques, levels);
	}
	
	private static Map<Integer, String[]> readTxtFile(String whichFile) throws IOException {
		Map<Integer, String[]> ret = new HashMap<Integer, String[]>();
		Path filePath = Paths.get("OriginalFiles", whichFile);
		try (BufferedReader reader = Files.newBufferedReader(filePath)) {
			String in;
			int lineNumber = 0;
			while ((in = reader.readLine()) != null) {
				if (in.isEmpty()) {
					continue;
				}
				String[] line = in.split("\\t");
				ret.put(lineNumber, line);
				lineNumber++;
			}
		}
		return ret;
	}
	
	public static int writeTxtFile(Path path, Map<String, String[]> changed, Map<Integer, String[]> originals) {
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
	        for (int i = 0; i < originals.size(); i++) {
	            String[] temp = originals.get(i);
	            temp = changed.getOrDefault(temp[0], temp);
	            writer.write(String.join("\t", temp));
	            writer.write("\r\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return 1;
	    }
	    return 0;
	}
	
	public static int resetTerrorZones(Path modPath) {
		Path superuniquesFile = Paths.get("OriginalFiles/SuperUniques.txt");
		Path superuniquesPath = modPath.resolve("SuperUniques.txt");
		Path monstatsFile = Paths.get("OriginalFiles/monstats.txt");
		Path monstatsPath = modPath.resolve("monstats.txt");
		Path levelsFile = Paths.get("OriginalFiles/Levels.txt");
		Path levelsPath = modPath.resolve("Levels.txt");
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
