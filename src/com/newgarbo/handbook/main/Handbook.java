package com.newgarbo.handbook.main;

import org.bukkit.plugin.java.JavaPlugin;

import com.newgarbo.handbook.permissions.PermissionsHandler;
import com.newgarbo.handbook.utils.UUIDUtils;

public class Handbook extends JavaPlugin
{
	/** The static instance of the plugin class */
	public static Handbook instance = null;
	
	/** Permissions Handler instance */
	public PermissionsHandler permissions;
	
	/** UUID Utilities instance */
	public UUIDUtils uuid;
	
	@Override
	public void onEnable()
	{
		Handbook.instance = this;
		
		this.permissions = new PermissionsHandler();
		this.uuid = new UUIDUtils();
	}
	
	@Override
	public void onDisable()
	{
		Handbook.instance = null;
	}
	
	@Override
	public String toString()
	{
		return this.getDescription().getFullName();
	}
}
