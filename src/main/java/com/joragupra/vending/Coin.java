package com.joragupra.vending;

/**
 * @author jagudo
 * @since 22.07.13
 */
public enum Coin {
	NICKEL(0.05),DIME(0.10),QUARTER(0.25),DOLLAR(1.00);

	double value;

	Coin(double value) {
		this.value = value;
	}
}
