package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.newgarbo.handbook.locale.Locale;
import com.newgarbo.handbook.main.Handbook;

public class CommandBroadcast extends Command
{
	public CommandBroadcast()
	{
		super("handbook.broadcast", "broadcast", Arrays.asList("bc", "b", "hbbroadcast"), false);
	}
	
	@Override
	protected boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		if (args.length > 0)
		{
			String message = "";
			
			for (int i = 0; i < args.length; i++)
			{
				message += args[i] + (i != args.length ? " " : "");
			}
			
			if (Handbook.instance.permissions.has(sender, "handbook.broadcast.color"))
			{
				message = message.replace('&', ChatColor.COLOR_CHAR);
			}
			
			Bukkit.broadcastMessage(String.format(Locale.translate("broadcast", true), message));
		}
		else
		{
			sender.sendMessage(String.format(Locale.translate("command.args", true), "/broadcast <message>"));
		}
		return true;
	}
}
