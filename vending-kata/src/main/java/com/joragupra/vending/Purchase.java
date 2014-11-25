package com.joragupra.vending;

import java.util.List;

/**
 * @author jagudo
 * @since 22.07.13
 */
public class Purchase {

	private Machine.Item item;

	private List<Coin> change;

	public Purchase(Machine.Item item, List<Coin> change) {
		this.item = item;
		this. change = change;
	}

	public Machine.Item getItem() {
		return item;
	}

	public List<Coin> getChange() {
		return change;
	}

}
