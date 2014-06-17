package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;
import com.newgarbo.handbook.utils.BooleanUtils;
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
				
				sender.sendMessage(ChatColor.GRAY + "-=-=-=Displaying player info for " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + "=-=-=-");
				sender.sendMessage(ChatColor.GRAY + "UUID: " + ChatColor.GREEN + target.getUniqueId().toString());
				sender.sendMessage(ChatColor.GRAY + "Display name: " + ChatColor.GREEN + target.getDisplayName());
				sender.sendMessage(ChatColor.GRAY + "Hunger: " + ChatColor.GREEN + target.getFoodLevel());
				sender.sendMessage(ChatColor.GRAY + "Health: " + ChatColor.GREEN + target.getHealth());
				sender.sendMessage(ChatColor.GRAY + "X: " + ChatColor.GREEN + target.getLocation().getBlockX() + ChatColor.GRAY + ", Y: " + ChatColor.GREEN + target.getLocation().getBlockY() + ChatColor.GRAY + ", Z: " + ChatColor.GREEN + target.getLocation().getBlockZ());
				sender.sendMessage(ChatColor.GRAY + "Pitch: " + ChatColor.GREEN + target.getLocation().getPitch() + ChatColor.GRAY + ", Yaw: " + ChatColor.GREEN + target.getLocation().getYaw());
				sender.sendMessage(ChatColor.GRAY + "IP: " + ChatColor.GREEN + target.getAddress().getAddress().getHostAddress());
				sender.sendMessage(ChatColor.GRAY + "Is OP: " + ChatColor.GREEN + BooleanUtils.friendlyName(target.isOp()));
				sender.sendMessage(ChatColor.GRAY + "On ground: " + ChatColor.GREEN + PlayerUtils.isOnGround(target));
				sender.sendMessage(ChatColor.GRAY + "Flying: " + ChatColor.GREEN + BooleanUtils.friendlyName(target.isFlying()));
				sender.sendMessage(ChatColor.GRAY + "Gamemode: " + ChatColor.GREEN + target.getGameMode().toString());
				sender.sendMessage(ChatColor.GRAY + "Can fly: " + ChatColor.GREEN + BooleanUtils.friendlyName(target.getAllowFlight()));
				sender.sendMessage(ChatColor.GRAY + "World: " + ChatColor.GREEN + target.getWorld().getName());
			}
			else
			{
				sender.sendMessage(Locale.translate("command.online", true));
			}
		}
		return true;
	}
}
