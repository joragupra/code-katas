package com.joragupra.vending;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class Machine {

	private Map<Item, Long> availableItems;

	private List<Coin> insertedCoins;

	public Machine() {
		insertedCoins = new ArrayList<Coin>();
		setupTestMode();
	}

	private void setupTestMode() {
		availableItems = new HashMap<Item, Long>();
		availableItems.put(Item.A, 1000L);
		availableItems.put(Item.B,  1000L);
		availableItems.put(Item.C, 1000L);
	}

	public void insert(Coin coin) {
		insertedCoins.add(coin);
	}

	public List<Coin> returnCoins() {
		List<Coin> coins = new ArrayList<Coin>(insertedCoins);
		insertedCoins = new ArrayList<Coin>();
		return coins;
	}

	public Purchase buy(Item selectedItem) throws NotEnoughMoneyException, ProductNotAvailableException {
		if (!hasPaidEnough(selectedItem)) {
			throw new NotEnoughMoneyException();
		}
		return new Purchase(selectedItem, calculateChange(selectedItem));
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

	public void service(Item item, Long availability) {
		availableItems.put(item, availability);
	}

	public enum Item {
		A(65),B(100),C(150);

		private long price;

		Item(long price) {
			this.price = price;
		}
	}
}
