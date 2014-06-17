package com.newgarbo.handbook.utils;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;
import com.newgarbo.handbook.main.Handbook;

public class PlayerUtils
{
	public static boolean vanishPlayer(Player p, boolean inform)
	{
		if (Handbook.instance.playerData.vanished.contains(p.getName()))
		{
			Handbook.instance.playerData.vanished.remove(p.getName());
			if (inform) p.sendMessage(Locale.translate("vanish.off", true));
			for (Player online : Bukkit.getOnlinePlayers())
				online.showPlayer(p);
		}
		else
		{
			Handbook.instance.playerData.vanished.add(p.getName());
			if (inform) p.sendMessage(Locale.translate("vanish.on", true));
			for (Player online : Bukkit.getOnlinePlayers())
				online.hidePlayer(p);
		}
		
		return Handbook.instance.playerData.vanished.contains(p.getName());
	}
	
	public static boolean isOnGround(Player p)
	{
		return p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid();
	}
}
