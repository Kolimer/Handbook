package com.newgarbo.handbook.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;
import com.newgarbo.handbook.main.Handbook;

public class PlayerUtils
{
	/**
	 * Toggles vanish for the specified player.
	 * 
	 * @param p - Whom the code should toggle vanish for.
	 * @param inform - Whether or not the code should send a message to the
	 *        specified player telling them they've been vanished/un-vanished.
	 * @return true if the specified player is currently in the vanished list.
	 */
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
	
	/**
	 * @param p - Player to check
	 * @return true if the player is on ground
	 */
	public static boolean isOnGround(Player p)
	{
		return ((CraftPlayer) p).isOnGround();
	}
}
