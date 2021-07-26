package me.brannstroom.expbottle.listeners;

import java.util.List;

import me.brannstroom.expbottle.handlers.InfoKeeper;
import me.brannstroom.expbottle.handlers.MainHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class ExpBottleListener implements Listener {

	@EventHandler
	public void onExpBottle(ExpBottleEvent event) {
		Bukkit.broadcastMessage("Hei.");
		ThrownExpBottle expBottle = event.getEntity();
		ItemStack item = expBottle.getItem();
		if(item.hasItemMeta()) {
			Bukkit.broadcastMessage("Check 1");
			if(item.getItemMeta().hasLore()) {
				Bukkit.broadcastMessage("Check 2");
				List<String> lore = item.getItemMeta().getLore();
				Bukkit.broadcastMessage("Check 3");
				Bukkit.broadcastMessage("AAA: " + lore.get(MainHandler.getXpLoreLine(item)).toLowerCase());
				Bukkit.broadcastMessage("BBB: " + lore.get(0));
				Bukkit.broadcastMessage("CCC: " + lore.get(1));
				if(lore.get(MainHandler.getXpLoreLine(item)).toLowerCase().startsWith("xp:")) {
					Bukkit.broadcastMessage("Check 4");
					String str = ChatColor.stripColor(lore.get(MainHandler.getXpLoreLine(item)));
					Bukkit.broadcastMessage("Check 5");
					int xp = Integer.parseInt(str.replaceAll("\\D+",""));
					Bukkit.broadcastMessage("eeeexp: " + xp);
					Bukkit.broadcastMessage("Check 6");
					event.setExperience(xp);
					Bukkit.broadcastMessage("Check 7");
				}
			}
		}
	}
	
	@EventHandler
	public void playerRenameItem(InventoryClickEvent event) {
		if (event.getView().getType().equals(InventoryType.ANVIL)) {
			if (event.getRawSlot() == 2) {
				if (event.getInventory().getItem(0) != null) {
					if (event.getInventory().getItem(0).getItemMeta().getDisplayName().equals(InfoKeeper.xpBottleName)) {
						event.setCancelled(true);
					}
				}
			}
		}
	}
}