package com.newgarbo.handbook.locale;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.newgarbo.handbook.main.Handbook;

public class Locale
{
	public static final ResourceBundle ENGLISH_BUNDLE = ResourceBundle.getBundle("com.newgarbo.handbook.locale.en_US");
	public static final ResourceBundle LITHUANIAN_BUNDLE = ResourceBundle.getBundle("com.newgarbo.handbook.locale.lt_LT");
	public static final ResourceBundle RUSSIAN_BUNDLE = ResourceBundle.getBundle("com.newgarbo.handbook.locale.ru_RU");
	
	public static String translate(String key)
	{
		try
		{
			return Handbook.instance.values.serverLanguage.bundle.getString(key);
		}
		catch (MissingResourceException e)
		{
			return key;
		}
	}
}
