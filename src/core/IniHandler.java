package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IniHandler {
	
	private IniHandler() {
		
	}
	
	public static Path readModPath() {
        File iniFile = new File("Desecrator.ini");
        
        if (!iniFile.exists()) {
            return null;
        }
        try {
            String line = Files.readAllLines(iniFile.toPath()).get(0);
            if (line.startsWith("ModPath=")) {
                Path dirPath = Paths.get(line.substring(8));
                if (Files.isDirectory(dirPath)) {
                    return dirPath;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	   public static void saveModPath(Path modPath) {
	        if (modPath == null || !Files.isDirectory(modPath)) {
	            return;
	        }
	        File iniFile = new File("Desecrator.ini");

	        try (BufferedWriter writer = Files.newBufferedWriter(iniFile.toPath(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
	            writer.write("ModPath=" + modPath.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
