package com.newgarbo.handbook.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil
{
	/**
	 * @param path - The file path to read from
	 * @param encoding - The charset to be used to decode the bytes
	 * @return The contents of the specified file
	 * @throws IOException If an I/O error occurs reading from the stream
	 * @throws OutOfMemoryError If an array of the required size cannot be
	 *         allocated, for example the file is larger that 2GB
	 * @throws SecurityException In the case of the default provider, and a
	 *         security manager is installed, the checkRead method is invoked to
	 *         check read access to the file.
	 */
	public static String readFile(String path, Charset encoding) throws IOException, OutOfMemoryError, SecurityException
	{
		return new String(Files.readAllBytes(Paths.get(path)), encoding);
	}
}
