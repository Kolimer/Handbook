package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;

public class CommandHeal extends Command
{
	public CommandHeal()
	{
		super("commandbook.heal", "heal", Arrays.asList("hbheal"), true);
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
				target.sendMessage(Locale.translate("heal", true));
				sender.sendMessage(String.format(Locale.translate("heal.other", true), target.getName()));
				target.setHealth(target.getMaxHealth());
			}
			else
			{
				sender.sendMessage(Locale.translate("command.online", true));
			}
		}
		else
		{
			((Player) sender).setHealth(((Player) sender).getMaxHealth());
			sender.sendMessage(Locale.translate("heal", true));
		}
		return true;
	}
}
