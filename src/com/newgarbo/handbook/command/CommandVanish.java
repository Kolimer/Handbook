package com.newgarbo.handbook.command;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.utils.PlayerUtils;

public class CommandVanish extends Command
{
	public CommandVanish()
	{
		super("commandbook.vanish", "vanish", Arrays.asList("v", "va"), true);
	}
	
	@Override
	protected boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		PlayerUtils.vanishPlayer((Player) sender, true);
		
		return true;
	}
}
