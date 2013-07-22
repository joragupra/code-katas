package com.joragupra.vending;

import junit.framework.TestCase;

import java.util.List;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class VendingTest extends TestCase {

	public void testAllCoinsReturned() {
		Machine machine = new Machine();
		machine.insert(Coin.QUARTER);
		machine.insert(Coin.QUARTER);
		List<Coin> returnedCoins = machine.returnCoins();
		assertEquals(Coin.QUARTER, returnedCoins.get(0));
		assertEquals(Coin.QUARTER, returnedCoins.get(1));
	}
}
