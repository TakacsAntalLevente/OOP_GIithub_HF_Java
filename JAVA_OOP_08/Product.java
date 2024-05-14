package homework.product;

import homework.tax.Taxable;

public abstract class Product implements Taxable {
	private String name;
	private int price;

	public static  double taxPercent = 27;
	private String currency = "Ft";

	public Product(String name, int price, int taxPercent) {
		this.name = name;
		if (price < 0) {
			this.price = 0;
		} else {
			this.price = price;
		}

		if (taxPercent < 0) {
			this.taxPercent = Taxable.taxPercent;
		} else {
			this.taxPercent = taxPercent;
		}

	}

	public Product(String name, int price) {
		super();
		this.name = name;
		if (price >= 0) {
			this.price = price;
		} else {
			this.price = 0;
		}

	}

	public Product() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if (price >= 0) {
			this.price = price;
		}
		return;
	}

	public void decreasePrice(int percentage) {
		if (percentage > 0) {
			double decreaseAmount = getPrice() * percentage / 100.0;
			double newPrice = getPrice() - decreaseAmount;

			
			setPrice((int) newPrice);
		}
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "Brutto ar=" + getTaxedValue() + "]";
	}

	public void increasePrice(int percent) {
		if (percent <= 0) {
			return;
		} else {
			double increasePercentage = (double) percent / 100;

			int newPrice = (int) Math.round(price * (1 + increasePercentage));

			price = newPrice;
		}

	}
	public static int getTaxPercent() {
        return (int) taxPercent;
    }

	public void setTax(double Tax) {
		if (taxPercent > 0) {
			Product.taxPercent = Taxable.taxPercent;
		} else {
			Product.taxPercent = Tax;
		}

	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return (this.price * Product.taxPercent) / 100;
	}

	@Override
	public double getTaxedValue() {
		if(Product.taxPercent > 0) {
			return this.price + Math.round(this.price*(float)Product.taxPercent/100);
		}
		return this.price + Math.round(this.price*(float)taxPercent/100);
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		if (currency.equals("Ft") || currency.equals("Euro")) {
			this.currency = currency;
			if (currency.equals("Euro")) {
				this.price /= 300;
			} else {
				this.price *= 300;
			}
		} else {
			System.out.println("Nem megfelelo devizanem. Váltás Ft-re.");
			this.currency = "Ft";
		}
	}

	public static  void changeCurrency(Product[] products, String targetCurrency) {
		
		
		for (Product product : products) {
			
			if (product.getCurrency() != targetCurrency) {
				product.setCurrency(targetCurrency);
				
			}
		}
	}
	
	public static int comparePrice(Product p1, Product p2) {
		if(p1.getPrice() > p2.getPrice()) {
			return 1;
		}else if(p2.getPrice() >p1.getPrice()){
			return 2;
		}else {
			return 0;
		}
	}
	
	
	public abstract int getUnitPrice();


}
