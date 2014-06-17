package com.newgarbo.handbook.main;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import com.newgarbo.handbook.command.CommandBroadcast;
import com.newgarbo.handbook.command.CommandPlayerInfo;
import com.newgarbo.handbook.command.CommandVanish;
import com.newgarbo.handbook.config.Values;
import com.newgarbo.handbook.data.PlayerData;
import com.newgarbo.handbook.listeners.PlayerListener;
import com.newgarbo.handbook.locale.Language;
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
	
	/** A storage class to store configuration values */
	public Values values;
	
	/** A storage class to store player data, like who's vanished, afk etc. */
	public PlayerData playerData;
	
	@Override
	public void onEnable()
	{
		Handbook.instance = this;
		
		this.permissions = new PermissionsHandler();
		this.uuid = new UUIDUtils();
		this.server = this.getServer();
		this.playerData = new PlayerData();
		
		setup("config");
		setup("listeners");
		setup("commands");
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
		else if (key.equalsIgnoreCase("config"))
		{
			this.getConfig().addDefault("UseCustomJoinMessage", false);
			this.getConfig().addDefault("CustomJoinMessage", "&7[&6Join&7] &a%name% &9in &a%world%");
			this.getConfig().addDefault("SendMotdOnJoin", true);
			
			this.values.customJoinMessage = this.getConfig().getBoolean("UseCustomJoinMessage");
			this.values.joinMessage = this.getConfig().getString("CustomJoinMessage");
			this.values.sendMotd = this.getConfig().getBoolean("SendMotdOnJoin");
			this.values.serverLanguage = Language.valueOf(this.getConfig().getString("ServerLanguage").toUpperCase());
			
			this.saveConfig();
		}
		else if (key.equalsIgnoreCase("commands"))
		{
			getCommand("vanish").setExecutor(new CommandVanish());
			getCommand("playerinfo").setExecutor(new CommandPlayerInfo());
			getCommand("broadcast").setExecutor(new CommandBroadcast());
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
