package me.brannstroom.expbottle.handlers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import me.brannstroom.expbottle.ExpBottle;
import me.brannstroom.expbottle.command.ExpBottleCommand;
import me.brannstroom.expbottle.model.Experience;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class InfoKeeper {


    private static FileConfiguration config = getUpdatedConfig();

    // Language
    public static String noPermission = ChatColor.translateAlternateColorCodes('&', config.getString("No Permission"));
    public static String cmdUsageUser = ChatColor.translateAlternateColorCodes('&', config.getString("Command Usage User"));
    public static String cmdUsageAdmin = ChatColor.translateAlternateColorCodes('&', config.getString("Command Usage Admin"));
    public static String notEnoughXp = ChatColor.translateAlternateColorCodes('&', config.getString("Not Enough Experience"));
    public static String xpNotANumber = ChatColor.translateAlternateColorCodes('&', config.getString("Invalid XP"));
    public static String overMaxUnderMin = ChatColor.translateAlternateColorCodes('&', config.getString("XP is more than max or less than min"));
    public static String successfulWithdraw = ChatColor.translateAlternateColorCodes('&', config.getString("Successful Withdraw"));
    public static String successfulRedeem = ChatColor.translateAlternateColorCodes('&', config.getString("Successful Redeem"));
    public static String reloadSuccessful = ChatColor.translateAlternateColorCodes('&', config.getString("Reload Successful"));
    public static String xpBottleGive = ChatColor.translateAlternateColorCodes('&', config.getString("XP Bottle Give"));
    public static String xpBottleReceive = ChatColor.translateAlternateColorCodes('&', config.getString("XP Bottle Receive"));
    public static String playerNotOnline = ChatColor.translateAlternateColorCodes('&', config.getString("Player Not Online"));
    public static String giveYourselfXp = ChatColor.translateAlternateColorCodes('&', config.getString("Give Yourself XP"));
    public static String couldNotFindXpLine = ChatColor.translateAlternateColorCodes('&', config.getString("Could Not Find Xp Line"));

    // XpBottle
    public static String xpBottleName = ChatColor.translateAlternateColorCodes('&', config.getString("XP Bottle Name"));
    public static List<String> xpBottleLore = config.getStringList("XP Bottle Lore");

    public static boolean tax = config.getBoolean("Tax");
    public static double taxAmount = config.getDouble("Tax Amount");

    public static int minXp = config.getInt("Min XP");
    public static int maxXp = config.getInt("Max XP");

    public static boolean throwable = config.getBoolean("Throwable");

    // Aliases
    public static List<String> commandAliases = config.getStringList("Command Aliases");
    public static List<String> giveAliases = config.getStringList("Give Aliases");
    public static List<String> reloadAliases = config.getStringList("Reload Aliases");
    public static List<String> allAliases = config.getStringList("All Aliases");

    public static String getInfoKeeper(Player player, String string, int exp, int playerExp) {

        string = string.replace("%playername%", player.getName());
        string = string.replace("%playerdisplayname%", player.getDisplayName());

        string = string.replace("%xp%", Integer.toString(exp));
        string = string.replace("%minxp%", Integer.toString(minXp));
        string = string.replace("%maxxp%", Integer.toString(maxXp));
        string = string.replace("%missingxp%", Integer.toString(exp - Experience.getExp(player)));
        string = string.replace("%playerxp%", Integer.toString(Experience.getExp(player)));

        string = string.replace("%tax%", Integer.toString((int) (taxAmount * 100)));
        string = string.replace("%taxprice%", Integer.toString((int) (exp * (1 + InfoKeeper.taxAmount))));
        string = string.replace("%taxout%", Integer.toString((int) (exp / (1 + InfoKeeper.taxAmount))));
        string = string.replace("%maxout%", Integer.toString((int) (Experience.getExp(player) / (1 + InfoKeeper.taxAmount))));

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

        string = string.replace("%tax%", Integer.toString((int) (taxAmount * 100)));
        if(tax) string = string.replace("%taxprice%", Integer.toString((int) (exp / (1 - InfoKeeper.taxAmount))));
        else string = string.replace("%taxprice%", Integer.toString(exp));
        string = string.replace("%taxout%", Integer.toString((int) (exp * (1 - InfoKeeper.taxAmount))));
        string = string.replace("%maxout%", Integer.toString((int) (Experience.getExp(giver) / (1 + InfoKeeper.taxAmount))));

        return string;
    }

    public static void updateConfig() {
        config = getUpdatedConfig();

        // Language
        noPermission = ChatColor.translateAlternateColorCodes('&', config.getString("No Permission"));
        cmdUsageUser = ChatColor.translateAlternateColorCodes('&', config.getString("Command Usage User"));
        cmdUsageAdmin = ChatColor.translateAlternateColorCodes('&', config.getString("Command Usage Admin"));
        notEnoughXp = ChatColor.translateAlternateColorCodes('&', config.getString("Not Enough Experience"));
        xpNotANumber = ChatColor.translateAlternateColorCodes('&', config.getString("Invalid XP"));
        overMaxUnderMin = ChatColor.translateAlternateColorCodes('&', config.getString("XP is more than max or less than min"));
        successfulWithdraw = ChatColor.translateAlternateColorCodes('&', config.getString("Successful Withdraw"));
        successfulRedeem = ChatColor.translateAlternateColorCodes('&', config.getString("Successful Redeem"));
        reloadSuccessful = ChatColor.translateAlternateColorCodes('&', config.getString("Reload Successful"));
        xpBottleGive = ChatColor.translateAlternateColorCodes('&', config.getString("XP Bottle Give"));
        xpBottleReceive = ChatColor.translateAlternateColorCodes('&', config.getString("XP Bottle Receive"));
        playerNotOnline = ChatColor.translateAlternateColorCodes('&', config.getString("Player Not Online"));
        giveYourselfXp = ChatColor.translateAlternateColorCodes('&', config.getString("Give Yourself XP"));
        couldNotFindXpLine = ChatColor.translateAlternateColorCodes('&', config.getString("Could Not Find Xp Line"));

        // XpBottle
        xpBottleName = ChatColor.translateAlternateColorCodes('&', config.getString("XP Bottle Name"));
        xpBottleLore = config.getStringList("XP Bottle Lore");

        tax = config.getBoolean("Tax");
        taxAmount = config.getDouble("Tax Amount");

        minXp = config.getInt("Min XP");
        maxXp = config.getInt("Max XP");

        throwable = config.getBoolean("Throwable");

        // Aliases
        commandAliases = config.getStringList("Command Aliases");
        giveAliases = config.getStringList("Give Aliases");
        reloadAliases = config.getStringList("Reload Aliases");
        allAliases = config.getStringList("All Aliases");

        String[] aliases = new String[commandAliases.size()];
        commandAliases.toArray(aliases);
        ExpBottle.registerCommand("expbottle", new ExpBottleCommand(), aliases);
    }

    private static FileConfiguration getUpdatedConfig() {
        File customConfigFile;
        FileConfiguration customConfig;
        customConfigFile = new File(ExpBottle.instance.getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            ExpBottle.instance.saveResource("config.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return customConfig;
    }
}
