package com.newgarbo.handbook.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import org.bukkit.Bukkit;

import com.newgarbo.handbook.main.Handbook;

public class UUIDUtils
{
	/**
	 * @param uuid - UUID to get name from
	 * @return A String(username) from the specified uuid
	 */
	public String getNameFromUUID(String uuid)
	{
		File db = new File(Handbook.instance.getDataFolder(), "uuids.database");
		
		try
		{
			String[] contents = FileUtil.readFile(db.getPath(), Charset.defaultCharset()).split("\n");
			
			for (String field : contents)
			{
				String fieldName = field.split(":")[0];
				String fieldUUID = field.split(":")[1];
				
				if (fieldUUID.substring(0, fieldUUID.length() - 1).equalsIgnoreCase(uuid))
				{
					return fieldName;
				}
			}
		}
		catch (Exception e)
		{
			Bukkit.getConsoleSender().sendMessage("[Handbook] Error getting name from UUID[" + uuid + "]");
		}
		
		return "nullnullnullnullnull";
	}
	
	/**
	 * @param name - Name to get UUID from
	 * @return A String(UUID) from the specified name
	 */
	public String getUUIDFromName(String name)
	{
		String returnable = "nullnullnullnullnull";
		
		File db = new File(Handbook.instance.getDataFolder(), "uuids.database");
		
		try
		{
			String[] contents = FileUtil.readFile(db.getPath(), Charset.defaultCharset()).split("\n");
			
			for (String field : contents)
			{
				if (field.split(":")[0].equalsIgnoreCase(name))
				{
					returnable = field.split(":")[1];
					break;
				}
				else
				{
					returnable = "nullnullnullnullnull";
				}
			}
		}
		catch (Exception e)
		{
			Bukkit.getConsoleSender().sendMessage("[Handbook] Error getting UUID from name[" + name + "]");
		}
		
		return returnable.substring(0, returnable.length() - 1);
	}
	
	/**
	 * Adds a new entry to the UUIDs database
	 * 
	 * @param uuid - UUID to add
	 * @param name - Name to adds
	 */
	public void addEntry(String uuid, String name)
	{
		try
		{
			File db = new File(Handbook.instance.getDataFolder(), "uuids.database");
			
			if (!db.exists())
			{
				db.createNewFile();
				Bukkit.getConsoleSender().sendMessage("[Handbook] UUIDs database is not created, creating the file now.");
			}
			
			PrintWriter pw = new PrintWriter(new FileWriter(db, true));
			String contents = FileUtil.readFile(db.getPath(), Charset.defaultCharset());
			
			if (!(contents.contains(name)) && !(contents.contains(uuid)))
			{
				pw.println(name + ":" + uuid);
				Bukkit.getConsoleSender().sendMessage("[Handbook] Added new record to UUID database[" + name + ":" + uuid + "]");
			}
			
			pw.flush();
			pw.close();
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
