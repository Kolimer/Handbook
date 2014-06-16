package com.newgarbo.handbook.main;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import com.newgarbo.handbook.listeners.PlayerListener;
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
	
	/** The server instance */
	private Server server;
	
	@Override
	public void onEnable()
	{
		Handbook.instance = this;
		
		this.permissions = new PermissionsHandler();
		this.uuid = new UUIDUtils();
		this.server = this.getServer();
		
		setup("listeners");
	}
	
	/**
	 * Sets up the specified object(s)
	 */
	private void setup(String key)
	{
		if (key.equalsIgnoreCase("listeners"))
		{
			this.server.getPluginManager().registerEvents(new PlayerListener(), this);
		}
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
