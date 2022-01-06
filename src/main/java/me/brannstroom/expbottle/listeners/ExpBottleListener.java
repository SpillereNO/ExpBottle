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
        ThrownExpBottle expBottle = event.getEntity();
        ItemStack item = expBottle.getItem();
        if (item.hasItemMeta()) {
            if (item.getItemMeta().hasLore()) {
                List<String> lore = item.getItemMeta().getLore();
                String str = ChatColor.stripColor(lore.get(MainHandler.getXpLoreLine()));
                int xp = Integer.parseInt(str.replaceAll("\\D+", ""));
                event.setExperience(xp);
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