package main.java.com.redstoner.misc.mysql.types.text;

import main.java.com.redstoner.misc.mysql.types.MysqlType;

public class Blob extends MysqlType {
	@Override
	public String getName() {
		return "BLOB";
	}
}
