package com.newgarbo.handbook.mute;

import java.io.File;
import java.nio.charset.Charset;
import java.util.UUID;

import com.newgarbo.handbook.main.Handbook;
import com.newgarbo.handbook.utils.FileUtil;

public class MuteClient
{
	private String uuid;
	
	private long time;
	
	public boolean isMuted;
	public boolean permanent;
	
	public MuteClient(String uuid)
	{
		this.uuid = uuid;
	}
	
	/**
	 * Populates the 'time' value by reading from the mutes file
	 */
	public void populateData()
	{
		try
		{
			String contents = FileUtil.readFile(Handbook.instance.getDataFolder() + File.separator + "mutes.data", Charset.defaultCharset());
			
			for (String entry : contents.split("\n"))
			{
				if (entry.startsWith(this.uuid + ":"))
				{
					this.time = Long.parseLong(entry.split(":")[1]);
					
					if (this.time != 0) this.isMuted = true;
					if (this.time == -1) this.permanent = true;
					
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getUUID()
	{
		return this.uuid;
	}
	
	public UUID getUUIDObj()
	{
		return UUID.fromString(this.uuid);
	}
	
}
