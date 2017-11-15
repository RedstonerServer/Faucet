package com.redstoner.misc;

import org.bukkit.command.CommandSender;

/** Classes implementing this interface can be used to define a filter for the Utils.broadcast method for sending a message to more than one, but less than all users.
 * 
 * @author Pepich */
public interface BroadcastFilter
{
	public boolean sendTo(CommandSender recipient);
}
