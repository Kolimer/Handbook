package com.newgarbo.handbook.utils;

public class BooleanUtils
{
	/**
	 * @param flag - Boolean to translate to 'yes' or 'no'
	 * @return A user-friendly name from a boolean
	 */
	public static String friendlyName(boolean flag)
	{
		return flag ? "yes" : "no";
	}
}
