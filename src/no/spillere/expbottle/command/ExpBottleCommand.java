package no.spillere.expbottle.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import no.spillere.expbottle.handlers.MainHandler;
import no.spillere.expbottle.handlers.InfoKeeper;

public class ExpBottleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("spillere.quest.xpbottle")) {
			if(args.length == 1) {
				if(StringUtils.isNumeric(args[0])) {
					int outXp = Integer.parseInt(args[0]);
					if(outXp <= InfoKeeper.maxXp && outXp >= InfoKeeper.minXp) {
						if(outXp <= player.getTotalExperience()) {
							player.sendMessage(ChatColor.GREEN + "Du tok ut " + ChatColor.RESET + outXp + ChatColor.GREEN + " XP.");
							MainHandler.givePlayerExpBottle(player, outXp);
							MainHandler.removePlayerExp(player, outXp);
						}
						else {
							player.sendMessage(InfoKeeper.notEnoughExp);
						}
					}
					else {
						player.sendMessage(InfoKeeper.overMaxUnderMin);
					}
				}
				else {
					player.sendMessage(InfoKeeper.xpNotANumber);
				}
			}
			else {
				player.sendMessage(InfoKeeper.cmdUsage);
			}
		}
		else {
			player.sendMessage(InfoKeeper.noPermission);
		}
		return true;
	}


}