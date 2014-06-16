package com.newgarbo.handbook.main;

import org.bukkit.plugin.java.JavaPlugin;

import com.newgarbo.handbook.permissions.PermissionsHandler;

public class Handbook extends JavaPlugin
{
	public static Handbook instance = null;
	public PermissionsHandler permissions;
	
	@Override
	public void onEnable()
	{
		this.instance = this;
		
		this.permissions = new PermissionsHandler();
	}
	
	@Override
	public void onDisable()
	{
		this.instance = null;
	}
	
	@Override
	public String toString()
	{
		return this.getDescription().getFullName();
	}
}
