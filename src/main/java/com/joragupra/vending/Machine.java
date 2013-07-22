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

	public Item getItem(Item selectedItem) {
		if (hasPaidEnough(selectedItem)) {
			return selectedItem;
		}
		else {
			return null;
		}
	}

	private boolean hasPaidEnough(Item selectedItem) {
		double paid = 0.0;
		for (Coin coin : insertedCoins) {
			paid += coin.value;
		}
		return (paid > selectedItem.price);
	}

	public enum Item {
		A(0.65),B(1.00),C(1.50);

		private double price;

		Item(double price) {
			price = price;
		}
	}
}
