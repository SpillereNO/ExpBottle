package no.spillere.expbottle;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class expBottleConfig {
	
	private static File file;
	private static FileConfiguration customFile;

	public static void setup() {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("ExpBottle").getDataFolder(), "config.yml");

		if(!file.exists()) {
			try {
				file.createNewFile();
			}
			catch(IOException ex) {
				
			}
		}
		customFile = YamlConfiguration.loadConfiguration(file);
	}
	
	public static FileConfiguration get() {
		return customFile;
	}
	
	public static void save() {
		try {
		customFile.save(file);
		}
		catch (IOException ex) {
			System.out.println("File couldn't be saved");
		}
	}
	
	public static void reload() {
		customFile = YamlConfiguration.loadConfiguration(file);
	}
}
