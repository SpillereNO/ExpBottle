package no.spillere.expbottle.listeners;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.inventory.ItemStack;

import no.spillere.expbottle.handlers.InfoKeeper;

public class ExpBottleListener implements Listener {

	@EventHandler
	public void onExpBottle(ExpBottleEvent event) {
		ThrownExpBottle expBottle = event.getEntity();
		ItemStack item = expBottle.getItem();
		if(item.hasItemMeta()) {
			if(item.getItemMeta().hasLore()) {
				List<String> lore = item.getItemMeta().getLore();
				if(lore.get(0).contains(InfoKeeper.xpBottleLore1) && lore.get(1).contains(InfoKeeper.xpBottleLore2)) {
					String str = ChatColor.stripColor(lore.get(1));
					int xp = Integer.parseInt(str.replaceAll("\\D+",""));
					event.setExperience(xp);
				}
			}
		}
	}
}