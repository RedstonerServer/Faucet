package main.java.com.redstoner.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import main.java.com.redstoner.misc.CommandHolder;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Commands
{
	CommandHolder value();
}
