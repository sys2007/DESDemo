package org.demo;

public class C extends BaseA {
	static{
		aa="c";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		C.getAa();
		BaseA.getAa();
		B.getAa();
	}

}
