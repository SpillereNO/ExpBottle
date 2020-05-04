package no.spillere.expbottle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;

import no.spillere.expbottle.command.ExpBottleCommand;
import no.spillere.expbottle.listeners.ExpBottleListener;

public class ExpBottlePlugin extends JavaPlugin {

	public static ExpBottlePlugin instance;

	public void onEnable() {
		instance = this;
		loadListeners();
		loadCommands();
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		expBottleConfig.setup();
		

		expBottleConfig.get().addDefault("noPermission", "You do not have the right permissions to excecute this command");
		expBottleConfig.get().addDefault("cmdUsage", "%blue%Use %yellow&/xpbottle <XP> %blue%to take out experience");
		expBottleConfig.get().addDefault("notEnoughExp", "You do not have the sufficient experience to take out");
		expBottleConfig.get().addDefault("xpNotANumber", "XP must be a number");
		expBottleConfig.get().addDefault("overMaxUnderMin", "You cant take out more than 100000 or less than 50 XP");
		expBottleConfig.get().addDefault("successfulTakeOut", "You successfully withdrawed xp");
		
		expBottleConfig.get().addDefault("expBottleName", "ExpBottle");
		expBottleConfig.get().addDefault("xpBottleLore1", "Creator: ");
		expBottleConfig.get().addDefault("xpBottleLore2", "XP: ");
		expBottleConfig.get().addDefault("minXp", 50);
		expBottleConfig.get().addDefault("maxXp", 100000);
		
		expBottleConfig.get().options().copyDefaults(true);
		expBottleConfig.save();
	}

	public void onDisable() {

	}

	private void loadListeners() {
		getServer().getPluginManager().registerEvents(new ExpBottleListener(), this);
	}

	private void loadCommands() {
		registerCommand("xpbottle", new ExpBottleCommand(), "expbottle");
	}

	public void registerCommand(String name, CommandExecutor executor, String... aliases) {
		try {
			Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			constructor.setAccessible(true);

			PluginCommand command = constructor.newInstance(name, this);

			command.setExecutor(executor);
			command.setAliases(Lists.newArrayList(aliases));
			if (executor instanceof TabCompleter) {
				command.setTabCompleter((TabCompleter) executor);
			}
			this.getCommandMap().register("expbottle", command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private CommandMap getCommandMap() {
		try {
			org.bukkit.Server server = Bukkit.getServer();
			Field commandMap = server.getClass().getDeclaredField("commandMap");
			commandMap.setAccessible(true);
			return (CommandMap) commandMap.get(server);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
			return null;
		}
	}
}