package coffeeOrderingSystem;

public class Order {
	private Coffee coffee;
	private int quantity;

	public Order(Coffee coffee, int quantity) {
		this.coffee = coffee;
		this.quantity = quantity;
	}

	public Coffee getCoffee() {
		return this.coffee;
	}

	public int getQuantity() {
		return this.quantity;
	}
}
