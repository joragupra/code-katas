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

	public void testBuyBWithExactChange() throws Exception {
		Machine machine = new Machine();
		machine.insert(Coin.QUARTER);
		machine.insert(Coin.QUARTER);
		machine.insert(Coin.QUARTER);
		machine.insert(Coin.QUARTER);
		Purchase purchase = machine.buy(Machine.Item.B);
		assertEquals(Machine.Item.B, purchase.getItem());
		assertTrue(purchase.getChange().isEmpty());
	}

	public void testBuyAWithoutExactChange() throws Exception{
		Machine machine = new Machine();
		machine.insert(Coin.DOLLAR);
		Purchase purchase = machine.buy(Machine.Item.A);
		assertEquals(Machine.Item.A, purchase.getItem());
		assertTrue(purchase.getChange().contains(Coin.DIME));
		assertTrue(purchase.getChange().contains(Coin.QUARTER));
	}

	public void testEnoughMoneyCheckedWhenBuying() throws Exception {
		Machine machine = new Machine();
		machine.insert(Coin.QUARTER);
		machine.insert(Coin.QUARTER);
		try {
			machine.buy(Machine.Item.A);
		} catch (NotEnoughMoneyException e) {
			//everything is ok
			return;
		}
		fail("No exception thrown when money was not enough to buy item");
	}

	public void testProductAvailableWhenBuying() throws Exception {
		Machine machine = new Machine();
		machine.service(Machine.Item.A, 0L);
		machine.insert(Coin.DOLLAR);
		try {
			machine.buy(Machine.Item.A);
		} catch (ProductNotAvailableException e) {
			//everything is ok
			return;
		}
		fail("No exception thrown when product was not available");
	}

	public void testEnoughCoinsForChangeWhenBuying() throws Exception {
		Machine machine = new Machine();
		machine.service(Coin.NICKEL, 0L);
		machine.service(Coin.QUARTER, 0L);
		machine.insert(Coin.DOLLAR);
		try {
			machine.buy(Machine.Item.A);
		} catch (NotEnoughCoinsForChangeException e) {
			//everything is ok
			return;
		}
		fail("No exception thrown when available coins were not enough for change");
	}
}
