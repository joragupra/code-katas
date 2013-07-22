package com.joragupra.vending;

import junit.framework.TestCase;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class VendingTest extends TestCase {

	public void testAllCoinsReturned() {
		Machine machine = new Machine();
		machine.insert(Coin.QUARTER);
		machine.insert(Coin.QUARTER);
		assertEquals(Coin.QUARTER, machine.returnCoins().get(0));
		assertEquals(Coin.QUARTER, machine.returnCoins().get(1));
	}
}
