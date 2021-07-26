package me.brannstroom.expbottle.handlers;

import java.util.List;

import me.brannstroom.expbottle.ExpBottle;
import me.brannstroom.expbottle.model.Experience;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class InfoKeeper {
	
	static FileConfiguration config = ExpBottle.getPlugin(ExpBottle.class).getConfig();
	
	// Language
	public static String noPermission = ChatColor.translateAlternateColorCodes('&', config.getString("noPermission"));
	public static String cmdUsageUser = ChatColor.translateAlternateColorCodes('&', config.getString("cmdUsageUser"));
	public static String cmdUsageAdmin = ChatColor.translateAlternateColorCodes('&', config.getString("cmdUsageAdmin"));
	public static String notEnoughXp = ChatColor.translateAlternateColorCodes('&', config.getString("notEnoughXp"));
	public static String xpNotANumber = ChatColor.translateAlternateColorCodes('&', config.getString("xpNotANumber"));
	public static String overMaxUnderMin = ChatColor.translateAlternateColorCodes('&', config.getString("overMaxUnderMin"));
	public static String successfulWithdraw = ChatColor.translateAlternateColorCodes('&', config.getString("successfulWithdraw"));
	public static String reloadSuccessful = ChatColor.translateAlternateColorCodes('&', config.getString("reloadSuccessful"));
	public static String xpBottleGive = ChatColor.translateAlternateColorCodes('&', config.getString("xpBottleGive"));
	public static String xpBottleReceive = ChatColor.translateAlternateColorCodes('&', config.getString("xpBottleReceive"));
	public static String playerNotOnline = ChatColor.translateAlternateColorCodes('&', config.getString("playerNotOnline"));
	public static String giveYourselfXp = ChatColor.translateAlternateColorCodes('&', config.getString("giveYourselfXp"));
	
	// XpBottle
	public static String xpBottleName = ChatColor.translateAlternateColorCodes('&', config.getString("xpBottleName"));
	public static List<String> xpBottleLore = config.getStringList("xpBottleLore");
	
	public static boolean tax = config.getBoolean("tax");
	public static double taxAmount = config.getDouble("taxAmount");
	
	public static int minXp = config.getInt("minXp");
	public static int maxXp = config.getInt("maxXp");
	
	// Aliases
	public static List<String> commandAliases = config.getStringList("commandAliases");
	public static List<String> giveAliases = config.getStringList("giveAliases");
	public static List<String> reloadAliases = config.getStringList("reloadAliases");
	public static List<String> allAliases = config.getStringList("allAliases");
	
	public static String getInfoKeeper(Player player, String string, int exp, int playerExp) {
		
		string = string.replace("%playername%", player.getName());
		string = string.replace("%playerdisplayname%", player.getDisplayName());
		
		string = string.replace("%xp%", Integer.toString(exp));
		string = string.replace("%minxp%", Integer.toString(minXp));	
		string = string.replace("%maxxp%", Integer.toString(maxXp));
		string = string.replace("%missingxp%", Integer.toString(exp- Experience.getExp(player)));
		string = string.replace("%playerxp%", Integer.toString(Experience.getExp(player)));
		
		string = string.replace("%tax%", Integer.toString((int) (taxAmount*100)));
		string = string.replace("%taxprice%", Integer.toString((int) (exp*(1+InfoKeeper.taxAmount))));
		string = string.replace("%taxout%", Integer.toString((int) (exp/(1+InfoKeeper.taxAmount))));
		string = string.replace("%maxout%", Integer.toString((int) (Experience.getExp(player)/(1+InfoKeeper.taxAmount))));
		
		return string;
	}
	
	public static String getReceiveInfoKeeper(Player giver, Player receiver, String string, int exp) {
		
		string = string.replace("%playername%", giver.getName());
		string = string.replace("%playerdisplayname%", giver.getDisplayName());
		string = string.replace("%receivername%", receiver.getName());
		string = string.replace("%receiverdisplayname%", receiver.getDisplayName());
		
		string = string.replace("%xp%", Integer.toString(exp));
		string = string.replace("%minxp%", Integer.toString(minXp));	
		string = string.replace("%maxxp%", Integer.toString(maxXp));
		string = string.replace("%playerxp%", Integer.toString(Experience.getExp(giver)));
		
		string = string.replace("%tax%", Integer.toString((int) (taxAmount*100)));
		string = string.replace("%taxprice%", Integer.toString((int) (exp/(1-InfoKeeper.taxAmount))));
		string = string.replace("%taxout%", Integer.toString((int) (exp*(1-InfoKeeper.taxAmount))));
		string = string.replace("%maxout%", Integer.toString((int) (Experience.getExp(giver)/(1+InfoKeeper.taxAmount))));
		
		return string;
	}
}
