package me.brannstroom.expbottle.command;

import me.brannstroom.expbottle.ExpBottle;
import me.brannstroom.expbottle.handlers.InfoKeeper;
import me.brannstroom.expbottle.handlers.MainHandler;
import me.brannstroom.expbottle.model.Experience;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ExpBottleCommand implements CommandExecutor {

    Plugin plugin = ExpBottle.getPlugin(ExpBottle.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission("expbottle.xpbottle") || player.isOp()) {
            if(args.length == 1) {
                if(StringUtils.isNumeric(args[0])) {
                    int xp = Integer.parseInt(args[0]);
                    if(xp <= InfoKeeper.maxXp && xp >= InfoKeeper.minXp) {
                        if(InfoKeeper.tax) {
                            double price = (xp*(1+InfoKeeper.taxAmount));
                            if(price <= Experience.getExp(player)) {
                                player.sendMessage(InfoKeeper.getInfoKeeper(player,  InfoKeeper.successfulWithdraw,  xp, Experience.getExp(player)));
                                MainHandler.givePlayerExpBottle(player, xp);
                                MainHandler.removePlayerExp(player, (int)price);
                            }
                            else {
                                player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.notEnoughXp, xp, Experience.getExp(player)));
                            }
                        }
                        else {
                            if(xp <= Experience.getExp(player)) {
                                player.sendMessage(InfoKeeper.getInfoKeeper(player,  InfoKeeper.successfulWithdraw,  xp, Experience.getExp(player)));
                                MainHandler.givePlayerExpBottle(player, xp);
                                MainHandler.removePlayerExp(player, xp);
                            }else {
                                player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.notEnoughXp, xp, Experience.getExp(player)));
                            }
                        }
                    }else {
                        player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.overMaxUnderMin, xp, Experience.getExp(player)));
                    }
                }else if(args[0].equalsIgnoreCase("all") || InfoKeeper.allAliases.contains(args[0])) {
                    int xp = Experience.getExp(player);
                    if(xp <= InfoKeeper.maxXp && xp >= InfoKeeper.minXp) {
                        if(InfoKeeper.tax) {
                            player.sendMessage(InfoKeeper.getInfoKeeper(player,  InfoKeeper.successfulWithdraw,  (int) (xp/(1+InfoKeeper.taxAmount)),  Experience.getExp(player)));
                            MainHandler.givePlayerExpBottle(player, (int) (xp/(1+InfoKeeper.taxAmount)));
                            MainHandler.removePlayerExp(player, xp);
                        }
                        else {
                            player.sendMessage(InfoKeeper.getInfoKeeper(player,  InfoKeeper.successfulWithdraw,  xp,  Experience.getExp(player)));
                            MainHandler.givePlayerExpBottle(player, xp);
                            MainHandler.removePlayerExp(player, xp);
                        }
                    }else {
                        player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.overMaxUnderMin, xp, Experience.getExp(player)));
                    }
                }
                else if(args[0].equalsIgnoreCase("reload") || InfoKeeper.reloadAliases.contains(args[0])) {
                    if(player.hasPermission("expbottle.reload")) {
                        ExpBottle.instance.reloadConfig();
                        player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.reloadSuccessful, 0, Experience.getExp(player)));
                    }else {
                        player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.noPermission, 0, Experience.getExp(player)));
                    }
                }else {
                    player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.xpNotANumber, 0, Experience.getExp(player)));
                }
            }else if(args.length == 3 && player.hasPermission("expbottle.admin")) {
                if(args[0].equalsIgnoreCase("give") || InfoKeeper.giveAliases.contains(args[0])) {
                    if(StringUtils.isNumeric(args[2])) {
                        int xp = Integer.parseInt(args[2]);
                        if(xp <= InfoKeeper.maxXp && xp >= InfoKeeper.minXp) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if(target != null) {
                                MainHandler.givePlayerExpBottle(target, xp);
                                if(target != player) {
                                    target.sendMessage(InfoKeeper.getReceiveInfoKeeper(player, target, InfoKeeper.xpBottleReceive, xp));
                                    player.sendMessage(InfoKeeper.getReceiveInfoKeeper(player, target, InfoKeeper.xpBottleGive, xp));
                                }else {
                                    player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.giveYourselfXp, xp, Experience.getExp(player)));
                                }
                            }else {
                                player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.playerNotOnline, 0, Experience.getExp(player)));
                            }
                        }else {
                            player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.overMaxUnderMin, xp, Experience.getExp(player)));
                        }
                    }else {
                        player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.xpNotANumber, 0, Experience.getExp(player)));
                    }
                }else {
                    player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.cmdUsageAdmin, 0, Experience.getExp(player)));
                }
            }else {
                if(player.hasPermission("expbottle.admin")) {
                    player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.cmdUsageAdmin, 0, Experience.getExp(player)));
                }else {
                    player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.cmdUsageUser, 0, Experience.getExp(player)));
                }
            }
        }else {
            player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.noPermission, 0, Experience.getExp(player)));
        }
        return true;
    }


}