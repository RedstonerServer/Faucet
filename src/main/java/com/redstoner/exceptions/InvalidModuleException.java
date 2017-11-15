package com.redstoner.exceptions;

import com.redstoner.loader.ModuleLoader;

public class InvalidModuleException extends Exception
{
	public InvalidModuleException()
	{
	}

	public InvalidModuleException(String message)
	{
		super(message);
	}

	public InvalidModuleException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public InvalidModuleException(Throwable cause)
	{
		super(cause);
	}

	public InvalidModuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public static InvalidModuleException descriptorFileMissing(ModuleLoader loader)
	{
		return new InvalidModuleException("Descriptor file missing for module \"" + loader.getModuleName() + "\" in jar file \"" + loader.getJarFile().getPath() + "\"");
	}
}
