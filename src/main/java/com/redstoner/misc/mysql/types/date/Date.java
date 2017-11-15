package main.java.com.redstoner.misc.mysql.types.date;

import main.java.com.redstoner.misc.mysql.types.MysqlType;

public class Date extends MysqlType {
	@Override
	public String getName() {
		return "DATE";
	}
}
