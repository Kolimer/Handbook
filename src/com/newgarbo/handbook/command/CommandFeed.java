package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;

public class CommandFeed extends Command
{
	public CommandFeed()
	{
		super("commandbook.feed", "feed", Arrays.asList("hbfeed"), true);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		if (args.length >= 1)
		{
			if (Bukkit.getPlayer(args[0]) != null)
			{
				Player target = Bukkit.getPlayer(args[0]);
				target.sendMessage(Locale.translate("feed", true));
				sender.sendMessage(String.format(Locale.translate("feed.other", true), target.getName()));
				target.setFoodLevel(20);
				target.setSaturation(6.0F);
			}
			else
			{
				sender.sendMessage(Locale.translate("command.online", true));
			}
		}
		else
		{
			((Player) sender).setFoodLevel(20);
			((Player) sender).setSaturation(6.0F);
			sender.sendMessage(Locale.translate("feed", true));
		}
		return true;
	}
}
