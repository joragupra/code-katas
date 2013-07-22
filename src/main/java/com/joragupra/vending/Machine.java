package com.joragupra.vending;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class Machine {

	private List<Coin> insertedCoins;

	public Machine() {
		insertedCoins = new ArrayList<Coin>();
	}

	public void insert(Coin coin) {
		insertedCoins.add(coin);
	}

	public List<Coin> returnCoins() {
		List<Coin> coins = new ArrayList<Coin>(insertedCoins);
		insertedCoins = new ArrayList<Coin>();
		return coins;
	}
}
