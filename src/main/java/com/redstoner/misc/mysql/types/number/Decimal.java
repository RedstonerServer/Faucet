package main.java.com.redstoner.misc.mysql.types.number;

import main.java.com.redstoner.misc.mysql.types.MysqlType;

public class Decimal extends MysqlType
{
	@Override
	public String getName() {
		return "DECIMAL";
	}
}
