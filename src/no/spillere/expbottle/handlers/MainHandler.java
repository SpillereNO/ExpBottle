package no.spillere.expbottle.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class MainHandler {

	public static boolean hasInventorySpace(Player player) {
		boolean hasSpace = false;
		for(int i = 0; i < 29; i++) {
			if(player.getInventory().firstEmpty() == i) {
				hasSpace = true;
			}  
		}

		return hasSpace;
	}

	public static void givePlayerItemStack(Player player, ItemStack item) {
		if(hasInventorySpace(player)) {
			player.getInventory().addItem(item);
		}
		else {
			player.getWorld().dropItem(player.getLocation(), item);
		}
	}

	public static void givePlayerExpBottle(Player player, int exp) {
		ItemStack xpBottle = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
		ItemMeta xpBottleMeta = xpBottle.getItemMeta();
		xpBottleMeta.setDisplayName(InfoKeeper.getInfoKeeper(player, InfoKeeper.xpBottleName, exp, player.getTotalExperience()));
		List<String> lore = new ArrayList<>();
		for(String string : InfoKeeper.xpBottleLore) {
			string = ChatColor.translateAlternateColorCodes('&', string);
			lore.add(InfoKeeper.getInfoKeeper(player, string, exp, player.getTotalExperience()));
		}
		xpBottleMeta.setLore(lore);
		xpBottle.setItemMeta(xpBottleMeta);


		MainHandler.givePlayerItemStack(player, xpBottle);
	}

	public static void removePlayerExp(Player player, int exp) {
		int hasExp = player.getTotalExperience();
		player.setTotalExperience(0);
		player.setLevel(0);
		player.setExp(0);

		player.giveExp(hasExp-exp);
	}
	
	public static int getXpLoreLine(ItemStack item) {
		int line = 0;
		
		for(int i = 0; i < InfoKeeper.xpBottleLore.size(); i++) {
			if(InfoKeeper.xpBottleLore.get(i).contains("%xp%")) {
				line = i;
			}
		}
		
		return line;
	}
}
