package com.newgarbo.handbook.locale;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.MissingResourceException;

import org.bukkit.ChatColor;

import com.newgarbo.handbook.main.Handbook;
import com.newgarbo.handbook.utils.FileUtil;

public class Locale
{
	/**
	 * Implemented this translations hashmap so there doesn't have to be a file
	 * reader for every translation request.
	 */
	public static HashMap<String, String> translations = new HashMap<>();
	
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
			String toReturn = "";
			
			toReturn = translations.get(key);
			
			if (toReturn == null || toReturn == "null" || toReturn == "")
			{
				toReturn = key;
			}
			
			return colorCode ? toReturn.replace('&', ChatColor.COLOR_CHAR) : toReturn;
		}
		catch (MissingResourceException e)
		{
			return key;
		}
	}
	
	public static void addDefault(String key, String value, Language lang)
	{
		translations.put(key, value);
		
		try
		{
			if (!new File(Handbook.instance.getDataFolder(), lang.code + ".lang").exists())
			{
				new File(Handbook.instance.getDataFolder(), lang.code + ".lang").createNewFile();
			}
			
			String contents = FileUtil.readFile(new File(Handbook.instance.getDataFolder(), lang.code + ".lang").getPath(), Charset.defaultCharset());
			
			if (!contents.contains(key + "="))
			{
				PrintWriter writer = new PrintWriter(new FileWriter(new File(Handbook.instance.getDataFolder(), lang.code + ".lang")));
				
				writer.println(key + "=" + value);
				
				writer.flush();
				writer.close();
			}
		}
		catch (Exception e)
		{
		}
	}
}
