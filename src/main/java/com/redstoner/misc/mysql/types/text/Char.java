package main.java.com.redstoner.misc.mysql.types.text;

import main.java.com.redstoner.misc.mysql.types.MysqlType;

public class Char extends MysqlType {
	private int size;
	
	public Char(int size) {
		this.size = size;
	}
	
	@Override
	public String getName() {
		return "CHAR(" + size + ")";
	}
}
