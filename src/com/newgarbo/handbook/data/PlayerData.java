package com.newgarbo.handbook.data;

import java.util.ArrayList;

public class PlayerData
{
	/**
	 * A list of currently vanished players.<p> Used for the toggling effect of
	 * /vanish and to make sure that when a new player joins it hides vanished
	 * players from them.
	 */
	public ArrayList<String> vanished;
	
	public PlayerData()
	{
		this.vanished = new ArrayList<>();
	}
	
}
