package com.redstoner.loader;

import com.redstoner.exceptions.InvalidModuleException;
import com.redstoner.faucet.Faucet;
import com.redstoner.modules.Module;
import com.redstoner.util.Checksum;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class ModuleLoader
{
	private final Faucet faucet;
	private final String moduleName;
	private final File jarFile;
	private final URL jarFileURL;
	private long jarFileLastModified;
	private byte[] jarFileChecksum;
	private ModuleDescriptor moduleDescriptor;
	private ClassLoader classLoader;
	private boolean isLoaded;
	private Module loadedModule;
	private boolean isEnabled;
	private long loadTime;


	public ModuleLoader(Faucet faucet, String moduleName, File jarFile)
	{
		if (!jarFile.isFile())
		{
			throw new IllegalArgumentException("Expected a file");
		}

		this.faucet = faucet;
		this.moduleName = moduleName;
		this.jarFile = jarFile;
		try
		{
			this.jarFileURL = jarFile.toURL();
		} catch (MalformedURLException e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	public void load() throws InvalidModuleException
	{
		if (isLoaded)
		{
			throw new IllegalStateException("Cannot load a module when it is loaded");
		}

		ClassLoader classLoader = new URLClassLoader(new URL[]{jarFileURL}, faucet.getParentLoader());
		ModuleDescriptor descriptor;
		try (InputStream descriptorStream = classLoader.getResourceAsStream("module-descriptor.yml"))
		{
			if (descriptorStream == null)
			{
				throw InvalidModuleException.descriptorFileMissing(this);
			}

			descriptor = ModuleDescriptor.load(descriptorStream);
		} catch (IOException e)
		{
			throw new InvalidModuleException(e);
		}

		this.classLoader = classLoader;
		this.moduleDescriptor = descriptor;

		// TODO
		// needs to check if version is newer than the old one, if an old one is present
		// etc
	}

	public void reload() throws InvalidModuleException
	{
		if (!isOutdated())
		{
			return;
		}
		unload();
		load();
	}

	public boolean isOutdated()
	{
		if (jarFileChecksum == null)
		{
			return true;
		}

		if (jarFile.lastModified() != jarFileLastModified)
		{
			return true;
		}

		byte[] checksum = computeChecksum();
		return !Arrays.equals(checksum, this.jarFileChecksum);
	}

	public void unload()
	{

	}

	private byte[] computeChecksum()
	{
		return Checksum.SHA1.compute(jarFile);
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public File getJarFile()
	{
		return jarFile;
	}

	public ModuleDescriptor getModuleDescriptor()
	{
		return moduleDescriptor;
	}

	public ClassLoader getClassLoader()
	{
		return classLoader;
	}

	public boolean isLoaded()
	{
		return isLoaded;
	}

	public Module getLoadedModule()
	{
		return loadedModule;
	}

	public boolean isEnabled()
	{
		return isEnabled;
	}

}
