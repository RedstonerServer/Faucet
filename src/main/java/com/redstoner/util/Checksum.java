package com.redstoner.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public enum Checksum
{
	MD5("MD5"),
	SHA1("SHA1"),
	SHA256("SHA-256"),
	SHA512("SHA-512");

	private String name;

	Checksum(String name)
	{
		this.name = name;
	}

	public byte[] compute(File input)
	{
		try (InputStream in = new FileInputStream(input))
		{
			MessageDigest digest = MessageDigest.getInstance(getName());
			byte[] block = new byte[4096];
			int length;
			while ((length = in.read(block)) > 0)
			{
				digest.update(block, 0, length);
			}
			return digest.digest();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public String getName()
	{
		return name;
	}

}