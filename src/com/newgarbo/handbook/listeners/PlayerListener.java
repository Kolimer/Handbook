package com.newgarbo.handbook.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.newgarbo.handbook.main.Handbook;

public class PlayerListener implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		Handbook.instance.uuid.addEntry(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName());
	
	}
}
