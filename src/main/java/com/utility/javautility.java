package com.utility;

import java.util.Random;

public class javautility {

	public int randomNum() {
		
		Random ran = new Random();
		int randomNumber = ran.nextInt(1000);
		
		return randomNumber;
		
	}

}
