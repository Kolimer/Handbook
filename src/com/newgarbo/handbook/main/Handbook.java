package com.newgarbo.handbook.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import com.newgarbo.handbook.command.CommandBroadcast;
import com.newgarbo.handbook.command.CommandFeed;
import com.newgarbo.handbook.command.CommandFlight;
import com.newgarbo.handbook.command.CommandHeal;
import com.newgarbo.handbook.command.CommandPlayerInfo;
import com.newgarbo.handbook.command.CommandVanish;
import com.newgarbo.handbook.config.Values;
import com.newgarbo.handbook.data.PlayerData;
import com.newgarbo.handbook.listeners.PlayerListener;
import com.newgarbo.handbook.locale.Language;
import com.newgarbo.handbook.locale.Locale;
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
			
			this.saveDefaultConfig();
			this.values.customJoinMessage = this.getConfig().getBoolean("UseCustomJoinMessage");
			this.values.joinMessage = this.getConfig().getString("CustomJoinMessage");
			this.values.sendMotd = this.getConfig().getBoolean("SendMotdOnJoin");
			
		}
		else if (key.equalsIgnoreCase("commands"))
		{
			getCommand("vanish").setExecutor(new CommandVanish());
			getCommand("playerinfo").setExecutor(new CommandPlayerInfo());
			getCommand("broadcast").setExecutor(new CommandBroadcast());
			getCommand("flight").setExecutor(new CommandFlight());
			getCommand("heal").setExecutor(new CommandHeal());
			getCommand("feed").setExecutor(new CommandFeed());
		}
		else if (key.equalsIgnoreCase("files"))
		{
			File mutes = new File(getDataFolder(), "mutes.data");
			
			if (!mutes.exists())
			{
				try
				{
					mutes.createNewFile();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
			File lang = new File(getDataFolder(), "en_US.lang");
			
			if (!lang.exists())
			{
				try
				{
					lang.createNewFile();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			Locale.addDefault("command.permission", "&7[&eHandbook&7] &9You require the permission &b%s&9 to execute this command.", Language.ENGLISH);
			Locale.addDefault("command.playerOnly", "&7[&eHandbook&7] &9You have to be a player to execute this command.", Language.ENGLISH);
			Locale.addDefault("vanish.on", "&7[&eHandbook&7] &9You have now been vanished.", Language.ENGLISH);
			Locale.addDefault("vanish.off", "&7[&eHandbook&7] &9You have now been un-vanished.", Language.ENGLISH);
			Locale.addDefault("vanish.on.other", "&7[&eHandbook&7] &9You have now vanished &a%s&9.", Language.ENGLISH);
			Locale.addDefault("vanish.off.other", "&7[&eHandbook&7] &9You have now un-vanished &a%s&9.", Language.ENGLISH);
			Locale.addDefault("command.online", "&7[&eHandbook&7] &9That player is not online!", Language.ENGLISH);
			Locale.addDefault("command.args", "&7[&eHandbook&7] &9Not enough arguments! usage: &a%s&9.", Language.ENGLISH);
			Locale.addDefault("broadcast", "&7[&eBroadcast&7] &6%s", Language.ENGLISH);
			Locale.addDefault("flight.on.other", "&7[&eHandbook&7] &9You have now enabled flight for &a%s&9.", Language.ENGLISH);
			Locale.addDefault("flight.off.other", "&7[&eHandbook&7] &9You have now disable flight for &a%s&9.", Language.ENGLISH);
			Locale.addDefault("flight.on", "&7[&eHandbook&7] &9You have now enabled flight.", Language.ENGLISH);
			Locale.addDefault("flight.off", "&7[&eHandbook&7] &9You have now disabled flight.", Language.ENGLISH);
			Locale.addDefault("heal", "&7[&eHandbook&7] &9You have been healed.", Language.ENGLISH);
			Locale.addDefault("heal.other", "&7[&eHandbook&7] &9You have have healed &a%s&9.", Language.ENGLISH);
			Locale.addDefault("feed", "&7[&eHandbook&7] &9You have have been fed.", Language.ENGLISH);
			Locale.addDefault("feed.other", "&7[&eHandbook&7] &9You have have fed &a%s&9.", Language.ENGLISH);
			
			Locale.addDefault("command.permission", "&7[&eHandbook&7] &9Kad daryti šita veiksmą tu turi turėti leidimą &b%s&9.", Language.LITHUANIAN);
			Locale.addDefault("command.playerOnly", "&7[&eHandbook&7] &9Tu turi būti žmogus kad daryti šita veiksmą.", Language.LITHUANIAN);
			Locale.addDefault("vanish.on", "&7[&eHandbook&7] &9Tu dabar ne matomas.", Language.LITHUANIAN);
			Locale.addDefault("vanish.off", "&7[&eHandbook&7] &9Tu dabar matomas.", Language.LITHUANIAN);
			Locale.addDefault("vanish.on.other", "&7[&eHandbook&7] &a%s&9 dabar ne matomas.", Language.LITHUANIAN);
			Locale.addDefault("vanish.off.other", "&7[&eHandbook&7] &a%s&9 vėl matomas.", Language.LITHUANIAN);
			Locale.addDefault("command.online", "&7[&eHandbook&7] &9Šitas žmogus nėra šio metu prisijunges!", Language.LITHUANIAN);
			Locale.addDefault("command.args", "&7[&eHandbook&7] &9Nepakanka informacijos! Taisingas naudojimas: &a%s&9.", Language.LITHUANIAN);
			Locale.addDefault("broadcast", "&7[&eBroadcast&7] &6%s", Language.LITHUANIAN);
			Locale.addDefault("flight.on.other", "&7[&eHandbook&7] &a%s&9 dabar gali skraidyti.", Language.LITHUANIAN);
			Locale.addDefault("flight.off.other", "&7[&eHandbook&7] &a%s&9 nebegali skraidyti.", Language.LITHUANIAN);
			Locale.addDefault("flight.on", "&7[&eHandbook&7] &9Tu dabar gali skraidyti.", Language.LITHUANIAN);
			Locale.addDefault("flight.off", "&7[&eHandbook&7] &9Tu nebegali skraidyti.", Language.LITHUANIAN);
			Locale.addDefault("heal", "&7[&eHandbook&7] &9Tavo širdutes papildžiau.", Language.LITHUANIAN);
			Locale.addDefault("heal.other", "&7[&eHandbook&7] &9Tu papildei &a%s&9'o širdutes.", Language.LITHUANIAN);
			Locale.addDefault("feed", "&7[&eHandbook&7] &9Tave pamaitinau.", Language.LITHUANIAN);
			Locale.addDefault("feed.other", "&7[&eHandbook&7] &9Tu pamaitinai &a%s&9.", Language.LITHUANIAN);
			
			Locale.loadDefaults();
		}
	}
	
	/**
	 * Returns the currently online players in a list
	 * <p>
	 * I'm using this "hacky" solution because Server#getOnlinePlayers(); now returns a Collection<? extends Player>
	 * <p>
	 * And I really don't feel like iterating through all the values, so I just made this method
	 * 
	 * @return An ArrayList filled with player objects of who is online.
	 */
	public ArrayList<Player> getOnlinePlayers()
	{
		ArrayList<Player> players = new ArrayList<Player>();
		players.addAll(this.getServer().getOnlinePlayers());
		return players;
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
