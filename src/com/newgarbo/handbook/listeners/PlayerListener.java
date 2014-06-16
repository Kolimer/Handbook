package com.newgarbo.handbook.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.newgarbo.handbook.main.Handbook;

@SuppressWarnings("deprecation")
public class PlayerListener implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		Handbook.instance.uuid.addEntry(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName());
		
		for (String p : Handbook.instance.playerData.vanished)
		{
			e.getPlayer().hidePlayer(Bukkit.getPlayer(p));
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		if (Handbook.instance.playerData.vanished.contains(e.getPlayer().getName()))
		{
			Handbook.instance.playerData.vanished.remove(e.getPlayer().getName());
		}
	}
}
