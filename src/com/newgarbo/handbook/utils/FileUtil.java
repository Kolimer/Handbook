package com.newgarbo.handbook.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil
{
	public static String readFile(String path, Charset encoding) throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)), encoding);
	}
	
}
