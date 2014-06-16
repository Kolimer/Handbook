package com.newgarbo.handbook.permissions;

import org.bukkit.entity.Player;

public class PermissionsHandler
{
	public boolean has(Player p, String perm)
	{
		return p.hasPermission(perm);
	}
}
