package com.entity.ex;

public class ExFactory {
	/**
	 * 创建附赠品
	 * id 1对应CD 2对应Pen 3对应Bag  
	 * 
	 */
	public static EX create(int id)  {
		switch (id) {
		case 1:
			return new CD();
		case 2:
			return new Pen();
		case 3:
			return new Bag();
		default:
			return null;
			}
	}
}
