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

	public Purchase buy(Item selectedItem) throws NotEnoughMoneyException {
		if (hasPaidEnough(selectedItem)) {
			return new Purchase(selectedItem, calculateChange(selectedItem));
		}
		else {
			return null;
		}
	}

	private boolean hasPaidEnough(Item selectedItem) {
		return (calculatePaidAmount() >= selectedItem.price);
	}

	private long calculatePaidAmount() {
		long paid = 0;
		for (Coin coin : insertedCoins) {
			paid += coin.value;
		}
		return paid;
	}

	private List<Coin> calculateChange(Item selectedItem) {
		long changeAmount = calculatePaidAmount() - selectedItem.price;
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
		return change;
	}

	public enum Item {
		A(65),B(100),C(150);

		private long price;

		Item(long price) {
			this.price = price;
		}
	}
}
