package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.newgarbo.handbook.locale.Locale;

public class CommandBroadcast extends Command
{
	public CommandBroadcast()
	{
		super("commandbook.broadcast", "broadcast", Arrays.asList("bc", "b", "hbbroadcast"), false);
	}
	
	@Override
	protected boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		if (args.length > 1)
		{
			String message = "";
			
			for (int i = 0; i < args.length; i++)
			{
				message += args[i] + (i != args.length ? " " : "");
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
