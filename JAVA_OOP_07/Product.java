package homework.product;

public class Product {
    private String name;
    private int price;


    public Product(String name,int price) {
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
            
            newPrice = Math.round(newPrice)+0.4;
            setPrice((int) newPrice);
        }
    }


	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}

	public void increasePrice(int percent) {
		if (percent <= 0) {
	        return; 
	    }else {
	    	double increasePercentage = (double) percent / 100;
		    
		    
		    int newPrice = (int) Math.round(price * (1 + increasePercentage));

		   
		    price = newPrice;
	    }
		
	}

  
   
}
