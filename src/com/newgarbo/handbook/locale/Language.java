package com.newgarbo.handbook.locale;

import java.util.ResourceBundle;

public enum Language
{
	ENGLISH(Locale.ENGLISH_BUNDLE),
	RUSSIAN(Locale.RUSSIAN_BUNDLE),
	LITHUANIAN(Locale.LITHUANIAN_BUNDLE);
	
	public ResourceBundle bundle;
	
	Language(ResourceBundle bundle)
	{
		this.bundle = bundle;
	}
}
