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
		super("commandbook.playerinfo", "playerinfo", Arrays.asList("info", "pi", "pinfo", "hbplayerinfo"), false);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		if (args.length == 1)
		{
			if (Bukkit.getPlayer(args[0]) != null)
			{
				Player t = Bukkit.getPlayer(args[0]);
				
				sender.sendMessage(ChatColor.GOLD + "-=-=-=Displaying player info for " + ChatColor.GREEN + t.getName() + ChatColor.GOLD + "=-=-=-");
				sender.sendMessage(ChatColor.GOLD + "UUID: " + ChatColor.RED + t.getUniqueId().toString());
				sender.sendMessage(ChatColor.GOLD + "Display name: " + ChatColor.RED + t.getDisplayName());
				sender.sendMessage(ChatColor.GOLD + "Hunger: " + ChatColor.RED + t.getFoodLevel());
				sender.sendMessage(ChatColor.GOLD + "Health: " + ChatColor.RED + t.getHealth());
				sender.sendMessage(ChatColor.GOLD + "X: " + ChatColor.RED + t.getLocation().getBlockX() + ChatColor.GOLD + ", Y: " + ChatColor.RED + t.getLocation().getBlockY() + ChatColor.GOLD + ", Z: " + ChatColor.RED + t.getLocation().getBlockZ());
				sender.sendMessage(ChatColor.GOLD + "Pitch: " + ChatColor.RED + t.getLocation().getPitch() + ChatColor.GOLD + ", Yaw: " + ChatColor.RED + t.getLocation().getYaw());
			}
			else
			{
				sender.sendMessage(Locale.translate("command.online", true));
			}
		}
		return true;
	}
}
