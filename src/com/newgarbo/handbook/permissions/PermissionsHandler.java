package com.newgarbo.handbook.permissions;

import org.bukkit.entity.Player;

public class PermissionsHandler
{
	/**
	 * @param p - Player to check
	 * @param perm - Which permission to check for
	 * @return true if the player has the specified permission
	 */
	public boolean has(Player p, String perm)
	{
		return p.hasPermission(perm);
	}
}
