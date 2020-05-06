package no.spillere.expbottle.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import no.spillere.expbottle.handlers.MainHandler;
import no.spillere.expbottle.ExpBottlePlugin;
import no.spillere.expbottle.handlers.InfoKeeper;

public class ExpBottleCommand implements CommandExecutor {
	
	Plugin plugin = ExpBottlePlugin.getPlugin(ExpBottlePlugin.class);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("expbottle.xpbottle") || player.isOp()) {
			if(args.length == 1) {
				if(StringUtils.isNumeric(args[0])) {
					int xp = Integer.parseInt(args[0]);
					if(xp <= InfoKeeper.maxXp && xp >= InfoKeeper.minXp) {
						if(xp <= player.getTotalExperience()) {
							player.sendMessage(InfoKeeper.getInfoKeeper(player,  InfoKeeper.successfulWithdraw,  xp,  player.getTotalExperience()));
							MainHandler.givePlayerExpBottle(player, xp);
							MainHandler.removePlayerExp(player, xp);
						}
						else {
							player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.notEnoughXp, xp, player.getTotalExperience()));
						}
					}
					else {
						player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.overMaxUnderMin, xp, player.getTotalExperience()));
					}
				}
				else if(args[0].equalsIgnoreCase("reload")) {
					if(player.hasPermission("expbottle.reload")) {
						ExpBottlePlugin.instance.reloadConfig();
						player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.reloadSuccessful, 0, player.getTotalExperience()));
					}
					else {
						player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.noPermission, 0, player.getTotalExperience()));
					}
				}
				else {
					player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.xpNotANumber, 0, player.getTotalExperience()));
				}
			}
			else {
				player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.cmdUsage, 0, player.getTotalExperience()));
			}
		}
		else {
			player.sendMessage(InfoKeeper.getInfoKeeper(player, InfoKeeper.noPermission, 0, player.getTotalExperience()));
		}
		return true;
	}


}