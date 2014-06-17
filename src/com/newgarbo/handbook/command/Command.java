package com.newgarbo.handbook.command;

import java.util.List;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.newgarbo.handbook.locale.Locale;

public abstract class Command implements CommandExecutor
{
	private List<String> aliases;
	private String name;
	private String permission;
	private boolean playerOnly;
	
	public Command(String permission, String name, List<String> aliases, boolean playerOnly)
	{
		this.permission = permission;
		this.name = name;
		this.aliases = aliases;
		this.playerOnly = playerOnly;
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, org.bukkit.command.Command arg1, String arg2, String[] arg3)
	{
		if (!(arg0 instanceof Player))
		{
			if (this.isPlayerOnly())
			{
				arg0.sendMessage(Locale.translate("command.playerOnly", true));
				return true;
			}
			else
			{
				return execute(arg0, arg1, arg2, arg3);
			}
		}
		else if (arg0 instanceof Player)
		{
			if (((Player) arg0).hasPermission(this.getPermission()) || ((Player) arg0).isOp())
			{
				return execute(arg0, arg1, arg2, arg3);
			}
			else
			{
				arg0.sendMessage(String.format(Locale.translate("command.permission", true), this.getPermission()));
				return true;
			}
		}
		else
		{
			return execute(arg0, arg1, arg2, arg3);
		}
	}
	
	/**
	 * Executes this command
	 * 
	 * @param sender - Sender of the command
	 * @param cmd - The bukkit command that was sent
	 * @param label - The command label sent
	 * @param args - Arguments
	 * @return true if the command was executed correctly
	 */
	protected abstract boolean execute(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args);
	
	/**
	 * @return true if this command is only executable by a player
	 */
	public boolean isPlayerOnly()
	{
		return this.playerOnly;
	}
	
	/**
	 * @return The main name of this command.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * @return A list of Strings which are the aliases of this command
	 */
	public List<String> getAliases()
	{
		return this.aliases;
	}
	
	/**
	 * @return A string which is the required permission to execute this command
	 */
	public String getPermission()
	{
		return this.permission;
	}
	
}
