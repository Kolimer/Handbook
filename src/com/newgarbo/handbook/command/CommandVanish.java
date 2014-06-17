package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;
import com.newgarbo.handbook.main.Handbook;
import com.newgarbo.handbook.utils.PlayerUtils;

public class CommandVanish extends Command
{
	public CommandVanish()
	{
		super("commandbook.vanish", "vanish", Arrays.asList("v", "va", "hbvanish"), true);
	}
	
	@Override
	protected boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		if (args.length > 1)
		{
			if (sender.hasPermission("commandbook.vanish.others"))
			{
				if (Bukkit.getPlayer(args[0]) == null)
				{
					sender.sendMessage(Locale.translate("command.online", true));
				}
				else
				{
					PlayerUtils.vanishPlayer(Bukkit.getPlayer(args[0]), true);
					sender.sendMessage(Handbook.instance.playerData.vanished.contains(args[0]) ? String.format(Locale.translate("vanish.on.other", true), args[0]) : String.format(Locale.translate("vanish.off.other", true), args[0]));
				}
			}
			else
			{
				sender.sendMessage(String.format(Locale.translate("command.permission", true), this.getPermission()));
			}
		}
		else
		{
			PlayerUtils.vanishPlayer((Player) sender, true);
		}
		
		return true;
	}
}
