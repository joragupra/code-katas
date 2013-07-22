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

	public Purchase buy(Item selectedItem) {
		if (hasPaidEnough(selectedItem)) {
			long paid = 0;
			for (Coin coin : insertedCoins) {
				paid += coin.value;
			}
			long changeAmount = paid - selectedItem.price;
			List<Coin> change = new ArrayList<Coin>();
			while (changeAmount > 0.0) {
				if (Coin.DOLLAR.value <= changeAmount) {
					change.add(Coin.DOLLAR);
					changeAmount -= Coin.DOLLAR.value;
				}
				if (Coin.QUARTER.value <= changeAmount) {
					change.add(Coin.QUARTER);
					changeAmount -= Coin.QUARTER.value;
				}
				if (Coin.DIME.value <= changeAmount) {
					change.add(Coin.DIME);
					changeAmount -= Coin.DIME.value;
				}
				if (Coin.NICKEL.value <= changeAmount) {
					change.add(Coin.NICKEL);
					changeAmount -= Coin.NICKEL.value;
				}
			}
			return new Purchase(selectedItem, change);
		}
		else {
			return null;
		}
	}

	private boolean hasPaidEnough(Item selectedItem) {
		long paid = 0;
		for (Coin coin : insertedCoins) {
			paid += coin.value;
		}
		return (paid >= selectedItem.price);
	}

	public enum Item {
		A(65),B(100),C(150);

		private long price;

		Item(long price) {
			this.price = price;
		}
	}
}
