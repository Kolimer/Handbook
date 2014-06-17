package com.newgarbo.handbook.main;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import com.newgarbo.handbook.command.CommandBroadcast;
import com.newgarbo.handbook.command.CommandFlight;
import com.newgarbo.handbook.command.CommandPlayerInfo;
import com.newgarbo.handbook.command.CommandVanish;
import com.newgarbo.handbook.config.Values;
import com.newgarbo.handbook.data.PlayerData;
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
		this.values = new Values();
		
		setup("config");
		setup("listeners");
		setup("commands");
		setup("files");
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
			
			this.saveConfig();
		}
		else if (key.equalsIgnoreCase("commands"))
		{
			getCommand("vanish").setExecutor(new CommandVanish());
			getCommand("playerinfo").setExecutor(new CommandPlayerInfo());
			getCommand("broadcast").setExecutor(new CommandBroadcast());
			getCommand("flight").setExecutor(new CommandFlight());
		}
		else if (key.equalsIgnoreCase("files"))
		{
			File lang = new File(Handbook.instance.getDataFolder(), "en_US.lang");
			
			if (!lang.exists())
			{
				try
				{
					lang.createNewFile();

					PrintWriter writer = new PrintWriter(new FileWriter(lang));

					writer.println("command.permission=&7[&eHandbook&7] &9You require the permission &b%s&9 to execute this command.");
					writer.println("command.playerOnly=&7[&eHandbook&7] &9You have to be a player to execute this command.");
					writer.println("vanish.on=&7[&eHandbook&7] &9You have now been vanished.");
					writer.println("vanish.off=&7[&eHandbook&7] &9You have now been un-vanished.");
					writer.println("vanish.on.other=&7[&eHandbook&7] &9You have now vanished &a%s&9.");
					writer.println("vanish.off.other=&7[&eHandbook&7] &9You have now un-vanished &a%s&9.");
					writer.println("command.online=&7[&eHandbook&7] &9That player is not online!");
					writer.println("command.args=&7[&eHandbook&7] &9Not enough arguments! usage: &a%s&9.");
					writer.println("broadcast=&7[&eBroadcast&7] &6%s");
					writer.println("flight.on.other=&7[&eHandbook&7] &9You have now enabled flight for &a%s&9.");
					writer.println("flight.off.other=&7[&eHandbook&7] &9You have now disable flight for &a%s&9.");
					writer.println("flight.on=&7[&eHandbook&7] &9You have now enabled flight.");
					writer.println("flight.off=&7[&eHandbook&7] &9You have now disabled flight.");
					
					writer.flush();
					writer.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
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
