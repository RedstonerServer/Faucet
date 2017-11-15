package com.redstoner.faucet;

import com.nemez.cmdmgr.Command;
import com.redstoner.annotations.Version;
import com.redstoner.misc.mysql.MysqlHandler;
import com.redstoner.modules.Module;
import net.nemez.chatapi.ChatAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/** Main class. Duh.
 * 
 * @author Pepich */
@Version(major = 4, minor = 0, revision = 0, compatible = -1)
public class Faucet extends JavaPlugin
{
	public static JavaPlugin plugin;
	public static HashMap<String, Module> modules = new HashMap<>();
	public static HashMap<Module, Boolean> states = new HashMap<>();
	
	private final ClassLoader parentLoader;
	
	public Faucet()
	{
		parentLoader = Faucet.class.getClassLoader();
	}
	
	@Override
	public void onEnable()
	{
		plugin = this;
		ChatAPI.initialize(this);
		MysqlHandler.init();
	}
	
	@Override
	public void onDisable()
	{}
	
	@Command(hook = "load")
	public static boolean loadModule()
	{
		return false;
	}
	
	@Command(hook = "tap")
	public static boolean loadBarrel()
	{
		return false;
	}
	
	public static boolean isEnabled(Module module)
	{
		return states.get(module);
	}
	
	public static void disable(String name)
	{
		Faucet.disable(modules.get(name));
	}
	
	public static void disable(Module module)
	{
		
	}

	public ClassLoader getParentLoader()
	{
		return parentLoader;
	}
}
