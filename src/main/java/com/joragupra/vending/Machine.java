package com.joragupra.vending;

import java.util.*;

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
		registerPurchase(result);
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

	private final List<Coin> coinsOrderedByValueDesc = Arrays.asList(new Coin[]{Coin.DOLLAR, Coin.QUARTER, Coin.DIME, Coin.NICKEL});

	private List<Coin> calculateChange(Item selectedItem) throws NotEnoughCoinsForChangeException {
		long changeAmount = calculatePaidAmount() - selectedItem.price;
		List<Coin> change = new ArrayList<Coin>();
		while (changeAmount > 0) {
			Coin coin = pickNextCoin(changeAmount);
			if (coin == null) {
				unmergeInsertedCoinsWithAvailableCoins();
				throw new NotEnoughCoinsForChangeException();
			}
			else {
				changeAmount -= coin.value;
				change.add(coin);
				availableCoins.put(coin, availableCoins.get(coin) - 1);
			}
		}
		return change;
	}

	private Coin pickNextCoin(long changeAmount) {
		Coin nextCoin = null;
		for (Coin coin : coinsOrderedByValueDesc) {
			if (canUseCoinForChange(coin, changeAmount)) {
				nextCoin = coin;
				break;
			}
		}
		return nextCoin;
	}

	private boolean canUseCoinForChange(Coin coin, long changeAmount) {
		return coin.value <= changeAmount && availableCoins.get(coin)>0;
	}

	private void unmergeInsertedCoinsWithAvailableCoins() {
		for (Coin coin : insertedCoins) {
			availableCoins.put(coin, availableCoins.get(coin) - 1);
		}
	}

	private void registerPurchase(Purchase purchase) {
		availableItems.put(purchase.getItem(), availableItems.get(purchase.getItem()) - 1);
		insertedCoins = new ArrayList<Coin>();
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
