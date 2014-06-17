package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;
import com.newgarbo.handbook.utils.PlayerUtils;

public class CommandVanish extends Command
{
	public CommandVanish()
	{
		super("commandbook.vanish", "vanish", Arrays.asList("v", "va", "hbvanish"), true);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		if (args.length >= 1)
		{
			if (sender.hasPermission("commandbook.vanish.others"))
			{
				if (Bukkit.getPlayer(args[0]) == null)
				{
					sender.sendMessage(Locale.translate("command.online", true));
				}
				else
				{
					sender.sendMessage(PlayerUtils.vanishPlayer(Bukkit.getPlayer(args[0]), true) ? String.format(Locale.translate("vanish.on.other", true), Bukkit.getPlayer(args[0]).getName()) : String.format(Locale.translate("vanish.off.other", true), Bukkit.getPlayer(args[0]).getName()));
				}
			}
			else
			{
				sender.sendMessage(String.format(Locale.translate("command.permission", true), this.getPermission() + ".others"));
			}
		}
		else
		{
			PlayerUtils.vanishPlayer((Player) sender, true);
		}
		
		return true;
	}
}
