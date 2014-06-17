package com.newgarbo.handbook.locale;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.MissingResourceException;

import org.bukkit.ChatColor;

import com.newgarbo.handbook.main.Handbook;
import com.newgarbo.handbook.utils.FileUtil;

public class Locale
{
	/**
	 * A simpler way of getting localizations instead of reading the .lang file
	 * every time the code wants to localize a string.
	 */
	public static HashMap<String, String> localizations = new HashMap<>();
	
	/**
	 * @param key - Localization key
	 * @param colorCode - Whether or not to replace '&' with the
	 *        ChatColor.COLOR_CHAR
	 * @return The localized string from 'key' or just returns 'key' if there is
	 *         no localized string for 'key'
	 */
	public static String translate(String key, boolean colorCode)
	{
		try
		{
			String toReturn = key;
			
			if (localizations.get(key) != null)
			{
				toReturn = localizations.get(key);
			}
			
			return colorCode ? toReturn.replace('&', ChatColor.COLOR_CHAR) : toReturn;
		}
		catch (MissingResourceException e)
		{
			return key;
		}
	}
	
	private static ArrayList<String> tempDefaults;
	
	public static void addDefault(String key, String value, Language lang)
	{
		if (tempDefaults == null) tempDefaults = new ArrayList<>();
		
		tempDefaults.add(key + "=" + value);
		
	}
	
	public static void loadDefaults()
	{
		try
		{
			String contents = FileUtil.readFile(new File(Handbook.instance.getDataFolder(), "en_US.lang").getPath(), Charset.defaultCharset());
			PrintWriter writer = new PrintWriter(new File(Handbook.instance.getDataFolder(), "en_US.lang"));
			
			for (String entry : tempDefaults)
			{
				if (!contents.contains(entry.split("=")[0] + "="))
				{
					writer.println(entry.split("=")[0] + "=" + entry.split("=")[1]);
				}
				
				Locale.localizations.put(entry.split("=")[0], entry.split("=")[1]);
			}
			
			writer.flush();
			writer.close();
		}
		catch (Exception e)
		{
		}
	}
}
