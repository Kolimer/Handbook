package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;
import com.newgarbo.handbook.main.Handbook;
import com.newgarbo.handbook.utils.PlayerUtils;

public class CommandFlight extends Command
{
	public CommandFlight()
	{
		super("handbook.flight", "flight", Arrays.asList("fly", "flying", "hbfly"), true);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		if (args.length >= 1)
		{
			if (sender.hasPermission("handbook.flight.others"))
			{
				if (Bukkit.getPlayer(args[0]) == null)
				{
					sender.sendMessage(Locale.translate("command.online", true));
				}
				else
				{
					Bukkit.getPlayer(args[0]).setAllowFlight(!Bukkit.getPlayer(args[0]).getAllowFlight());
					sender.sendMessage(Bukkit.getPlayer(args[0]).getAllowFlight() ? String.format(Locale.translate("flight.on.other", true), args[0]) : String.format(Locale.translate("flight.off.other", true), args[0]));
					Bukkit.getPlayer(args[0]).sendMessage(Bukkit.getPlayer(args[0]).getAllowFlight() ? Locale.translate("flight.on", true) : Locale.translate("flight.off", true));
				}
			}
			else
			{
				sender.sendMessage(String.format(Locale.translate("command.permission", true), this.getPermission() + ".others"));
			}
		}
		else
		{
			((Player) sender).setAllowFlight(!((Player) sender).getAllowFlight());
			if (((Player) sender).getAllowFlight())
			{
				sender.sendMessage(Locale.translate("flight.on", true));
			}
			else
			{
				sender.sendMessage(Locale.translate("flight.off", true));
			}
		}
		
		return true;
	}
}
