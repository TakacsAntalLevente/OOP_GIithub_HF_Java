package homework.tax;

public interface Taxable {
	int taxPercent = 27;
	
	void setTax(double tax);
	double getTax();
	double getTaxedValue();

}
