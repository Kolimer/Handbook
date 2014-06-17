package com.newgarbo.handbook.permissions;

import org.bukkit.command.CommandSender;

public class PermissionsHandler
{
	/**
	 * @param sender - CommandSender to check
	 * @param perm - Which permission to check for
	 * @return true if the player has the specified permission
	 */
	public boolean has(CommandSender sender, String perm)
	{
		return sender.hasPermission(perm);
	}
}
