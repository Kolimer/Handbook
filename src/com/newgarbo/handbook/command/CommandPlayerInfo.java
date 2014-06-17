package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;
import com.newgarbo.handbook.utils.PlayerUtils;

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
				Player target = Bukkit.getPlayer(args[0]);
				
				sender.sendMessage(ChatColor.GOLD + "-=-=-=Displaying player info for " + ChatColor.GREEN + target.getName() + ChatColor.GOLD + "=-=-=-");
				sender.sendMessage(ChatColor.GOLD + "UUID: " + ChatColor.RED + target.getUniqueId().toString());
				sender.sendMessage(ChatColor.GOLD + "Display name: " + ChatColor.RED + target.getDisplayName());
				sender.sendMessage(ChatColor.GOLD + "Hunger: " + ChatColor.RED + target.getFoodLevel());
				sender.sendMessage(ChatColor.GOLD + "Health: " + ChatColor.RED + target.getHealth());
				sender.sendMessage(ChatColor.GOLD + "X: " + ChatColor.RED + target.getLocation().getBlockX() + ChatColor.GOLD + ", Y: " + ChatColor.RED + target.getLocation().getBlockY() + ChatColor.GOLD + ", Z: " + ChatColor.RED + target.getLocation().getBlockZ());
				sender.sendMessage(ChatColor.GOLD + "Pitch: " + ChatColor.RED + target.getLocation().getPitch() + ChatColor.GOLD + ", Yaw: " + ChatColor.RED + target.getLocation().getYaw());
				sender.sendMessage(ChatColor.GOLD + "IP: " + ChatColor.RED + target.getAddress().getAddress().getHostAddress());
				sender.sendMessage(ChatColor.GOLD + "Is OP: " + ChatColor.RED + target.isOp());
				sender.sendMessage(ChatColor.GOLD + "On ground: " + ChatColor.RED + PlayerUtils.isOnGround(target));
				sender.sendMessage(ChatColor.GOLD + "Flying: " + ChatColor.RED + target.isFlying());
				sender.sendMessage(ChatColor.GOLD + "Gamemode: " + ChatColor.RED + target.getGameMode().toString());
				sender.sendMessage(ChatColor.GOLD + "Can fly: " + ChatColor.RED + target.getAllowFlight());
				sender.sendMessage(ChatColor.GOLD + "World: " + ChatColor.RED + target.getWorld().getName());
			}
			else
			{
				sender.sendMessage(Locale.translate("command.online", true));
			}
		}
		return true;
	}
}
