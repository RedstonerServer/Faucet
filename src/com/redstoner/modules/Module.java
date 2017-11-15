package com.redstoner.modules;

import com.redstoner.annotations.Version;
import com.redstoner.faucet.Faucet;

/** Interface for the Module class. Modules must always have an empty constructor to be invoked by the ModuleLoader.
 * 
 * @author Pepich */
public interface Module
{
	/** This method gets called to enable your module.
	 * It is guaranteed that all hard dependencies were loaded and enabled successfully.<br/>
	 * If is not guaranteed that all soft dependencies were loaded and enabled successfully, but it is guaranteed that there was an attempt to do so.<br/>
	 * If a hard dependency is missing or could not be enabled, then this method will not be called at all.<br/>
	 * If a soft dependency is missing or could not be enabled, then this method will be run regardless.<br/>
	 * 
	 * @return true, if and only if your module successfully enabled. Return false to tell the loader that something went wrong. */
	public default boolean onEnable()
	{
		return true;
	}
	
	/** This method gets called after ALL modules were enabled.<br/>
	 * You can use this method to do initialization based on which other modules are loaded and enabled. */
	public default void postEnable()
	{}
	
	/** This method will be called when the loader disables the module. You can use this method to do cleanup.<br/>
	 * Please note that this method is run ASYNC and with a 10ms timeout.<br/>
	 * If the timeout gets exceeded the execution will be aborted. If you need to perform a task that will be longer than the given timeout,
	 * you can use setTimeout(int timeout) to change it as you desire. If you need your execution to be done sync, annotate this method with @SYNCHRONISED. */
	public default void onDisable()
	{}
	
	/** Gets called on registration of the module, when this option is selected for command registration
	 * 
	 * @return The String used for the CommandManager to register the commands. */
	public default String getCommandString()
	{
		return null;
	}
	
	/** This method gets run the very first time a module gets loaded. You can use this to set up file structures or background data. */
	public default void firstLoad()
	{}
	
	/** This method gets run every time a module gets loaded and its version has changed.
	 * 
	 * @param old The version of the previous module. */
	public default void migrate(Version old)
	{}
	
	public default boolean enabled()
	{
		return Faucet.isEnabled(this);
	}
	
	public default void disable()
	{
		Faucet.disable(this);
	}
}
