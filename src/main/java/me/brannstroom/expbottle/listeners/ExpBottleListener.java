package me.brannstroom.expbottle.listeners;

import java.util.List;

import me.brannstroom.expbottle.handlers.InfoKeeper;
import me.brannstroom.expbottle.handlers.MainHandler;
import me.brannstroom.expbottle.model.Experience;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ExpBottleListener implements Listener {

    @EventHandler
    public void onExpBottle(ExpBottleEvent event) {
        ThrownExpBottle expBottle = event.getEntity();
        ItemStack item = expBottle.getItem();
        if(MainHandler.isExpBottle(item)) {
            event.setExperience(MainHandler.getBottledExperience(item));
        }
    }

    @EventHandler
    public void onPlayerRenameItem(InventoryClickEvent event) {
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(InfoKeeper.throwable) return;
        if(event.getItem() == null) return;

        ItemStack item = event.getItem();
        boolean isExpBottle = MainHandler.isExpBottle(item);

        if(!isExpBottle) return;
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        event.setCancelled(true);

        Player player = event.getPlayer();

        if(item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        } else {
            if (player.getInventory().getItemInMainHand().equals(item)) {
                player.getInventory().setItemInMainHand(null);
            } else if (player.getInventory().getItemInOffHand().equals(item)) {
                player.getInventory().setItemInOffHand(null);
            } else {
                player.getInventory().remove(item);
            }
        }

        int bottledExperience = MainHandler.getBottledExperience(item);
        Experience.changeExp(player, bottledExperience);
    }

    // TODO: Disable xp bottle from dispenser
    @EventHandler
    public void onBlockDispense(BlockDispenseEvent event) {
        if(InfoKeeper.throwable) return;
        if(event.getItem() != null && MainHandler.isExpBottle(event.getItem())) {
            event.setCancelled(true);
        }
    }
}
