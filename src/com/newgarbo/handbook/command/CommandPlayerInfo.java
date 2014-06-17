package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;

public class CommandPlayerInfo extends Command
{
	public CommandPlayerInfo()
	{
		super("commandbook.playerinfo", "playerinfo", Arrays.asList("info", "pi", "pinfo"), false);
	}
	
	@Override
	protected boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		if (args.length == 1)
		{
			if (Bukkit.getPlayer(args[0]) != null)
			{
				Player t = Bukkit.getPlayer(args[0]);
				
				sender.sendMessage(ChatColor.BLUE + "-=-=-=Displaying player info for " + ChatColor.GREEN + t.getName() + ChatColor.BLUE + "=-=-=-");
				sender.sendMessage(ChatColor.BLUE + "UUID: " + t.getUniqueId().toString());
				sender.sendMessage(ChatColor.BLUE + "Display name: " + t.getDisplayName());
				sender.sendMessage(ChatColor.BLUE + "Hunger: " + t.getFoodLevel());
				sender.sendMessage(ChatColor.BLUE + "Health: " + t.getHealth());
				sender.sendMessage(ChatColor.BLUE + "X: " + t.getLocation().getBlockX() + ", Y: " + t.getLocation().getBlockY() + ", Z:" + t.getLocation().getBlockZ());
				sender.sendMessage(ChatColor.BLUE + "Pitch: " + t.getLocation().getPitch() + ", Yaw: " + t.getLocation().getYaw());
			}
			else
			{
				sender.sendMessage(Locale.translate("command.online", true));
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		CommandPlayerInfo
	}
}
