package coffeeOrderingSystem;

public class Coffee {
	private String coffeeName;
	private float coffeePrice;
	
	public Coffee(String coffeeName, float coffeePrice) {
		this.coffeeName = coffeeName;
		this.coffeePrice = coffeePrice;
	}
	
	public String getCoffeeName() {
		return this.coffeeName;
	}

	public float getCoffeePrice() {
		return this.coffeePrice;
	}
}
