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

	private Map<Coin, Long> availableCoins;

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
		availableCoins = new HashMap<Coin, Long>();
		availableCoins.put(Coin.NICKEL, 1000L);
		availableCoins.put(Coin.DIME, 1000L);
		availableCoins.put(Coin.QUARTER, 1000L);
		availableCoins.put(Coin.DOLLAR, 1000L);
	}

	public void insert(Coin coin) {
		insertedCoins.add(coin);
	}

	public List<Coin> returnCoins() {
		List<Coin> coins = new ArrayList<Coin>(insertedCoins);
		insertedCoins = new ArrayList<Coin>();
		return coins;
	}

	public Purchase buy(Item selectedItem) throws ProductNotAvailableException, NotEnoughCoinsForChangeException, NotEnoughMoneyException {
		if (isAvailable(selectedItem)) {
			throw new ProductNotAvailableException();
		}
		if (!hasPaidEnough(selectedItem)) {
			throw new NotEnoughMoneyException();
		}
		mergeInsertedCoinsWithAvailableCoins();
		Purchase result = new Purchase(selectedItem, calculateChange(selectedItem));
		availableItems.put(selectedItem, availableItems.get(selectedItem) - 1);
		insertedCoins = new ArrayList<Coin>();
		return result;
	}

	private boolean isAvailable(Item selectedItem) {
		return !(availableItems.get(selectedItem)>0);
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

	private void mergeInsertedCoinsWithAvailableCoins() {
		for (Coin coin : insertedCoins) {
			availableCoins.put(coin, availableCoins.get(coin) + 1);
		}
	}

	private void unmergeInsertedCoinsWithAvailableCoins() {
		for (Coin coin : insertedCoins) {
			availableCoins.put(coin, availableCoins.get(coin) - 1);
		}
	}

	private List<Coin> calculateChange(Item selectedItem) throws NotEnoughCoinsForChangeException {
		long changeAmount = calculatePaidAmount() - selectedItem.price;
		List<Coin> change = new ArrayList<Coin>();
		while (changeAmount > 0) {
			boolean coinSelected = false;
			if (canUseCoinForChange(Coin.DOLLAR, changeAmount)) {
				change.add(Coin.DOLLAR);
				changeAmount -= Coin.DOLLAR.value;
				availableCoins.put(Coin.DOLLAR, availableCoins.get(Coin.DOLLAR) - 1);
				coinSelected = true;
			}
			else if (canUseCoinForChange(Coin.QUARTER, changeAmount)) {
				change.add(Coin.QUARTER);
				changeAmount -= Coin.QUARTER.value;
				availableCoins.put(Coin.QUARTER, availableCoins.get(Coin.QUARTER) - 1);
				coinSelected = true;
			}
			else if (canUseCoinForChange(Coin.DIME, changeAmount)) {
				change.add(Coin.DIME);
				changeAmount -= Coin.DIME.value;
				availableCoins.put(Coin.DIME, availableCoins.get(Coin.DIME) - 1);
				coinSelected = true;
			}
			else if (canUseCoinForChange(Coin.NICKEL, changeAmount)) {
				change.add(Coin.NICKEL);
				changeAmount -= Coin.NICKEL.value;
				availableCoins.put(Coin.NICKEL, availableCoins.get(Coin.NICKEL) - 1);
				coinSelected = true;
			}
			if (!coinSelected) {
				unmergeInsertedCoinsWithAvailableCoins();
				throw new NotEnoughCoinsForChangeException();
			}
		}
		return change;
	}

	private boolean canUseCoinForChange(Coin coin, long changeAmount) {
		return coin.value <= changeAmount && availableCoins.get(coin)>0;
	}

	public void service(Item item, Long availability) {
		availableItems.put(item, availability);
	}

	public void service(Coin coin, Long availability) {
		availableCoins.put(coin, availability);
	}

	public enum Item {
		A(65),B(100),C(150);

		private long price;

		Item(long price) {
			this.price = price;
		}
	}
}
