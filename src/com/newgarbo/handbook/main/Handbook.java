package com.newgarbo.handbook.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Handbook extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		
	}
	
	@Override
	public void onDisable()
	{
	}
	
	@Override
	public String toString()
	{
		return this.getDescription().getFullName();
	}
	
}
