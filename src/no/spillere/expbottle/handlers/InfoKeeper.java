package no.spillere.expbottle.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import no.spillere.expbottle.expBottleConfig;

public class InfoKeeper {
	
	
	// Language
	public static String noPermission = expBottleConfig.get().getString("noPermission");
	public static String cmdUsage = expBottleConfig.get().getString("cmdUsage");
	public static String notEnoughExp = expBottleConfig.get().getString("notEnoughExp");
	public static String xpNotANumber = expBottleConfig.get().getString("xpNotANumber");
	public static String overMaxUnderMin = expBottleConfig.get().getString("overMaxUnderMin");
	public static String successfulTakeOut = expBottleConfig.get().getString("successfulTakeOut");
	
	
	// XpBottle
	public static String xpBottleName = expBottleConfig.get().getString("expBottleName");
	public static String xpBottleLore1 = expBottleConfig.get().getString("xpBottleLore1");
	public static String xpBottleLore2 = expBottleConfig.get().getString("xpBottleLore2");
	
	public static int minXp = expBottleConfig.get().getInt("minXp");
	public static int maxXp = expBottleConfig.get().getInt("maxXp");
	
	public static String getInfoKeeper(String string, Player player, int exp) {
		
		String str = ChatColor.translateAlternateColorCodes('&', string);
		
		return str;
	}

}
