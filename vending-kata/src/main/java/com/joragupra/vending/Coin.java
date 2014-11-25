package com.joragupra.vending;

/**
 * @author jagudo
 * @since 22.07.13
 */
public enum Coin {
	NICKEL(5),DIME(10),QUARTER(25),DOLLAR(100);

	long value;

	Coin(long value) {
		this.value = value;
	}
}
