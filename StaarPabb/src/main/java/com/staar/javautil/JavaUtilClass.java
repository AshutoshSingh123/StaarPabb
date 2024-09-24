package com.staar.javautil;

import java.util.Random;

/**
 * utility class for basic java operations
 * @author Ashutosh
 */
public class JavaUtilClass {
	
	/**
	 * method to generate random number
	 * @return a random integer from 0 to 1000
	 */
	public int getRandom(int limit) {
		Random r=new Random();
		return r.nextInt(limit);
	}
}
