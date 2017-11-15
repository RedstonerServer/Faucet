package main.java.com.redstoner.misc.mysql.types.date;

import main.java.com.redstoner.misc.mysql.types.MysqlType;

public class Year extends MysqlType {
	@Override
	public String getName() {
		return "YEAR";
	}
}
